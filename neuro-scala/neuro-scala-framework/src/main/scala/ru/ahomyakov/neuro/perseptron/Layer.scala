package ru.ahomyakov.neuro.perseptron

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import util.Random
import ru.ahomyakov.neuro.VectorUtils

class Layer(weights: Array[Array[Double]],
            aFunc: Double => Double,
            dAFuncDx: Double => Double) {
  /**
   * Инициализация слоя из сохранённого в б/д json-объекта
   */
  def this(o: DBObject, aFunc: Double => Double, dAFuncDx: Double => Double) =
    this (o.get("matrix").toString.split("::").
      map(col => col.split(":").
      map(element => java.lang.Double.parseDouble(element))), aFunc, dAFuncDx);

  /**
   * Инициализация слоя указанной размерности случайными весами
   */
  def this(inputs: Int,
           outputs: Int,
           aFunc: Double => Double,
           dAFuncDx: Double => Double) =
    this (
      (for (i <- 0 to outputs - 1) yield
        (for (j <- 0 to inputs - 1) yield
          Random.nextDouble() % 1D).toArray).toArray, aFunc, dAFuncDx);

  /**
   * Объект для сохранения в mongo
   */
  def toDatabaseObject: DBObject =
    MongoDBObject("matrix" -> weights.foldLeft("")
      ((a, b) => (a + (if (a.isEmpty) "" else "::") + b.foldLeft("")
        ((c, d) => (c + (if (c.isEmpty) "" else ":") + d)))));


  /**
   * Выполнить преобразование слоя
   */
  def apply(input: Array[Double]): Array[Double] =
    weights.map(x => aFunc(multiplyV(input, x)));

  protected def multiplyV(rowVector: Array[Double],
                          colVector: Array[Double]): Double =
    (for (i: Int <- 0 to rowVector.length - 1) yield
      rowVector(i) * colVector(i)).foldLeft(0D)(_ + _);

  /**
   * Обратное распространение ошибки
   */
  def errorBackTrace(err: Seq[Double], output: Array[Double]): Seq[Double] =
    (for (i <- 0 to weights(0).length - 1) yield
      (for (j <- 0 to weights.length - 1) yield
        dAFuncDx(output(j)) * weights(j)(i)).foldLeft(0D)(_ + _));


  /**
   * Создание нового слоя с откорректированной матрицей весов
   */
  def correctWeights(err: Array[Double],
                     prevOutput: Array[Double],
                     teachingCoeff: Double): Layer =
    new Layer(correctWeightsMatrix(err, prevOutput, teachingCoeff), aFunc, dAFuncDx);

  /**
   * Корректировка весов слоя
   */
  protected def correctWeightsMatrix(err: Array[Double], prevOutput: Array[Double],
                           teachingCoeff: Double): Array[Array[Double]] =
    (for (j <- 0 to weights.length - 1) yield
      ((for (i <- 0 to weights(j).length - 1) yield
        weights(j)(i) + err(j) * prevOutput(i) * teachingCoeff).toArray)).toArray;

  override def toString = "Layer\n " + weights(0).length +
    " inputs\n" + weights.length + " outputs\n" +
    "weights: " + VectorUtils.printMatrix(weights) + "\n";
}
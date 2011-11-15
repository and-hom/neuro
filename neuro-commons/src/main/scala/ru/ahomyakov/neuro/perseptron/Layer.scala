package ru.ahomyakov.neuro.perseptron

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import util.Random

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
    (for (i: Int <- 0 to rowVector.length - 1)
    yield rowVector(i) * colVector(i)).
      foldLeft(0D)(_ + _);

  /**
   * Обратное распространение ошибки
   */
  def errorBackTrace(err: Double): Double = err;

  /**
   * Корректировка весов
   */
  def correctWeights(err: Double): Layer = this;
  //    new Layer(weights.map(col =>), aFunc);
  //  for (i <- 0 to requiredOutput.length - 1) yield
  //    (requiredOutput(i) - realOutput(i)) * teachingCoeff

}
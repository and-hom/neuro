package ru.ahomyakov.neuro.perseptron

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import util.Random
import ru.ahomyakov.neuro.VectorUtils

class Layer(weights: Array[Array[Double]],
            shift: Array[Double],
            aFunc: Double => Double,
            dAFuncDx: Double => Double) {
  /**
   * Инициализация слоя из сохранённого в б/д json-объекта
   */
  def this(o: DBObject, aFunc: Double => Double, dAFuncDx: Double => Double) =
    this (o.get("matrix").toString.split("::").
      map(col => col.split(":").
      map(element => java.lang.Double.parseDouble(element))),
      o.get("shift").toString.split(":").
        map(element => java.lang.Double.parseDouble(element)),
      aFunc, dAFuncDx);

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
          Random.nextDouble() % 1D).toArray).toArray,
      (for (j <- 0 to outputs - 1) yield
        Random.nextDouble() % 1D).toArray,
      aFunc, dAFuncDx);

  /**
   * Объект для сохранения в mongo
   */
  def toDatabaseObject: DBObject =
    MongoDBObject("matrix" -> weights.foldLeft("")
      ((str1, row) => (str1 + (if (str1.isEmpty) "" else "::") + row.foldLeft("")
        ((str2, cell) => (str2 + (if (str2.isEmpty) "" else ":") + cell)))),
      "shift" -> shift.foldLeft("")
        ((str, elem) => if (str.isEmpty) elem.toString else str + ":" + elem));


  /**
   * Выполнить преобразование слоя
   */
  def apply(input: Array[Double]): Array[Double] =
    applyFunction(applyWeightsAndShift(input));

  /**
   * Применить матрицу весов и вектор сдвига
   */
  def applyWeightsAndShift(input: Array[Double]): Array[Double] =
    Math.sumV(weights.map(x => Math.multiplyVScalar(input, x)), shift);

  /**
   * Применить ф-цию активности
   */
  def applyFunction(input: Array[Double]): Array[Double] =
    input.map(x => aFunc(x));


  /**
   * Обратное распространение ошибки
   */
  def errorBackTrace(err: Array[Double]): Array[Double] =
    (for (i <- 0 to weights(0).length - 1) yield
      (for (j <- 0 to weights.length - 1) yield
        err(j) * weights(j)(i)).foldLeft(0D)(_ + _)).toArray;

  def dF(inp: Array[Double]): Array[Double] =
    inp.map(x => dAFuncDx(x));


  /**
   * Создание нового слоя с откорректированной матрицей весов
   */
  def correctWeights(err: Array[Double],
                     prevOutput: Array[Double],
                     teachingCoeff: Double): Layer =
    new Layer(
      correctWeightsMatrix(err, prevOutput, teachingCoeff),
      correctShift(err, teachingCoeff),
      aFunc, dAFuncDx);

  /**
   * Корректировка вектора смещения
   */
  protected def correctShift(err: Array[Double], teachingCoeff: Double): Array[Double] =
    Math.sumV(shift, err.map(e => e * teachingCoeff));

  /**
   * Корректировка весов слоя
   */
  protected def correctWeightsMatrix(err: Array[Double], prevOutput: Array[Double],
                                     teachingCoeff: Double): Array[Array[Double]] =
    (for (j <- 0 to weights.length - 1) yield
      ((for (i <- 0 to weights(j).length - 1) yield
        weights(j)(i) + err(j) * prevOutput(i) * teachingCoeff).toArray)).toArray;

  def reset(): Layer = new Layer(weights.length, weights(0).length, aFunc, dAFuncDx);

  /**
   * Строковое представление для отладки
   */
  override def toString = "Layer\n " + weights(0).length +
    " inputs\n" + weights.length + " outputs\n" +
    "weights: " + VectorUtils.printMatrixT(weights) +
    "\nshift: " + VectorUtils.printVector(shift) + "\n";
}
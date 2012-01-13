package ru.ahomyakov.neuro.perseptron

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import util.Random
import ru.ahomyakov.neuro.VectorUtils
import ru.ahomyakov.neuro.base.VectorFunction

class Layer(weights: Matrix,
            shift: Array[Double],
            aFunc: VectorFunction) {
  /**
   * Инициализация слоя из сохранённого в б/д json-объекта
   */
  def this(o: DBObject, aFunc: VectorFunction) =
    this (new Matrix(o.get("matrix").toString),
      o.get("shift").toString.split(":").
        map(element => java.lang.Double.parseDouble(element)),
      aFunc);

  /**
   * Инициализация слоя указанной размерности случайными весами
   */
  def this(inputs: Int,
           outputs: Int,
           aFunc: VectorFunction) =
    this (new Matrix(inputs, outputs),
      (for (j <- 0 to outputs - 1) yield
        Random.nextDouble() % 1D).toArray,
      aFunc);

  /**
   * Объект для сохранения в mongo
   */
  def toDatabaseObject: DBObject =
    MongoDBObject("matrix" -> weights.serialize(),
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
    Math.sumV(weights.mapT(x => Math.multiplyVScalar(input, x.toArray)).toArray, shift);

  /**
   * Применить ф-цию активности
   */
  def applyFunction(input: Array[Double]): Array[Double] = aFunc.f(input);


  /**
   * Обратное распространение ошибки через матрицу весов
   */
  def errorBackTrace(err: Array[Double]): Array[Double] =
    weights.map(row => Math.multiplyVScalar(err, row.toArray)).toArray;

  /**
   * Производная от ф-ции активности
   */
  def dF(input: Array[Double]): Array[Double] = aFunc.df(input);


  /**
   * Создание нового слоя с откорректированной матрицей весов
   */
  def correctWeights(err: Array[Double],
                     prevOutput: Array[Double],
                     teachingCoeff: Double): Layer =
    new Layer(
      correctWeightsMatrix(err, prevOutput, teachingCoeff),
      correctShift(err, teachingCoeff),
      aFunc);

  /**
   * Корректировка вектора смещения
   */
  protected def correctShift(err: Array[Double], teachingCoeff: Double): Array[Double] =
    Math.sumV(shift, err.map(e => e * teachingCoeff));

  /**
   * Корректировка весов слоя
   */
  protected def correctWeightsMatrix(err: Array[Double], prevOutput: Array[Double],
                                     teachingCoeff: Double): Matrix =
    weights.sum(prevOutput(_) * err(_) * teachingCoeff);


  def reset(): Layer = new Layer(weights.height(), weights.width(), aFunc);

  /**
   * Строковое представление для отладки
   */
  override def toString = "Layer\n " + weights.width() +
    " inputs\n" + weights.height() + " outputs\n" +
    "weights:\n" + weights.toString +
    "\nshift: " + VectorUtils.printVector(shift) + "\n";
}
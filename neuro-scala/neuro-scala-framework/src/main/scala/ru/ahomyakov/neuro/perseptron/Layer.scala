package ru.ahomyakov.neuro.perseptron

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject
import util.Random
import ru.ahomyakov.neuro.base.VectorFunction

class Layer(weights: Matrix,
            shift: Seq[Double],
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
  def apply(input: Seq[Double]): Seq[Double] =
    applyFunction(applyWeightsAndShift(input));

  /**
   * Применить матрицу весов и вектор сдвига
   */
  def applyWeightsAndShift(input: Seq[Double]): Seq[Double] =
    Math.sumV(weights.mapT(x => Math.multiplyVScalar(input, x.toArray)), shift);

  /**
   * Применить ф-цию активности
   */
  def applyFunction(input: Seq[Double]): Seq[Double] = aFunc.f(input.toArray).toList;


  /**
   * Обратное распространение ошибки через матрицу весов
   */
  def errorBackTrace(err: Seq[Double]): Seq[Double] =
    weights.map(row => Math.multiplyVScalar(err, row.toArray));

  /**
   * Производная от ф-ции активности
   */
  def dF(input: Seq[Double]): Seq[Double] = aFunc.df(input.toArray).toSeq;


  /**
   * Создание нового слоя с откорректированной матрицей весов
   */
  def correctWeights(err: Seq[Double],
                     prevOutput: Seq[Double],
                     teachingCoeff: Double): Layer =
    new Layer(
      correctWeightsMatrix(err, prevOutput, teachingCoeff),
      correctShift(err, teachingCoeff),
      aFunc);

  /**
   * Корректировка вектора смещения
   */
  protected def correctShift(err: Seq[Double], teachingCoeff: Double): Seq[Double] =
    Math.sumV(shift, err.map(e => e * teachingCoeff));

  /**
   * Корректировка весов слоя
   */
  protected def correctWeightsMatrix(err: Seq[Double], prevOutput: Seq[Double],
                                     teachingCoeff: Double): Matrix =
    weights.sum(prevOutput(_) * err(_) * teachingCoeff);


  def reset(): Layer = new Layer(weights.height(), weights.width(), aFunc);

  /**
   * Строковое представление для отладки
   */
  override def toString = "Layer\n " + weights.width() +
    " inputs\n" + weights.height() + " outputs\n" +
    "weights:\n" + weights.toString +
    "\nshift: " + printVector(shift) + "\n";

  protected def printVector(v: Seq[Double]) =
    v.foldLeft("")((str, el) => (if (str.isEmpty) el.toString else str + "\t" + el))
}
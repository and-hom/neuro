package ru.ahomyakov.neuro.perseptron

import scala.Array
import ru.ahomyakov.neuro.data.Dao
import ru.ahomyakov.neuro.base.SigmaFunction

class Perseptron(layers: List[Layer])
  extends ru.ahomyakov.neuro.base.NeuroNet {
  /**
   * Зачитываем персептрон из хранилища
   */
  def this(dao: Dao, id: String) =
    this (dao.findById(id).map(o => new Layer(o, new SigmaFunction())).toList);

  override def reset() = new Perseptron(
    layers.map(layer => layer.reset()));

  override def process(input: Array[Double]): Array[Double] =
    layers.foldLeft(input)(
      (vect: Array[Double], layer: Layer) => layer.apply(vect));

  /**
   * Сохранить персептрон в базу
   */
  def store(id: String, dao: Dao) {
    dao.store(layers.map(l => l.toDatabaseObject).toList);
  };

  /**
   * Обратное распространение ошибки, Прямой и
   * обратный ход при помощи системного стека
   */
  override def teach(input: Array[Double],
                     requiredOutput: Array[Double],
                     teachingCoeff: Double): Perseptron =
    new Perseptron(correctLayers(layers, input,
      errs(input, requiredOutput, layers),
      teachingCoeff));

  /**
   * Процедура исправления весов и получения нового списка слоёв.
   */
  protected def correctLayers(layers: List[Layer],
                              input: Array[Double],
                              errs: Seq[Array[Double]],
                              teachingCoeff: Double): List[Layer] =
    if (layers.size == 0) List()
    else layers.head.correctWeights(errs.head, input, teachingCoeff) ::
      correctLayers(layers.tail, layers.head.apply(input), errs.tail, teachingCoeff);

  /**
   * <strong>Распространение ошибки по ещё не модифицированной сети.</strong><br/>
   * Используем системный стек (рекурсия).<br/>
   * Если мы на последнем слое, считаем ошибку последнего слоя и заносим в список.<br/>
   * Если ещё есть непройденные слои, считаем хвост списка ошибок далее. Затем
   * в голову списка ошибок добавляем результат обратного распространения
   * для текущего слоя. То есть, берём голову полученного списка ошибок,
   * прогоняем через слой назад и добавляем 1-м элементом. <br/>
   * При подсчёте ошибок всех слоёв, кроме последнего
   */
  protected def errs(input: Array[Double],
                     requiredOutput: Array[Double],
                     layers: List[Layer]): List[Array[Double]] =
    if (layers.size == 1) List(calculateErrorLast(input, requiredOutput, layers.head))
    else errsAdd(layers.head, layers.tail.head, input,
      errs(layers.head.apply(input), requiredOutput, layers.tail));

  protected def errsAdd(layer: Layer, nextLayer: Layer, input: Array[Double],
                        errs: List[Array[Double]]): List[Array[Double]] =
    calculateErrorInternal(input, nextLayer.errorBackTrace(errs.head), layer).toArray :: errs;

  /**
   * Вычисление ошибки для последнего слоя
   */
  protected def calculateErrorLast(input: Array[Double],
                                   requiredOutput: Array[Double],
                                   layer: Layer): Array[Double] =
    calculateErrorLast1(layer.applyWeightsAndShift(input), requiredOutput, layer);

  protected def calculateErrorLast1(weightedInput: Array[Double],
                                    requiredOutput: Array[Double],
                                    layer: Layer): Array[Double] =
    Math.multiplyV(Math.substituteV(requiredOutput, layer.applyFunction(weightedInput)),
      layer.dF(weightedInput));


  protected def calculateErrorInternal(input: Array[Double],
                                       err: Array[Double],
                                       layer: Layer): Array[Double] =
    Math.multiplyV(err, layer.dF(layer.applyWeightsAndShift(input)));


  override def toString = "Neuro network \n" + layers.size + " layers\n" +
    (layers.foldLeft(StringBuilder.newBuilder)((s, l) => s.append(l.toString))).toString()
}
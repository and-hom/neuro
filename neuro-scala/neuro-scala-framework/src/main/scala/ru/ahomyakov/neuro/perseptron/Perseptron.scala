package ru.ahomyakov.neuro.perseptron

import ru.ahomyakov.neuro.data.MongoDao
import scala.Array

class Perseptron(dao: MongoDao, layers: List[Layer])
  extends ru.ahomyakov.neuro.Perseptron {
  /**
   * Зачитываем персептрон из хранилища
   */
  def this(dao: MongoDao, id: String) =
    this (dao, dao.findById(id).map(o =>
      new Layer(o, x => AFunctions.sigma(x), x => AFunctions.dSigma(x))
    ));

  /**
   * f2(f1(input*A_1)*A_2)....
   */
  def process(input: Array[Double]): Array[Double] =
    layers.foldLeft(input)(
      (vect: Array[Double], layer: Layer) => layer.apply(vect));


  def store(id: String) {
    dao.store(layers.map(l => l.toDatabaseObject).toList);
  };

  /**
   * Обратное распространение ошибки, Прямой и
   * обратный ход при помощи системного стека
   */
  def errorBackTrace(input: Array[Double],
                     requiredOutput: Array[Double],
                     teachingCoeff: Double): Perseptron =
    new Perseptron(dao,
      mapLayers(layers,
        errs(input, requiredOutput, layers, teachingCoeff).tail));

  protected def mapLayers(layers: List[Layer], errs: Seq[Array[Double]]): List[Layer] =
    if (layers.size == 0) List()
    else layers.head.correctWeights(errs.head) :: mapLayers(layers.tail, errs.tail);

  /**
   * <strong>Распространение ошибки по ещё не модифицированной сети.</strong><br/>
   * Используем системный стек (рекурсия).<br/>
   * Если мы прошли последний слой и осталось 0 слоёв, то input - это выход
   * последнего слоя и в данном случае - выход сети. то есть
   * (input-requiredOutput) и будет ошибка.<br/>
   * Если ещё есть непройденные слои, считаем хвост списка ошибок далее. Затем
   * в голову списка ошибок добавляем результат обратного распространения
   * для текущего слоя. То есть, берём голову полученного списка ошибок,
   * прогоняем через слой назад и добавляем 1-м элементом.
   */
  protected def errs(input: Array[Double],
                     requiredOutput: Array[Double],
                     layers: List[Layer],
                     teachingCoeff: Double): List[Array[Double]] =
    if (layers.size == 0) List(calculateError(input, requiredOutput, teachingCoeff))
    else errsAdd(layers.head, layers.head.apply(input),
      errs(layers.head.apply(input), requiredOutput, layers.tail, teachingCoeff));

  protected def errsAdd(layer: Layer, output: Array[Double],
                        errs: List[Array[Double]]): List[Array[Double]] =
    layer.errorBackTrace(errs.head, output).toArray :: errs;

  /**
   * Вычисление ошибки для последнего слоя с учётом выхода.
   * То есть, ошибка между слоем нейронов и связями
   */
  protected def calculateError(requiredOutput: Array[Double],
                               realOutput: Array[Double],
                               teachingCoeff: Double): Array[Double] =
    (for (i <- 0 to requiredOutput.length - 1) yield
      (requiredOutput(i) - realOutput(i)) *
        requiredOutput(i) * (1 - requiredOutput(i)) *
        teachingCoeff).toArray;

  override def toString = "Neuro network \n" + layers.size + " layers\n" +
    (layers.foldLeft(StringBuilder.newBuilder)((s, l) => s.append(l.toString))).toString()
}
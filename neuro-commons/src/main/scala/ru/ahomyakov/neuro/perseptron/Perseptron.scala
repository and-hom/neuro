package ru.ahomyakov.neuro.perseptron

import ru.ahomyakov.neuro.data.MongoDao

class Perseptron(dao: MongoDao, layers: Array[Layer]) {
  /**
   * Зачитываем персептрон из хранилища
   */
  def this(dao: MongoDao, id: String) =
    this (dao, dao.findById(id).map(o =>
      new Layer(o, x => AFunctions.sigma(x), x => AFunctions.dSigma(x))
    ).toArray);

  /**
   * Инициализация персептрона слоями заданной размерности со случайными весами.
   * Размерность задана как {{вх1, вых1}, {вх2, вых2},...}
   * Контроля размерности нет
   */
  def this(dao: MongoDao, dimensions: Array[Array[Int]]) = this (dao,
    dimensions.map(dim => new Layer(dim(0), dim(1),
      x => AFunctions.sigma(x), x => AFunctions.dSigma(x))));

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
    new Perseptron(dao, errorBackTrace(input, requiredOutput, layers.toList).toArray);

  /**
   * Предварительно считаем все вектора ошибок
   */
  protected def errs(err: Array[Double],
                     teachingCoeff: Double,
                     layersReversed: List[Layer]):
  List[Array[Double]] =
    layers.foldRight(List(): List[Array[Double]])(
      (layer: Layer, list: List[Array[Double]]) =>
        ((if (list.length == 0) err else list(0)) :: list));


  protected def err(requiredOutput: Array[Double],
                    realOutput: Array[Double],
                    teachingCoeff: Double): Seq[Double] =
    for (i <- 0 to requiredOutput.length - 1) yield
      (requiredOutput(i) - realOutput(i)) *
        requiredOutput(i) * (1 - requiredOutput(i)) *
        teachingCoeff;

  protected def errorBackTrace(input: Array[Double],
                               requiredOutput: Array[Double],
                               layers: List[Layer]): List[Layer] =
    layers match {
      case Nil => Nil;
      case head :: Nil => head.correctWeights(err(requiredOutput, head.apply(input), head));
      case head :: tail => errorBackTrace(head.apply(input), requiredOutput, tail);
    }

  /**
   * Прогоняем ошибку через последний слой
   */
  protected def err(input: Array[Double],
                    error: Array[Double],
                    layer: Layer): Array[Double] {

  }
}
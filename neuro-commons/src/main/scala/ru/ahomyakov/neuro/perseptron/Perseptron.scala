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
   * Обратное распространение ошибки
   */
  def errorBackTrace(requiredOutput: Array[Double],
                     realOutput: Array[Double],
                     teachingCoeff: Double): Perseptron =
    new Perseptron(dao, errorBackTrace(
      err(requiredOutput, realOutput, teachingCoeff),
      layers.toList.reverse).reverse.toArray);

  protected def err(requiredOutput: Array[Double],
                    realOutput: Array[Double],
                    teachingCoeff: Double): Seq[Double] =
    for (i <- 0 to requiredOutput.length - 1) yield
      (requiredOutput(i) - realOutput(i)) *
        requiredOutput(i) * (1 - requiredOutput(i)) *
        teachingCoeff;

  protected def errorBackTrace(err: Seq[Double],
                               layers: List[Layer]): List[Layer] =
    layers match {
      case Nil => Nil;
      case head :: tail => head.correctWeights(err) :: errorBackTrace(head.errorBackTrace(err), tail);
    }
}
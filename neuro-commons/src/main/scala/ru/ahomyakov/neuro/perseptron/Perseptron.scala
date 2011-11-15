package ru.ahomyakov.neuro.perseptron

import ru.ahomyakov.neuro.data.MongoDao

class Perseptron(dao: MongoDao, layers: Array[Layer]) {
  /**
   * Зачитываем персептрон из хранилища
   */
  def this(dao: MongoDao, id: String) =
    this (dao, dao.findById(id).map(o => new Layer(o, (x => x): (Double => Double))).toArray);

  /**
   * Инициализация персептрона слоями заданной размерности со случайными весами.
   * Размерность задана как {{вх1, вых1}, {вх2, вых2},...}
   * Контроля размерности нет
   */
  def this(dao: MongoDao, dimensions: Array[Array[Int]]) = this (dao,
    dimensions.map(dim => new Layer(dim(0), dim(1),
      (x => x): (Double => Double))));

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
  //  protected def errorBackTrace(requiredOutput: Array[Double],
  //                               realOutput: Array[Double],
  //                               teachingCoeff: Double): Perseptron =
  //    layers.foldRight(new Perseptron(dao, Array[Layer]))(
  //      (l: Layer, p: Perseptron) => (p.addLayerToBegin(l.errorBackTrace())));

  /**
   * сделать из персептрона новый с добавлением слоя в начало.
   * используется при обратном распространении ошибки
   */
//  def addLayerToBegin(layer: Layer): Perseptron =
//    new Perseptron(dao, layer :: layers);
}
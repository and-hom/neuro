package ru.ahomyakov.neuro.perseptron

import ru.ahomyakov.neuro.data.MongoDao

class Perseptron(dao: MongoDao, layers: Array[Layer]) {
  /**
   * Зачитываем персептрон из хранилища
   */
  def this(dao: MongoDao, id: String) =
    this (dao, dao.findById(id).map(o => new Layer(o, (x => x): (Double => Double))).toArray);

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
  def addLayerToBegin(layer: Layer): Perseptron =
    new Perseptron(dao, layer :: layers);
}
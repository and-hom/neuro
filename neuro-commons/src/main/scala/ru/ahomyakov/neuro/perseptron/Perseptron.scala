package ru.ahomyakov.neuro.perseptron

import ru.ahomyakov.neuro.data.MongoDao

class Perseptron(dao: MongoDao, layers: Layer*) {
  /**
   * Зачитываем персептрон из хранилища
   */
  def this(dao: MongoDao, id: String) =
    this (dao, dao.findById(id).map(o => new Layer(o, x => x)));

  /**
   * f2(f1(input*A_1)*A_2)....
   */
  def process(input: Array[Double],
              layers: Array[Layer]): Array[Double] =
    layers.foldLeft(input)(
      (vect: Array[Double], layer: Layer) => layer.apply(vect));

  def process(input: Array[Double], id: String): Array[Double] =
    process(input, load(id));


  def store(id: String) {
    dao.store(layers.map(l => l.toDatabaseObject));
  };
}
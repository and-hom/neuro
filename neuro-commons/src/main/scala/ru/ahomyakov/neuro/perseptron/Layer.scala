package ru.ahomyakov.neuro.perseptron

import com.mongodb.DBObject
import com.mongodb.casbah.commons.MongoDBObject

class Layer(weights: Array[Array[Double]],
            aFunc: Double => Double) {
  def this(o: DBObject, aFunc: Double => Double) =
    this (o.get("matrix").toString.split("::").
      map(col => col.split(":").
      map(element => java.lang.Double.parseDouble(element))), aFunc);

  /**
   * Объект для сохранения в mongo
   */
  def toDatabaseObject: DBObject =
    MongoDBObject("matrix" -> weights.foldLeft("")
      ((a, b) => (a + "::" + b.foldLeft("")
        ((c, d) => (c + ":" + d)))));


  /**
   * Выполнить преобразование слоя
   */
  def apply(input: Array[Double]): Array[Double] =
    weights.map(x => multiplyV(input, x));

  protected def multiplyV(rowVector: Array[Double],
                          colVector: Array[Double]): Double =
    (for (i: Int <- 0 to rowVector.length - 1)
    yield rowVector(i) * colVector(i)).
      foldLeft(0D)(_ + _);
}
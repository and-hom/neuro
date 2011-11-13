package ru.ahomyakov.neuro.perseptron

import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.{DBCursor, DBObject}

object Perseptron {
  /**
   * f2(f1(input*A_1)*A_2)....
   */
//  def process(input: Array[Double],
//              layers: Array[Layer]): Array[Double] =
//    layers.foldLeft(input)((vect: Array[Double], layer: Layer) =>
//      Matrix.multiply(vect, layer.weights).map(x => layer.aFunc(x)))
//
//  def process(input: Array[Double], id: String): Array[Double] =
//    process(input, load(id));
//
//  def load(id: String): Array[Layer] =
//    dbObjectsToLayers(MongoConnection("127.0.0.1").
//      getDB("neuro").getCollection("neuro").
//      find(MongoDBObject("id" -> id)));
//
//  def dbObjectsToLayers(cursor: DBCursor): Array[Layer] =
//  (for {x <- cursor} yield Layer(dbObjectToMatrix(ob), x => x));
//
//  def dbObjectToMatrix(ob: DBObject): Array[Array[Double]] =
//    ob.get("matrix").toString.split("::").
//      map(col => col.split(":").
//      map(element => java.lang.Double.parseDouble(element)));
//
//
//  def layersToDbObjects(layers: Array[Layer]): Array[DBObject] =
//    layers.map(ob => MongoDBObject(matrixToDbObject(ob.weights)));
//
//  def matrixToDbObject(m: Array[Array[Double]]): DBObject =
//    MongoDBObject("matrix" -> m.foldLeft("")
//      ((a, b) => (a + "::" + b.foldLeft("")
//        ((c, d) => (c + ":" + d)))));
//
//  def store(id: String, layers: Array[Layer]) {
//    printf("saved!")
//  };
}
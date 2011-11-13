package ru.ahomyakov.neuro.data

import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObject
import collection.JavaConverters
import com.mongodb.{WriteResult, DBObject}
import java.util.ArrayList
import collection.mutable.Buffer


class MongoDao(host: String, database: String, collection: String) {
  def conn = MongoConnection(host).getDB(database).getCollection(collection);

  def findById(id: String): Buffer[DBObject] =
    JavaConverters.asScalaBufferConverter(
      conn.find(MongoDBObject("id" -> id)).toArray).asScala;

  def store(obs: Iterable[DBObject]): WriteResult =
    conn.insert(new ArrayList(JavaConverters.asJavaCollectionConverter(obs).asJavaCollection));
}
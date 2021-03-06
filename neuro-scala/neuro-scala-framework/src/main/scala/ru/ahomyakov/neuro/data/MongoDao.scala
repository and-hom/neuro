package ru.ahomyakov.neuro.data

import com.mongodb.casbah.MongoConnection
import com.mongodb.casbah.commons.MongoDBObject
import collection.JavaConverters
import com.mongodb.{WriteResult, DBObject}
import java.util.ArrayList


class MongoDao(host: String, database: String, collection: String) extends Dao {
  def conn = MongoConnection(host).getDB(database).getCollection(collection);

  override def findById(id: String): List[DBObject] =
    JavaConverters.asScalaBufferConverter(
      conn.find(MongoDBObject("id" -> id)).toArray).asScala.toList;

  override def store(obs: List[DBObject]): WriteResult = multipleDBExec(
    conn.remove(MongoDBObject("id" -> obs(0).get("id"))),
    conn.insert(new ArrayList(JavaConverters.asJavaCollectionConverter(obs).asJavaCollection)));

  protected def multipleDBExec(results: WriteResult*): WriteResult =
    results(0);
}
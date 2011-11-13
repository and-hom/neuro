package ru.ahomyakov.neuro

import data.MongoDao
import com.mongodb.casbah.commons.MongoDBObject


object Main {
  def main(args: Array[String]) {
    println("Hello, World!")
    var dao = new MongoDao("127.0.0.1", "neuro", "neuro");
    dao.store(List(MongoDBObject("id" -> "a", "value" -> "b")));
    var found=dao.findById("a");
    found.foreach(x => println(x));
  }
}



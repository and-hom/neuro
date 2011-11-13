package ru.ahomyakov.neuro.data

import collection.mutable.Buffer
import com.mongodb.{WriteResult, DBObject}

abstract class Dao {
  def findById(id: String): Buffer[DBObject];

  def store(obs: List[DBObject]): WriteResult;
}
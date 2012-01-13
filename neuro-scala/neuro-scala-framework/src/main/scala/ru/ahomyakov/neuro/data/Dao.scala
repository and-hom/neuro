package ru.ahomyakov.neuro.data

import com.mongodb.{WriteResult, DBObject}

trait  Dao {
  def findById(id: String): Iterable[DBObject];

  def store(obs: List[DBObject]): WriteResult;
}
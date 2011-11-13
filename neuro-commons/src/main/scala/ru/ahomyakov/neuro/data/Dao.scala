package ru.ahomyakov.neuro.data

import collection.mutable.Buffer
import com.mongodb.{WriteResult, DBObject}

abstract class Dao {
  abstract def findById(id: String): Buffer[DBObject];

  abstract def store(obs: List[DBObject]): WriteResult;
}
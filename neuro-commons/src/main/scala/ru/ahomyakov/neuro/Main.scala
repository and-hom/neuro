package ru.ahomyakov.neuro

import data.MongoDao
import perseptron.{Layer, Perseptron}


object Main {
  def main(args: Array[String]) {
    var perseptron = new Perseptron(
      new MongoDao("127.0.0.1", "neuro", "neuro"),
      Array(new Layer(Array(Array(1D, 1D)),
        (x => if (x > 1) 1 else 0): (Double => Double))));

    println(perseptron.
      process(Array(1D, 1D)).foldLeft("")(_ + ";" + _));

    perseptron.store("p1");
  }
}



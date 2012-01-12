package ru.ahomyakov.neuro

import data.MongoDao
import perseptron.{AFunctions, Layer, Perseptron}


object PerseptronHelper {
  def create(): Perseptron = new Perseptron(
    new MongoDao("127.0.0.1", "neuro", "neuro"),
    List(
      new Layer(2, 4, AFunctions.sigma _, AFunctions.dSigma _),
      new Layer(4, 1, AFunctions.step _, AFunctions.dStep _))
  );
}
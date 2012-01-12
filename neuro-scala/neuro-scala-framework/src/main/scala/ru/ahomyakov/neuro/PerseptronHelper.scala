package ru.ahomyakov.neuro

import perseptron.{AFunctions, Layer, Perseptron}


object PerseptronHelper {
  def create(): Perseptron = new Perseptron(
    List(
      new Layer(2, 4, AFunctions.sigma _, AFunctions.dSigma _),
      new Layer(4, 1, AFunctions.step _, AFunctions.dStep _))
  );
}
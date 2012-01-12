package ru.ahomyakov.neuro

import base.{BarierFunction, SigmaFunction}
import perseptron.{Layer, Perseptron}


object PerseptronHelper {
  def create(): Perseptron = new Perseptron(
    List(
      new Layer(2, 4, new SigmaFunction()),
      new Layer(4, 1, new BarierFunction()))
  );
}
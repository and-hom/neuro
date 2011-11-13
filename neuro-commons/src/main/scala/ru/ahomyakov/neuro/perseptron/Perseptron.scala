package ru.ahomyakov.neuro.perseptron

object Perseptron {
  /**
   * f2(f1(input*A_1)*A_2)....
   */
  def process(input: Array[Double],
              layers: Array[Layer]): Array[Double] =
  layers.foldLeft(input)((vect:Array[Double],layer:Layer)=>
    Matrix.multiply(vect,layer.weights).map(x=>layer.aFunc(x)))


}
package ru.ahomyakov.neuro

import perseptron.Matrix

object Main {
  def main(args: Array[String]) {
    println("Hello, World!")
    var v = Array(1D, 2D, 3D)
    var m = Array(Array(1D, 2D, 3D), Array(4D, 5D, 6D))
    println(Matrix.multiply(v, m))
  }
}



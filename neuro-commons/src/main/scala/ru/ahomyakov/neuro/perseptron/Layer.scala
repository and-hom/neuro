package ru.ahomyakov.neuro.perseptron

class Layer(w: Array[Array[Double]],
            f: Double => Double) {
  /**
   * Транспанированная матрица весов
   */
  def weights: Array[Array[Double]] = w;

  /**
   * Вектор функций активности
   */
  def aFunc: Double => Double = f;
}
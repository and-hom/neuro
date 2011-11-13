package ru.ahomyakov.neuro.perseptron


object Matrix {
  /**
   * 1-й индекс в матрице - номер столбца
   */
  def multiply(input: Array[Double],
               matrix: Array[Array[Double]]):
  Array[Double] =
    matrix.map(x => multiplyV(input, x));

  def multiplyV(rowVector: Array[Double],
                colVector: Array[Double]): Double =
    (for (i: Int <- 0 to rowVector.length)
    yield rowVector(i) * colVector(i)).
      foldLeft(0D)(_ + _);

}
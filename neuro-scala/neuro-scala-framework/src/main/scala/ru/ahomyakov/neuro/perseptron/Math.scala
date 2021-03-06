package ru.ahomyakov.neuro.perseptron


object Math {
  def sumV(v1: Array[Double], v2: Array[Double]): Array[Double] =
    (for (i <- 0 to v1.length - 1) yield v1(i) + v2(i)).toArray;

  def sumV(v1: Seq[Double], v2: Seq[Double]): Seq[Double] =
    (for (i <- 0 to v1.length - 1) yield v1(i) + v2(i));

  def substituteV(v1: Array[Double], v2: Array[Double]): Array[Double] =
    (for (i <- 0 to v1.length - 1) yield v1(i) - v2(i)).toArray;

  def substituteV(v1: Seq[Double], v2: Seq[Double]): Seq[Double] =
    (for (i <- 0 to v1.length - 1) yield v1(i) - v2(i));

  /**
   * Покомпонентно перемножить векторы
   */
  def multiplyV(v1: Array[Double], v2: Array[Double]): Array[Double] =
    (for (i <- 0 to v1.length - 1) yield v1(i) * v2(i)).toArray;
  /**
   * Покомпонентно перемножить векторы
   */
  def multiplyV(v1: Seq[Double], v2: Seq[Double]): Seq[Double] =
    (for (i <- 0 to v1.length - 1) yield v1(i) * v2(i)).toSeq;

  /**
   * Скалярное произведение векторов
   */
  def multiplyVScalar(rowVector: Array[Double],
                      colVector: Array[Double]): Double =
    (for (i: Int <- 0 to rowVector.length - 1) yield
      rowVector(i) * colVector(i)).foldLeft(0D)(_ + _);
  /**
   * Скалярное произведение векторов
   */
  def multiplyVScalar(rowVector: Seq[Double],
                      colVector: Seq[Double]): Double =
    (for (i: Int <- 0 to rowVector.length - 1) yield
      rowVector(i) * colVector(i)).foldLeft(0D)(_ + _);
}
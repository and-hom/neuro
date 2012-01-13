package ru.ahomyakov.neuro.perseptron

import util.Random


class Matrix(rows: Seq[Seq[Double]],
             cols: Seq[Seq[Double]]) {

  def this(rows: Seq[Seq[Double]]) = this (rows,
    for (j <- 0 to rows(0).length - 1) yield
      for (i <- 0 to rows.length - 1) yield
        rows(i)(j));

  def this(rows: Int, cols: Int) = this (
    for (i <- 0 to rows - 1) yield
      for (j <- 0 to cols - 1) yield
        Random.nextDouble() % 1D);

  def this(serialized: String) = this (serialized.split("::").
    map(col => col.split(":").
    map(element => java.lang.Double.parseDouble(element)).toSeq).toSeq);

  def serialize(): String = rows.foldLeft("")((str1, row) =>
    (str1 + (if (str1.isEmpty) "" else "::") + row.foldLeft("")
      ((str2, cell) => (str2 + (if (str2.isEmpty) "" else ":") + cell))));

  def map[T](f: (Seq[Double] => T)): Seq[T] = rows.map(f);

  def mapT[T](f: (Seq[Double] => T)): Seq[T] = cols.map(f);

  def get(i: Int, j: Int) = rows(i)(j);

  def width(): Int = cols.length;

  def height(): Int = cols.length;

  def sum(m: Matrix): Matrix = new Matrix(
    for (i <- 0 to rows.length - 1) yield
      for (j <- 0 to rows(i).length - 1) yield
        m.get(i, j) + rows(i)(j));

  def sum(f: ((Int), Int) => Double): Matrix = new Matrix(
    for (i <- 0 to rows.length - 1) yield
      for (j <- 0 to rows(i).length - 1) yield
        f(i, j) + rows(i)(j));

  override def toString: String = rows.foldLeft("")((str1, row) =>
    (str1 + (if (str1.isEmpty) "" else "\n") + row.foldLeft("")
      ((str2, cell) => (str2 + (if (str2.isEmpty) "" else "\t") + cell))));
}
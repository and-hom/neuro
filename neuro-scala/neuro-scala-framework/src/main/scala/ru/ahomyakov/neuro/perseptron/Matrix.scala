package ru.ahomyakov.neuro.perseptron

import util.Random


class Matrix(rows: Array[Array[Double]],
             cols: Array[Array[Double]]) {

  def this(rows: Array[Array[Double]]) = this (rows,
    (for (j <- 0 to rows(0).length - 1) yield
      (for (i <- 0 to rows.length - 1) yield
        rows(i)(j)).toArray).toArray);

  def this(rows: Int, cols: Int) = this ((
    for (i <- 0 to rows - 1) yield
      (for (j <- 0 to cols - 1) yield
        Random.nextDouble() % 1D).toArray).toArray);

  def this(serialized: String) = this (serialized.split("::").
    map(col => col.split(":").
    map(element => java.lang.Double.parseDouble(element))));

  def serialize(): String = rows.foldLeft("")((str1, row) =>
    (str1 + (if (str1.isEmpty) "" else "::") + row.foldLeft("")
      ((str2, cell) => (str2 + (if (str2.isEmpty) "" else ":") + cell))));

  def map[T](f: (Array[Double] => T)): T = rows.map(f);

  def mapT[T](f: (Array[Double] => T)): T = cols.map(f);

  def get(i: Int, j: Int) = rows(i)(j);

  def width(): Int = cols.length;

  def height(): Int = cols.length;
}
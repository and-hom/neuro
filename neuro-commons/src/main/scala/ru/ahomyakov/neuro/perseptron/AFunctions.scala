package ru.ahomyakov.neuro.perseptron


object AFunctions {
  def sigma(x: Double): Double = 1 / (1 + scala.math.exp(-x));

  def dSigma(x: Double): Double = x * (1 - x);

  def step(x: Double): Double = if (x > 0) 1 else 0;

  def dStep(x: Double): Double = if (x == 0) 1 else 0;
}
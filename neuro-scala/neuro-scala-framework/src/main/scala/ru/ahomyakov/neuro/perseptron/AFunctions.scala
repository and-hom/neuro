package ru.ahomyakov.neuro.perseptron


object AFunctions {
  def sigma(x: Double): Double = 1 / (1 + scala.math.exp(-x));

  def dSigma(x: Double): Double = sigma(x) * (1 - sigma(x));

  def step(x: Double): Double = if (x > 0) 1 else 0;

  def dStep(x: Double): Double = dSigma(x);
}
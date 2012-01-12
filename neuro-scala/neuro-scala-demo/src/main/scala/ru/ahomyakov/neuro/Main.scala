package ru.ahomyakov.neuro

import data.MongoDao
import perseptron.impl.functions.{BarierFunction, SigmaFunction}
import perseptron.{AFunctions, Layer, Perseptron}
import java.util.Arrays
import ru.ahomyakov.neuro.perseptron.impl.{LayerImpl, NeuroNetImpl}


object Main {
  def main(args: Array[String]) {
    // инициализация персепторна случайными весами
    val weights1: Array[Array[Double]] = Array(Array(0.1, -0.2),
      Array(-0.1, 0.1),
      Array(0d, 0.1));
    val weights2: Array[Array[Double]] = Array(Array(0d, 0.1, -0.1));
    var scalaPerseptron = new Perseptron(
      new MongoDao("127.0.0.1", "neuro", "neuro"),
      List(
        new Layer(weights1, Array(0d, 0d, 0d), AFunctions.sigma _, AFunctions.dSigma _),
        new Layer(weights2, Array(0d), AFunctions.step _, AFunctions.dStep _))
    );


    var javaPerseptron = new NeuroNetImpl(Arrays.asList(
      new LayerImpl(Array(Array(0.1, -0.1, 0d), Array(-0.2, 0.1, 0.1)),
        Array(0d, 0d, 0d), new SigmaFunction()),
      new LayerImpl(Array(Array(0d), Array(0.1), Array(-0.1)), Array(0d),
        new BarierFunction())
    ));



    println("\nJava:\n" + javaPerseptron.toString);
    println("\nScala:\n" + scalaPerseptron.toString)
    println("========================================================")
    println("========================================================")
    println("========================================================")

    for (i <- 1 to 10000) {
      for (i <- 0 to 3) {
        for (j <- 0 to 3) {
          def in = Array(int2double(i), int2double(j));
          def out = if (i + j < 3 || i + j > 4) Array(1d) else Array(0d);
          javaPerseptron = javaPerseptron.teach(in, out, 0.3)
          scalaPerseptron = scalaPerseptron.teach(in, out, 0.3)

//          println("\nJava:\n" + javaPerseptron.toString);
//          println("\nScala:\n" + scalaPerseptron.toString)
        }
      }
    }


    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        def in = Array(int2double(i), int2double(j));
        def answer = if (i + j < 3 || i + j > 4) 1d else 0d;
        val etaOut: Double = javaPerseptron.process(in)(0)
        val realOut: Double = scalaPerseptron.process(in)(0)
        if (etaOut != realOut || etaOut != answer) {
          println("i=" + i + "; j=" + j);
          println("Answer\t" + answer);
          println("Java\t" + etaOut);
          println("Scala\t" + realOut)
        }
      }
    }

    println("\nJava:\n" + javaPerseptron.toString);
    println("\nScala:\n" + scalaPerseptron.toString)
    scalaPerseptron.store("p1");
  }

}



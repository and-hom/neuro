package ru.ahomyakov.neuro

import data.MongoDao
import perseptron.{AFunctions, Layer, Perseptron}
import java.util.Arrays
import ru.ahomyakov.neuro.perseptron.impl.{LayerImpl, NeuroNetImpl}
import ru.ahomyakov.neuro.perseptron.impl.functions.SigmaFunction


object Main {
  def main(args: Array[String]) {
    // инициализация персепторна случайными весами
    val weights1: Array[Array[Double]] = Array(Array(0.1, -0.2),
      Array(-0.1, 0.1),
      Array(0d, 0.1));
    val weights2: Array[Array[Double]] = Array(Array(0d, 0.1, -0.1));
    var perseptron = new Perseptron(
      new MongoDao("127.0.0.1", "neuro", "neuro"),
      List(
        new Layer(weights1, AFunctions.sigma _, AFunctions.dSigma _),
        new Layer(weights2, AFunctions.sigma _, AFunctions.dSigma _))
    );


    var net = new NeuroNetImpl(Arrays.asList(
      new LayerImpl(Array(Array(0.1, -0.1, 0d), Array(-0.2, 0.1, 0.1)),
        Array(0d, 0d, 0d), new SigmaFunction()),
      new LayerImpl(Array(Array(0d), Array(0.1), Array(-0.1)), Array(0d),
        new SigmaFunction())
    ));



    println("\nJava:\n" + net.toString);
    println("\nScala:\n" + perseptron.toString)
    println("========================================================")
    println("========================================================")
    println("========================================================")

    for (i <- 1 to 10000) {
      for (i <- 0 to 3) {
        for (j <- 0 to 3) {
          def in = Array(int2double(i), int2double(j));
          def out = if (i + j < 3 || i + j > 4) Array(1d) else Array(0d);
          net = net.errorBackTrace(in, out, 0.3)
          perseptron = perseptron.errorBackTrace(in, out, 0.3)

          println("\nJava:\n" + net.toString);
          println("\nScala:\n" + perseptron.toString)
        }
      }
    }


    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        def in = Array(int2double(i), int2double(j));
        def answer = if (i + j < 3 || i + j > 4) 1d else 0d;
        val etaOut: Double = net.process(in)(0)
        val realOut: Double = perseptron.process(in)(0)
        if (etaOut != realOut || etaOut != answer) {
          println("i=" + i + "; j=" + j);
          println("Answer\t" + answer);
          println("Java\t" + etaOut);
          println("Scala\t" + realOut)
        }
      }
    }

    println("\nJava:\n" + net.toString);
    println("\nScala:\n" + perseptron.toString)
    perseptron.store("p1");
  }

}



package ru.ahomyakov.neuro

import data.MongoDao
import perseptron.{AFunctions, Layer, Perseptron}
import java.util.Arrays
import ru.ahomyakov.neuro.perseptron.impl.{LayerImpl, NeuroNetImpl}
import ru.ahomyakov.neuro.perseptron.impl.functions.SigmaFunction


object Main {
  def main(args: Array[String]) {
    // инициализация персепторна случайными весами
    var perseptron = new Perseptron(
      new MongoDao("127.0.0.1", "neuro", "neuro"),
      List(
        new Layer(2, 3, AFunctions.sigma, AFunctions.dSigma),
        new Layer(3, 1, AFunctions.sigma, AFunctions.dSigma))
    );


    def net = new NeuroNetImpl(Arrays.asList(
      new LayerImpl(2, 3, new SigmaFunction()),
      new LayerImpl(3, 1, new SigmaFunction())
    ));



    for (i <- 1 to 10000) {
      for (i <- 0 to 3) {
        for (j <- 0 to 3) {
          def in = Array(int2double(i), int2double(j));
          def out = if (i + j < 3 || i + j > 4) Array(1d) else Array(0d);
          net.errorBackTrace(in, out, 0.3)
          perseptron = perseptron.errorBackTrace(in, out, 0.3)
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



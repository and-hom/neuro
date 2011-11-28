package ru.ahomyakov.neuro

import data.MongoDao
import perseptron.{AFunctions, Layer, Perseptron}
import java.util.Arrays
import ru.mai.neuro.perseptron.impl.{LayerImpl, NeuroNetImpl}
import ru.mai.neuro.perseptron.impl.functions.{BarierFunction, SigmaFunction}


object Main {
  def main(args: Array[String]) {
    // инициализация персепторна случайными весами
    var perseptron = new Perseptron(
      new MongoDao("127.0.0.1", "neuro", "neuro"),
      List(
        new Layer(2, 3, AFunctions.sigma, AFunctions.dSigma),
        new Layer(3, 1, AFunctions.step, AFunctions.dStep))
    );


    def net = new NeuroNetImpl(Arrays.asList(
      new LayerImpl(2, 3, new SigmaFunction()),
      new LayerImpl(3, 1, new BarierFunction())
    ));



    for (i <- 1 to 100) {
      for (i <- 0 to 3) {
        for (j <- 0 to 3) {
          def in = Array(int2double(i), int2double(j));
          def out = if (i + j < 3 || i + j > 4) Array(1d) else Array(0d);
          net.teach(in, out, 0.01)
          perseptron = perseptron.errorBackTrace(in, out, 0.01)
        }
      }
    }


    for (i <- 0 to 3) {
      for (j <- 0 to 3) {
        def in = Array(int2double(i), int2double(j));
        def answer = if (i + j < 3 || i + j > 4) 1d else 0d;
        val etaOut: Double = net.operate(in)(0)
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



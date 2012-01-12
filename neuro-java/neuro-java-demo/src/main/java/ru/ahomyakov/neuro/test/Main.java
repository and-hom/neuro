package ru.ahomyakov.neuro.test;

import ru.ahomyakov.neuro.perseptron.impl.LayerImpl;
import ru.ahomyakov.neuro.perseptron.impl.NeuroNetImpl;
import ru.ahomyakov.neuro.perseptron.impl.functions.BarierFunction;
import ru.ahomyakov.neuro.perseptron.interfaces.Layer;
import ru.ahomyakov.neuro.perseptron.interfaces.NeuroNet;

public class Main {
    public static void main(String[] args) {
        test2();

    }

    public static void test1() {
        double[][] m1 = new double[2][2];
        m1[0][0] = -1;
        m1[0][1] = -1;
        m1[1][0] = -1;
        m1[1][1] = -1;
        double[] v1 = new double[2];
        v1[0] = 1.5;
        v1[1] = 0.5;
        double[][] m2 = new double[2][1];
        m2[0][0] = 1;
        m2[1][0] = -1;
        double[] v2 = new double[1];
        v2[0] = -0.5;
        double[] test1 = new double[2];

        NeuroNet neuroNet = new NeuroNetImpl();
        Layer layer = new LayerImpl(m1, v1, new BarierFunction());
        neuroNet.addLayer(layer);
        layer = new LayerImpl(m2, v2, new BarierFunction());
        neuroNet.addLayer(layer);

        test1[0] = 0;
        test1[1] = 0;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 1;
        test1[1] = 0;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 0;
        test1[1] = 1;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 1;
        test1[1] = 1;
        System.out.println(neuroNet.process(test1)[0]);

        System.out.println(neuroNet);
    }

    public static void test2() {
        double[] test1 = new double[2];
        double[] no = new double[1];
        no[0] = 0;
        double[] yes = new double[1];
        yes[0] = 1;

        NeuroNet neuroNet = new NeuroNetImpl();
        Layer layer = new LayerImpl(2, 2, new BarierFunction());
        neuroNet.addLayer(layer);
        layer = new LayerImpl(2, 1, new BarierFunction());
        neuroNet.addLayer(layer);

        System.out.println(neuroNet);
        for (int i = 0; i < 1000; i++) {
            test1[0] = 0;
            test1[1] = 0;
            neuroNet.teach(test1, no, 0.3);
            test1[0] = 1;
            test1[1] = 0;
            neuroNet.teach(test1, yes, 0.3);
            test1[0] = 0;
            test1[1] = 1;
            neuroNet.teach(test1, yes, 0.3);
            test1[0] = 1;
            test1[1] = 1;
            neuroNet.teach(test1, no, 0.3);
        }

        System.out.println("--------------------------------------------");
        System.out.println(neuroNet);
        test1[0] = 0;
        test1[1] = 0;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 1;
        test1[1] = 0;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 0;
        test1[1] = 1;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 1;
        test1[1] = 1;
        System.out.println(neuroNet.process(test1)[0]);

    }


    public static void test3() {
        double[][] m = new double[2][1];
        double[] v = new double[1];
        m[0][0] = 0.2;
        m[1][0] = -0.1;
        v[0] = 0.3;

        double[] test1 = new double[2];
        double[] no = new double[1];
        no[0] = 0;
        double[] yes = new double[1];
        yes[0] = 1;

        NeuroNet neuroNet = new NeuroNetImpl();
        Layer layer = new LayerImpl(m, v, new BarierFunction());
        neuroNet.addLayer(layer);

        System.out.println(neuroNet);
        for (int i = 0; i < 10000; i++) {
            test1[0] = 1;
            test1[1] = 1;
            neuroNet.teach(test1, yes, 1);
            test1[0] = 0;
            test1[1] = 0;
            neuroNet.teach(test1, no, 1);
            test1[0] = 1;
            test1[1] = 0;
            neuroNet.teach(test1, yes, 1);
            test1[0] = 0;
            test1[1] = 1;
            neuroNet.teach(test1, yes, 1);
        }

        System.out.println("--------------------------------------------");
        System.out.println(neuroNet);
        test1[0] = 0;
        test1[1] = 0;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 1;
        test1[1] = 0;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 0;
        test1[1] = 1;
        System.out.println(neuroNet.process(test1)[0]);
        test1[0] = 1;
        test1[1] = 1;
        System.out.println(neuroNet.process(test1)[0]);

    }


    public static double[] testXor(double x, double y) {
        double[] result = new double[1];
        result[0] = xor((int) x, (int) y);
        return result;
    }

    public static int xor(int x, int y) {
        if (x != y)
            return 1;
        return 0;
    }
}

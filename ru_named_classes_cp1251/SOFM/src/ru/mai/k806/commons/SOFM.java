package ru.mai.k806.commons;

import java.util.LinkedList;
import java.util.List;

/**
 * ���� ��������
 */
public class SOFM implements Classificator {

    /**
     * ����� ���������� ��������� ����
     * ������ Output �� ������������
     *
     * @param samples
     */
    public SOFM(List<Sample> samples, int clasterCount) {

        clasters = new Claster[clasterCount];
        for (int i = 0; i < clasterCount; i++)
            clasters[i] = new Claster(24);
        // ������ � ���� �������
        for (int k = 0; k < 10; k++)
            work(samples);
        modificationBlocked = false;
    }

    public double whatIsIt(double[] sample) {
        List<Sample> samples = new LinkedList<Sample>();
        samples.add(new Sample(sample, null));
        return work(samples);
    }

    protected int work(List<Sample> samples) {
        double eta = 0.9;
        Metric metric = new EuclidSqrMetric();
        for (Sample sample : samples) {
            double minDistance = 0;
            int num = 0;
            int winnerNumber = 0;

            // ���� ����� ����������
            for (Claster claster : clasters) {
                //��������� ���������� ����� �������� � ���������� ��������
                double distance = metric.getDistance(claster.getCentroid(), sample.getInput());
                if (num == 0)
                    minDistance = distance;

                if (distance < minDistance) {
                    minDistance = distance;
                    winnerNumber = num;
                }
                num++;
            }

            if (this.modificationBlocked)
                return winnerNumber;
            // ������������ ����� ��������
            eta -= 0.01;

            // �������� �������� ��������-����������
            double[] winnerCentroid = clasters[winnerNumber].getCentroid();
            for (int i = 0; i < winnerCentroid.length; i++)
                winnerCentroid[i] += (sample.getInput()[i] - winnerCentroid[i]) * eta;
        }
        return 0;
    }

    protected boolean modificationBlocked = false;
    protected Claster[] clasters = new Claster[0];
}

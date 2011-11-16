package ru.mai.k806.fatAndThin;

import ru.mai.k806.commons.*;

/**
 * ���� ��� ��������
 */
public class ���������� {
    public static void main(String[] args) throws ����������������������� {

        // ��������� ��������
        double[][] ������� = new double[][]{
                {180, 80},
                {170, 90},
                {160, 99},
                {190, 70},
                {160, 60}
        };

        ���� ���� = new ����();

        double[][] ��������������� = new double[][]{{1,1},{-1,-1}};
        double[] ������������������� = new double[]{-80, -100};
        ���� ����������� = new ����(���������������, �������������������, new ��������������������������(0, 1, 0));

        // �������-�������!!!
        double[][] ��������������� = new double[][]{{1}, {1}};
        // �������� �������
        ���� ������������ = new ����(���������������, new �������������������������());

        ����.������������(�����������);
        for (int i = 0; i < �������.length; i++) {
            System.out.println("������ �" + (i + 1));
            System.out.println("���� = " + ���������������(�������[i]));
            System.out.println("����� = " + ���������������(����.�����������������(�������[i])));
            System.out.println();
        }

        ����.������������(������������);


        for (int i = 0; i < �������.length; i++) {
            System.out.println("������ �" + (i + 1));
            System.out.println("���� = " + ���������������(�������[i]));
            System.out.println("����� = " + ���������������(����.�����������������(�������[i])));
            System.out.println();
        }
    }

    private static String ���������������(double[] ������) {
        String ��������� = "[";
        for (int i = 0; i < ������.length; i++) {
            ��������� += ������[i];
            if (i < ������.length - 1)
                ��������� += ", ";
        }
        ��������� += "]";
        return ���������;
    }
}

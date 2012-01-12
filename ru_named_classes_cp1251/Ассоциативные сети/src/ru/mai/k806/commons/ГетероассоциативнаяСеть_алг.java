package ru.mai.k806.commons;

/**
 */
public class�����������������������_���  implements Classificator{


public�����������������������_���(List<Sample>samples){
        if(samples.size()==0){
        W=new double[0][0];
return;
}else
        W=new double[samples.get(0).getInputLength()][samples.get(0).getOutputLength()];
for(Sample sample:samples){
        for(int i=0;i<W.length;i++)
        for(int j=0;j<W[0].length;j++)
        W[i][j]+=sample.getInput()[i]*sample.getOutput()[j];
}
        }

public double whatIsIt(double[]input){
        double[]inp=input;
double[]oldOutput,output=null;
while(true){
        // ������ ���������������
        oldOutput=output;
output=new double[W[0].length];
for(int i=0;i<W.length;i++)
        for(int j=0;j<output.length;j++)
        output[j]+=inp[i]*W[i][j];
// ���������� ��������� ������� ����������
for(int j=0;j<output.length;j++)
        if(output[j]<0)
        output[j]=-1;
else if(output[j]>0)
        output[j]=1;
// �������� ���������������
inp=new double[input.length];
for(int i=0;i<W.length;i++)
        for(int j=0;j<output.length;j++)
        inp[i]=W[i][j]*output[j];
// �������� ������������
if(oldOutput!=null){
        boolean systemIsStable=true;
for(int j=0;j<output.length;j++)
        if(output[j]!=oldOutput[j])
        systemIsStable=false;
if(systemIsStable)
        break;
}
        }
        for(int j=0;j<output.length;j++)
        if(output[j]==1)
        return j;
return-1;
}


protected double[][]W;
}

package ru.mai.k806.commons;

/**
 */
public class�����������������������_0 implements Classificator{


public�����������������������_0(List<Sample>samples){
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
        int maxindex=-1;
double max=0;
double[]output=new double[W[0].length];
for(int j=0;j<output.length;j++){
        for(int i=0;i<input.length;i++)
        output[j]+=input[i]*W[i][j];
if(output[j]>max){
        max=output[j];
maxindex=j;
}
        }

        return maxindex;
}


protected double[][]W;
}

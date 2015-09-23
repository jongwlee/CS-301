import java.util.ArrayList;

//
// Name: Lee, Jong
// Project: # 2
// Due: 5/27/14
// Course: CS 301
//
// Description:
// Implement a program to read a text file and create a divided difference table.
// The program will then print out the interpolating polynomial and the simplified polynomial.

public class Polynomial{

    //Multiplying by x
    public ArrayList<Double> multiplyX(ArrayList<Double> array){
        ArrayList<Double> value = new ArrayList<Double>();
        value.add(0.0);

        for(int i = 0; i < array.size() - 1; i++){
            value.add(array.get(i));
        }
        return value;
    }

    //Multiplying the constants
    public ArrayList<Double> multiply(ArrayList<Double> array, double x){
        ArrayList<Double> value = new ArrayList<Double>();
        for(double i : array){
            value.add(i * x);
        }
        return value;
    }


    //Combine the like terms
    public ArrayList<Double> combineLike(ArrayList<ArrayList<Double>> array){
        ArrayList<Double> combined = new ArrayList<Double>();
        for(int i = 0; i < array.get(0).size(); i++){
            double sum = 0.0;
            for(int j = 0; j < array.size(); j++){
                sum = sum + array.get(j).get(i);
            }
            combined.add(sum);
        }

        return combined;
    }


    public ArrayList<Double> polyFunction(double value, ArrayList<Double> array, int size){
        ArrayList<ArrayList<Double>> narray = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> storeY = new ArrayList<Double>();

        for(int i = 0; i < array.size() + 1; i++){
            storeY.add(0.0);
        }

        storeY.add(0, value);

        for(int i = 0; i < array.size(); i++){
            narray.add(multiplyX(storeY));
            narray.add(multiply(storeY, -array.get(i)));
            storeY = combineLike(narray);
            narray.clear();
        }

        int ysize = storeY.size();

        for(int i = 0; i < size - ysize; i++){
            storeY.add(0.0);
        }

        return storeY;
    }
}
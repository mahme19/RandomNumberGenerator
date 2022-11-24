package com.company;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        double a = 101427;
        double c = 321;
        double m = Math.pow(2,16);


        double seed = 123456789;


        double a1 = 65539;
        double c1 = 0;
        double m1 = Math.pow(2,31);


        System.out.println("1st LCG Setting");

        //ArrayList<Double> generatedNumbers =  generateNumbers(a,c,m,seed);
        //chiSquare(generatedNumbers);
       // KS_test(generatedNumbers);
       // autoCorrelationTest(generatedNumbers);

        //System.out.println("2ND LCG setting");

        ArrayList<Double> generatedNumbers1 = generateNumbers(a1, c1, m1, seed);
        //KS_test(generatedNumbers1);

        autoCorrelationTest(generatedNumbers1);
        //System.out.println("Java Random Library");
        //ArrayList<Double> randomGeneratedNumbers = randomNumber();

        //KS_test(randomGeneratedNumbers);

    }


    public static ArrayList<Double> randomNumber(){
        ArrayList<Double> finalList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 10000 ; i++) {
            finalList.add(random.nextDouble());
        }
        return finalList;
    }
    public static ArrayList<Double> generateNumbers(double a , double c , double m, double seed){
        ArrayList<Double> listOfSeeds = new ArrayList<>();
        double newSeed;
        int amount = 0;

        while(amount < 10000){
            seed = (a * seed +c) % m;
            newSeed = seed/m;
            listOfSeeds.add(newSeed);
            amount++;
         }

        return listOfSeeds;
    }

    public static void chiSquare(ArrayList numbers) {
        int occurences[] = new int[10];
        System.out.println(numbers.size());
        for(int i = 0 ; i < numbers.size() ; i++) {

            //System.out.println("We get the number : " + string);
            double number = (double)numbers.get(i);

            //System.out.println("NUMBER : " + number);
            if (number >= 0.9) { occurences[9]++; }
            else if (number >= 0.8) { occurences[8]++; }
            else if (number >= 0.7) { occurences[7]++; }
            else if (number >= 0.6) { occurences[6]++; }
            else if (number >= 0.5) { occurences[5]++; }
            else if (number >= 0.4) { occurences[4]++; }
            else if (number >= 0.3) { occurences[3]++; }
            else if (number >= 0.2) { occurences[2]++; }
            else if (number >= 0.1) { occurences[1]++; }
            else if (number >= 0) { occurences[0]++; }
        }
        System.out.println("--- OCURRENCES ---");
        System.out.println(Arrays.toString(occurences));

        double chiSquareTotal = 0;

        for (int i = 0 ; i < 10 ; i++) {
            double expectedQuantity = numbers.size()/10;

            chiSquareTotal = chiSquareTotal + Math.pow(occurences[i]-(expectedQuantity),2)/(expectedQuantity);
            System.out.println("Chi under : " + chiSquareTotal);
        }

        System.out.println("chi TOTAL : " + chiSquareTotal);
        if (chiSquareTotal > 16.92) {
            System.out.println("H0 Rejected");
        } else {
            System.out.println("H0 NOT Rejected");
        }
    }

    public static void autoCorrelation(){
        double m;
        int i;
        int N;
        double M;

        double rho;
    }

    public static void KS_test(ArrayList<Double> numbers){
        double dPlus;
        double dMinus;
        ArrayList<Double> D_plus = new ArrayList<>();
        ArrayList<Double> D_minus = new ArrayList<>();

        ArrayList<Double> newArray = new ArrayList<>();


        for(int i = 0; i<100; i++){
            newArray.add(numbers.get(i));
        }


        Collections.sort(newArray);


        for(int i = 1; i < newArray.size()+1; i++){
            int size = newArray.size();
            int value = i-1;
            double test = newArray.get(i-1);
            double testTwo = (double)value/(double)size;
            double ddMinus = test - testTwo;
           D_minus.add(ddMinus);

           double ddPlus =  (i/ (double)newArray.size())- newArray.get(i-1);
           D_plus.add(ddPlus);
        }





        dMinus = Collections.max(D_minus);
        dPlus = Collections.max(D_plus);

        System.out.println("D minus:" + dMinus);
        System.out.println("D plus: "+ dPlus);

        double val = Math.max(dPlus, dMinus);
        System.out.println("D: "+val);


    }
    public static void autoCorrelationTest(ArrayList<Double> testArray) {
        System.out.println("we started");
        int m = 128;
        int i = 2;
        int N = testArray.size();
        int M = 77;  // 3 + ( M + 1 ) * 128 = 10,000

        // Calculate rho-value
        double sum = 0;
        int counter = 0;

        for (int k = 0 ; k <= M; k++) {

            int test1 = i+(k*m);
            int test2 = i+(k+1)*m;
            sum += testArray.get(test1)*testArray.get(test2);
            counter++;

        }


        System.out.println("number of loops " + counter);

        double divide = 1.0 / (77+1.0);
        double rho = divide * sum - 0.25;
        System.out.println("divide: " +divide);
        System.out.println("sum:"+ sum);
        System.out.println("Rho " + rho);

        // Calculate sigma
        double testsigma1 = Math.sqrt(13* 77+7);
        double testsigma2 =  12 * (77+1);
        double sigma = testsigma1 / testsigma2;
        System.out.println("sigma " + sigma);

        // Test statistic (Z_0)
        double Z = rho/sigma;

        System.out.println("Your Z : " + Z);
    }

    public static Integer factorial(int n)
    {
        BigInteger f = new BigInteger("1");
        for (int i = n; i > 0; i--)
            f = f.multiply(BigInteger.valueOf(i));
        return f.intValue();
    }


    public static void runTest(ArrayList<Double> runArray) {

        // The ArrayList that will store the each of the runs and their length
        ArrayList runLengths = new ArrayList();
        int df = 6; // Degree of Freedom


        int i = 0;  // Index used to iterate through random-array

        while(i < runArray.size()){
            int counter = 0;

            // Prevent indexOutOfBounds
            if(i == runArray.size()-1) { break; }

            if ((double)runArray.get(i) < (double)runArray.get(i+1)) {
                while ((double)runArray.get(i) < (double)runArray.get(i+1)) {
                    counter++;
                    i++;

                    // Prevent indexOutOfBounds
                    if(i == runArray.size()-1) { break; }
                }
                runLengths.add(counter);
            } else {
                while ((double) runArray.get(i) > (double) runArray.get(i + 1)) {
                    counter++;
                    i++;

                    // Prevent indexOutOfBounds
                    if (i == runArray.size() - 1) {
                        break;
                    }
                }
                runLengths.add(counter);
            }
        }

        // Count number of occurrences for each run-length, and insert into array [NOofLength1, NOofLength2, NOofLength3...]
        ArrayList occurencesOfLengths = new ArrayList();

        // Counting the observed occurences of each length
        for (int x = 1 ; x < df+2 ; x++ ) {
            occurencesOfLengths.add(Collections.frequency(runLengths, x));
        }


        // Calculating expected values
        ArrayList expOccurencesOfLengths = new ArrayList();

        // Calculating expected values
        for (int x = 1 ; x < df+2 ; x++ ) {
            double firstPart = 2.0/(double) factorial(x + 3);
            int secondPart = runArray.size()*((int)Math.pow(x,2)+3*x+1)-((int)Math.pow(x,3)+3*(int)Math.pow(x,2)-x-4);

            double expectedValue = firstPart * secondPart;

            expOccurencesOfLengths.add(expectedValue);
        }

        // Calculating the product of expected and observed - then saving sum as chi
        double chiValue = 0;

        for (int x = 0 ; x < df ; x++ ) {
            double expected = (double)expOccurencesOfLengths.get(x);
            int observed = (int)occurencesOfLengths.get(x);

            double product = Math.pow((expected-observed),2)/expected;
            chiValue += product;
        }


        System.out.println("chi TOTAL : " + chiValue);
        if (chiValue > 12.592) {
            System.out.println("H0 Rejected");
        } else {
            System.out.println("H0 NOT Rejected");
        }
    }

}

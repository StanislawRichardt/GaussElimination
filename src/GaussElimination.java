import java.util.Random;
import java.util.Scanner;

public class GaussElimination {

    static int numberOfEquations=4;

    //first parameter is enlarged by 1, because we want to start indexing from '1', not from '0'
    //second parameter is enlarged by 2, for reason above and also because we want to include free variable
    static double[][] matrix= new double[numberOfEquations+1][numberOfEquations+2];
    static Random exponents= new Random();
    static Scanner exponentIntake= new Scanner(System.in);

    //Functionality: to input exponents from system of equations to matrix, last loop run is for free word
    //Right now, exponents are randomized. Exponents can be manually written.
    private static void matrixInput(){
        for (int i=1;i<=numberOfEquations;i++){
            for (int j=1;j<=numberOfEquations+1;j++) {
                //matrix[i][j]= exponents.nextInt(100);
                matrix[i][j]=exponentIntake.nextDouble();
            }
        }
        displayMatrix();
    }

    //Functionality: to display matrix, after every Gauss elimination
    //Regex was used to cut out unnecessary zeroes
    private static void displayMatrix(){
        for(int i=1;i<=numberOfEquations;i++){
            for(int j=1;j<=numberOfEquations+1;j++){
               System.out.print(Double.toString(matrix[i][j]).replaceAll("\\.?0*$", "")+" ");
                //System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Functionality: Make Gauss elimination on every column and every row
    private static void GaussElimination(){

        for(int i=1;i<=numberOfEquations;i++){

            //rows need to be swapped in case divider is zero
            if(matrix[i][i]==0.0)
            {
                double temp;
                for (int l = 1; l <= numberOfEquations + 1; l++) {
                    temp = matrix[i][l];
                    matrix[i][l] = matrix[i + 1][l];
                    matrix[i + 1][l] = temp;
                }
                displayMatrix();
            }

            double divider=matrix[i][i];

            for(int j=i;j<=numberOfEquations;j++){
                double multiplier=matrix[j][i];

                for(int k=1;k<=numberOfEquations+1;k++){
                    //First step:algorithm divides row of current divider
                    if(j==i){
                        matrix[j][k]=matrix[j][k]/divider;
                    }

                    //Second step:algorithm subtract the rest of exponents
                    else{
                        matrix[j][k]=matrix[j][k]-(matrix[i][k]*multiplier);
                    }
                }
                displayMatrix();
            }
        }
    }

    //Functionality: to calculate values of the unknowns
    private static void matrixReduction(){
        for(int i=numberOfEquations;i>1;i--){
            for(int j=i;j>1;j--){

                //We subtract current free variable from previous free variable, multiplied by exponents, ranging down
                matrix[j-1][numberOfEquations+1]=matrix[j-1][numberOfEquations+1]-(matrix[i][numberOfEquations+1]*matrix[j-1][i]);

                matrix[j-1][i]=0;
            }
            displayMatrix();
        }
    }

    public static void main (String[] args){
        matrixInput();
        GaussElimination();
        matrixReduction();
    }
}

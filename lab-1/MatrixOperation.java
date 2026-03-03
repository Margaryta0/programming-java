import java.util.Random;

public class MatrixOperation {
    public static void main(String[] args) {
        System.out.println("Developer: Smal Margarita");
        System.out.println("---------------------------");

        int size = 4;
        int[][] K = new int[size][size];
        Random random = new Random();

        // 2. Filling the matrix with random numbers 
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                K[i][j] = random.nextInt(50) + 1;
            }
        }

        // 3. Printing the original matrix
        System.out.println("Original Matrix K:");
        printMatrix(K);

        // 4. Swapping main diagonal elements with the first column elements
        // Main diagonal: K[i][i]
        // First column: K[i][0]
        for (int i = 0; i < size; i++) {
            int temp = K[i][i];
            K[i][i] = K[i][0];
            K[i][0] = temp;
        }

        // 5. Printing the transformed matrix
        System.out.println("\nMatrix K after transformation:");
        printMatrix(K);

        // 6. Calculating the sum of the first column
        int firstColumnSum = 0;
        for (int i = 0; i < size; i++) {
            firstColumnSum += K[i][0];
        }

        System.out.println("\nSum of the elements of the first column: " + firstColumnSum);
    }

    // Helper method to print the matrix nicely
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
package utils;

import java.util.Arrays;

public class ArrayUtil {

    public static int[][] copyMatrix(int[][] matrix) {
        int[][] result = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++) {
            result[i] = new int[matrix[i].length];
            for(int j = 0; j < matrix[i].length; j++)
                result[i][j] = matrix[i][j];
        }
        
        return result;
    }

    public static int[][] reflect(int[][] matrix) {
        return reflectHorizontal(reflectVertical(matrix));
    }

    public static int[][] reflectHorizontal(int[][] matrix) {
        int[] col1 = new int[matrix.length];
        int[] col2 = new int[matrix.length];
        int[][] result = copyMatrix(matrix);
        for(int i = 0; i < result[0].length / 2; i++) {
            for(int j = 0; j < result.length; j++) {
                col1[j] = result[j][i];
                col2[j] = result[j][result[0].length - i - 1];
            }

            for(int j = 0; j < result.length; j++) {
                result[j][i] = col2[j];
                result[j][result[0].length - i - 1] = col1[j];
            }
        }
        return result;

    }

    public static int[][] reflectVertical(int[][] matrix) {
        int[][] result = copyMatrix(matrix);
        for(int i = 0; i < result.length / 2; i++) {
            int[] row1 = Arrays.copyOf(result[i], result[i].length);
            int[] row2 = Arrays.copyOf(result[result.length - i - 1], result[i].length);

            result[i] = row2;
            result[result.length - i -1] = row1;
        }
        return result;
    }

    public static int[][] multiplyByMinusOne(int[][] matrix) {
        int[][] result = copyMatrix(matrix);
        for(int i = 0; i < result.length; i++)
            for(int j = 0; j < result[i].length; j++)
                result[i][j] *= -1;
        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println();
        }
    } 
}

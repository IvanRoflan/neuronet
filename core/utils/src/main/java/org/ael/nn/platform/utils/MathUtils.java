
package org.ael.nn.platform.utils;

/**
 *
 * @author vaganovdv
 */
public class MathUtils {

  
    /**
     * Пример реализации статического метода:
     * метод производит умножение на 2 входного числа x
     * 
     * @param x  входное число 
     * @return  x = x*2
     */
    public static Double multiplicationBy2(Double x)
    {
        return x*2;
    }

    
    /**
     * Умножение матрицы на константу
     * 
     * @param matrix
     * @param a
     * @return 
     */
    public static Double [][] matrixMultiplicationByConstant(Double [][] matrix, Double a) {
        Double [][] result = new Double[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                result[i][j] = a * matrix[i][j];
            }
        }
        return result;
    }

    
    /**
     * Умножение матрицы на вектор
     * 
     * @param matrix
     * @param vector
     * @return 
     */
    public static Double [] matrixMultiplicationByVector(Double[][] matrix, Double[] vector) {
        Double [] result = new Double[matrix.length];
        if (matrix[0].length == vector.length) {
            for (int i = 0; i < matrix.length; i++) {
                Double v = 0.0;
                for (int j = 0; j < vector.length; j++) {
                    v += matrix[i][j] * vector[j];
                }
                result[i] = v;
            }
        } else {
            System.out.println("число столбцов в матрице должно совпадать с числом строк в векторе");
        }

        return result;
    }
    
    /**
     * Умножение матрицы на вектор
     *
     * @param matrix
     * @param vector
     * @return
     */
    public static Double[] vectorByMatrixMultiplication(double[] vector, double[][] matrix) {
        // Выходной вектор
        Double[] result = new Double[matrix[0].length];

        System.out.println("Количесвто строк: " + matrix.length);
        System.out.println("Количесвто столбцов: " + matrix[0].length);

        Double sum = 0.0;
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {
                System.out.println("  i = [" + i + "], j = [" + j + "]");
                System.out.println(matrix[i][j]);
                sum = sum + matrix[i][j] * vector[i];
                result[j] = sum;
            }

        }

        System.out.println("Размерность выходного вектора: "+result.length);
        StringBuilder sb = new StringBuilder();
        sb.append("\n [");
        for (Double s : result) {            
            sb.append(String.format("%12.3f", s));            
            sb.append("    ");                        
        }
        sb.append("]");
        System.out.println(sb.toString());
        
        return result;
    }
    
    
    /**
     * Умножение матриц
     *
     * @param a
     * @param b
     * @return
     */
    public static Double[][] matrixMultiply(Double[][] a, Double[][] b) {
        int rows = a.length;
        int columns = b[0].length;
        Double[][] result = new Double[rows][columns];
        if (rows == columns) {

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    for (int inner = 0; inner < b.length; inner++) {
                        result[row][col] += a[row][inner] * b[inner][col];
                    }
                }
            }

        } else {
            System.out.println("Кол-во строк первой матрицы должно равняться кол-ву столбцов второй матрицы");
        }
        return result;
    }

}

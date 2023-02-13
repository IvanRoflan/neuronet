/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

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
    
    public static Double [][] matrixMultiply(Double [][] a, Double [][] b) {
        int rows = a.length;
        int columns = b[0].length;
        Double [][]result = new Double[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                for (int inner = 0; inner < 2; inner++) {
                    result[row][col] += a[row][inner] * b[inner][col];
                }
            }
        }

        return result;
    }
    
}

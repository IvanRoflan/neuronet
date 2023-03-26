/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.ael.nn.platform.matrix;

/**
 *
 * @author vaganovdv
 */
public class Matrix {

    private double[][] data;
    
    private final int rows;       // Количество  строк
    private final int columns;    // Количество столбцов

    public Matrix(double[][] data) {
        this.data = data;
        this.rows = data.length;
        this.columns = data[0].length;
    }

    
    public Matrix(int rows, int columns) {
        data = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = Math.random() * 2 - 1;
            }
        }
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public int getRows() {
        return rows;
    }

    

    public int getColumns() {
        return columns;
    }

   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Матрица ["+this.rows+"] строк, ["+this.columns+"] столбцов");
        sb.append("\n");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                sb.append(" ");
                sb.append(String.format("%12.3f",  data[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.ael.nn.platform.matrix;

/**
 *
 * @author vaganovdv
 */
public class Matrix {

    private double[][] data;      // Данные матрицы
    
    private final int rows;       // Количество  строк
    private final int columns;    // Количество столбцов

    
    // 1 й способ создания матрицы  - указание входного 2-х мерного
    // массива
    public Matrix(double[][] data) {
        this.data = data;
        // Устанавливаем кол-во строк матрицы = длине двумерного массива
        this.rows = data.length;
        // Устанавливаем кол-во столбцов матрцы = длине первого элемента двумерного массива
        this.columns = data[0].length;
    }

    
    // 2 й способ - указание размерности массива
    //
    public Matrix(int rows, int columns) {
        data = new double[rows][columns];
        this.rows = rows;
        this.columns = columns;
        // Заполняем матрицу случайными числами
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

   
    // Метод вывода матрицы
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

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
    
    private int rows;       // Количество  строк
    private int columns;    // Количество столбцов

    public Matrix() {
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

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        
        for (int i = 0; i < rows; i++) {
            
            for (int j = 0; j < columns; j++) {
                sb.append(" ");
                sb.append(""+data[i][j]);
                
            }
        }
        
        
        return sb.toString();        
    }

    
    
    
}

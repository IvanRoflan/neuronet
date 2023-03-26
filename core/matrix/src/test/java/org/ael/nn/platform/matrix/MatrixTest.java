/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.platform.matrix;

import org.testng.annotations.Test;

/**
 * Тесты матриц
 * 
 * @author vaganovdv
 */
public class MatrixTest {
    
    @Test(priority = 1, groups = {"platform-core"})
    public void printMatrix ()
    {
        double [][] input = {
            {0, 1, 2},
            {1, 1, 5}
            
        };
        
        
        Matrix m = new Matrix (input);
       
        System.out.println(m);
        
    }
    
}

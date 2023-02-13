/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.platform.utils;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Тесты математических утилит 
 * 
 * 
 * @author vaganovdv
 */
public class MathUtilsTest {
    
     /**
     * Тест статических методов класса  MathUtils
     *  (пример оформления утилит)
     */
    @Test(priority = 1, groups = {"platform-core"})
    public void multiplicationBy2Test() {

        // Умножение 2 * 2.0
        Double y = MathUtils.multiplicationBy2(2.0);
        
        // Проверка результата теста 
        
        // проверить что равно 4.0 = y
        //              |
        //              |
        Assert.assertEquals(4.0, y);
        
    }
    
    @Test(priority = 1, groups = {"platform-core"})
    public void matrixMultiplyTest() {
       System.out.println("Начало теста");
       Double [][] b = {{1.0, 2.0, 3.1}, {3.4, 2.0, 1.7}};
       Double [][] a = {{1.1, 2.0}, {3.6, 4.7}, {5.1, 6.2}};
       
       Double [][] result = MathUtils.matrixMultiply(a, b);
       
       System.out.println("Тест успешно завершен");
    }

    
}

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

    
}

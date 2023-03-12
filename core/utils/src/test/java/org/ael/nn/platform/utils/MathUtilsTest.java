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
     * Тест статических методов класса MathUtils (пример оформления утилит)
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
    public void matrixMultiplyByConstant() {
        System.out.println("Начало теста умножения матрицы на константу");

        Double[][] A = {
            {1.0, 2.0, 3.1},
            {3.4, 2.0, 1.7}
        };
        Double B = 5.0;
        
        // Ожидаемая матрица
        Double[][] shouldBe = {
            {5.0, 10.0, 15.5},
            {17.0, 10.0, 8.5}
        };

        Double[][] result = MathUtils.matrixMultiplicationByConstant(A, B);

        System.out.println("Вывод ожидаемой матрицы: \n");
        for (int i = 0; i < shouldBe.length; i++) {
            for (int j = 0; j < shouldBe[i].length; j++) {
                System.out.print(shouldBe[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("Вывод полученной матрицы: \n");
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
       
        Assert.assertEquals(result, shouldBe);
        System.out.println("Тест завершен");
    }

    @Test(priority = 1, groups = {"platform-core"})
    public void matrixMultiplyByVector() {
        System.out.println("Начало теста умножения матрицы на вектор");

        Double[][] a = {
            {1.0, 2.0, 3.1},
            {3.4, 2.0, 1.7}
        };
        Double[] vector = {3.0, 4.0, 2.0};

        Double[] shouldBe = {17.2, 21.6};

        Double[] result = MathUtils.matrixMultiplicationByVector(a, vector);

        System.out.println("Вывод ожидаемого вектора: \n");
        for (int i = 0; i < shouldBe.length; i++) {
            System.out.print(shouldBe[i] + " ");
        }

        System.out.println("Вывод полученного вектора: \n");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println("Тест завершен");
    }

    /*
    @Test(priority = 1, groups = {"platform-core"})
    public void matrixMultiplyTest() {
       System.out.println("Начало теста умножения матриц");
       Double [][] a = {
        {1.0, 2.0, 3.1},
        {3.4, 2.0, 1.7}
        };
       Double [][] b = {
        {1.1, 2.0},
        {3.6, 4.7},
        {5.1, 6.2}};

        Double [][] shouldBe = {
            {24.11, 30.62},
            {19.61, 26.74}
           };
       
       Double [][] result = MathUtils.matrixMultiply(a, b);

       System.out.println("Вывод ожидаемой матрицы: \n");
       for (int i = 0; i < shouldBe.length; i++) {
        for (int j = 0; j < shouldBe[i].length; j++) {
            System.out.print(shouldBe[i][j] + "\t");
        }
        System.out.println();
       }

       System.out.println("Вывод полученной матрицы: \n");
       for (int i = 0; i < result.length; i++) {
        for (int j = 0; j < result[i].length; j++) {
            System.out.print(result[i][j] + "\t");
        }
        System.out.println();
       }

       System.out.println("Тест завершен");
    }
     */
}

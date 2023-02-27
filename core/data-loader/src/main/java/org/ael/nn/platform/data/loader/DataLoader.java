/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.ael.nn.platform.data.loader;

import org.ael.nn.platform.matrix.Matrix;



/**
 *
 * @author developer
 */
public class DataLoader {

    
      /**
     * Загрузка изображения из файла
     *
     * @param path строковый путь к файлу
     * @param fileName
     */
    public Matrix load(String path, String fileName) {
        Matrix m = new Matrix();

        System.out.println("Загрузка изображения из файла ...");

        if (path != null && !path.isEmpty()) {

            if (fileName != null && !fileName.isEmpty()) {
                System.out.println("Начало загрузки ...");
                System.out.println("Каталог загрузки: "+ path);
                System.out.println("Файл: "+ fileName);

            } else {
                System.out.println("Загрузка изображения из файла не возможна: отсутствует указание имени файла");
            }

        } else {
            System.out.println("Загрузка изображения из файла не возможна: отсутствует путь к файлу");
        }

        return m;

    }
}

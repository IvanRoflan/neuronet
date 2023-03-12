/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.ael.nn.platform.data.loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ael.nn.platform.matrix.Matrix;
import org.ael.nn.platform.vector.Vector;



/**
 *
 * @author developer
 */
public class DataLoader {

    // модификатор доступа      название класса      название экземпляра
    //  |                        /                    /
    private                    File                 file; 
    
    // Класс сканирования строк из файла
    private Scanner scanner;
   
    /**
     * Загрузка списка векторов из файла
     *
     * @param path строковый путь к файлу
     * @param fileName каталог с файлом имя файла
     * @param len длина вектора
     *
     */
    public List<Vector> loadVectorList(String path, String fileName, int len) {

        int index = 0; // Индекс для подсчета прочитанных из файла строк
        int filled = 0;
        int empty = 0;

        List<Vector> vectorList = new ArrayList<>();

        System.out.println("Загрузка списка векторов длиной ["+len+"] ...");

        if (checkIfFileExist(path, fileName)) {
            try {
                // Создание нового экземпляра сканера
                scanner = new Scanner(file);

                // Читать файл построчно - разделитеть строк - символ перевода строки
                index++;
                while (scanner.hasNextLine()) {
                    index++;
                    String s = scanner.nextLine();
                    if (!s.isEmpty()) {
                        filled++;
                        System.out.println(String.format("%-7s ==> %s", "[" + index + "]", s));
                        readVectorFromString(s, len);
                    } else {
                        empty++;
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Ошибка чтения из файла: ");
            }
        }
        return vectorList;

    }

    
    /**
     * Формирование вектора путем разбора строки
     *
     * @param s входная строка
     * @param len ожидаемое количество строк вектора
     * @return
     */
    private Vector readVectorFromString(String s, int len) {
        Vector vector = null;

        StringTokenizer st = new StringTokenizer(s, ",");

        System.out.println("Строка имеет [" + st.countTokens() + "]  токенов");
        if (st.countTokens() == len) {
            double[] v = new double[len];

            int index = -1;
            while (st.hasMoreTokens()) {
                String str = st.nextToken();
                System.out.println("token: " + str.trim());
                index++;
                try {
                    Double d = Double.parseDouble(str.trim());
                    v[index] = d;
                } catch (NumberFormatException ex) {
                    System.out.println("Не удалось преобразовать токен: [" + str + "] в число формата " + Double.class.getSimpleName());
                }                              
            }
            
            vector = new Vector(v);
            
        } else {
            System.out.println("Ошибка разбора строки: ожидаемая длина вектора [" +len+  "] не соответствует количеству токенов [" +st.countTokens()+ "]");
        }
        return vector;
    }
    
    

    /**
     * Проверка существования файла
     *
     * @param path
     * @param fileName
     * @return
     */
    private boolean checkIfFileExist(String path, String fileName) {
        boolean result = false;

        if (path != null && !path.isEmpty()) {
            if (fileName != null && !fileName.isEmpty()) {
                System.out.println("Начало загрузки ...");
                System.out.println(String.format("%-35s %s", "Каталог загрузки: ", path));
                System.out.println(String.format("%-35s %s", "Файл: ", fileName));
                String fullPath = path + File.separator + fileName;
                file = new File(fullPath);
                if (file.exists()) {
                    System.out.println("Файл  [" + fileName + "] существует");
                    result = true;
                } else {
                    System.out.println("Ошибка: файл  [" + fileName + "] не существует");
                }
            } else {
                System.out.println("Загрузка списка векторов из файла не возможна: отсутствует указание имени файла");
            }
        } else {
            System.out.println("Загрузка изображения из файла не возможна: отсутствует путь к файлу");
        }
        return result;
    }
  
    
}

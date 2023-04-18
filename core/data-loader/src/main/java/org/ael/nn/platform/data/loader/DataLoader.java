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
import org.ael.nn.platform.vector.Vector;

/**
 *
 * @author developer
 */
public class DataLoader {

    // модификатор доступа      название класса      название экземпляра
    //  |                        /                    /
    private File file;

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

        System.out.println("Загрузка списка векторов длиной [" + len + "] ...");

        if (checkIfFileExist(path, fileName)) {
            try {
                // Создание нового экземпляра сканера
                scanner = new Scanner(file);

                // Читать файл построчно - разделитеть строк - символ перевода строки
                index++;
                while (scanner.hasNextLine()) {
                    index++;
                    // Получаем строку
                    String s = scanner.nextLine();
                    if (!s.isEmpty()) {
                        filled++;
                        System.out.println(String.format("%-7s ==> %s", "[" + index + "]", s));
                        // Если строка не пустая - получаем из неё вектор с помощью readVectorromString()
                        Vector vectorFromString = readVectorFromString(s, len, index);
                        if (vectorFromString != null) {
                            // Если получилось достать вектор добавляем в список векторов
                            vectorList.add(vectorFromString);
                            System.out.println("Добавлен вектор :" + vectorFromString.getUid());
                        }
                    } else {
                        empty++;
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Ошибка чтения из файла:  " + fileName + " описание ошибки: " + ex.toString());
            }

            if (!vectorList.isEmpty()) {
                System.out.println("Успешно прочитан из файла [" + fileName + "] список векторов [" + vectorList.size() + "] элементов");
            } else {
                System.out.println("Ошибка чтения списка векторов из файла [" + fileName + "] СПИСОК ВЕКТОРОВ НЕ ПРОЧИТАН");
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
    private Vector readVectorFromString(String s, int len, int strIndex) {
        // Инициализируем переменную под вектор
        Vector vector = null;

        // Разделяем строку на токены
        StringTokenizer st = new StringTokenizer(s, ",");

        System.out.println("Строка [" + strIndex + "] имеет [" + st.countTokens() + "]  токенов");
        if (st.countTokens() == len) {
            // Инициализируем массив под вектор размером с len
            double[] v = new double[len];

            int index = -1;
            while (st.hasMoreTokens()) {
                // Пока есть токены записываем их в строку str
                String str = st.nextToken();
                System.out.println("token: " + str.trim());
                index++;
                try {
                    // Пытаемся перевести из строки в число double
                    Double d = Double.parseDouble(str.trim());
                    // Полученное число добавляем в массив
                    v[index] = d;
                } catch (NumberFormatException ex) {
                    System.out.println("Не удалось преобразовать токен: [" + str + "] в число формата " + Double.class.getSimpleName());
                }
            }

            // Создаем вектор на основе полученного массива
            vector = new Vector(v);

        } else {
            System.out.println("Ошибка разбора строки [" + strIndex + "]: ожидаемая длина вектора [" + len + "] не соответствует количеству токенов [" + st.countTokens() + "]");
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

        // Проверяем что путь не пустой и не равен нулю
        if (path != null && !path.isEmpty()) {
            // Проверяем что имя файла не поустое и не равно нулю
            if (fileName != null && !fileName.isEmpty()) {
                System.out.println("Начало загрузки ...");
                System.out.println(String.format("%-35s %s", "Каталог загрузки: ", path));
                System.out.println(String.format("%-35s %s", "Файл: ", fileName));
                // Записываем полный путь до файла конкатенацией строк
                String fullPath = path + File.separator + fileName;
                // СОздаем файл
                file = new File(fullPath);

                if (file.exists()) {
                    // Если файл существует - возвращаем истину
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

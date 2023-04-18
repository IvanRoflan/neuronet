/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.ael.nn.planform.core.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.ael.nn.planform.core.neuron.Neuron;
import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.layer.Layer;
import org.ael.nn.platform.matrix.Matrix;
import org.ael.nn.platform.utils.MathUtils;
import org.ael.nn.platform.vector.Vector;

/**
 * Нейронная сеть Нейронная сеть состоит из списка слоев нейронов. Слои могут
 * содержать массивы нейронови или одиночные векторы.
 *
 * @author vaganovdv
 */
public class Network {

    /**
     * Название нейросети
     */
    private String name;

    /**
     *  Уникальный идентификатор сети
     */
    private final String uid;



    /**
         *  Конструктор создания нейросети
         */
        public Network() {
        this.uid = UUID.randomUUID().toString();
        System.out.println("Создана сейть нейронов  uid = [" + this.uid + "]");
    }

    /**
     * Карта слоев нейронной сети (ключ - целое число)
     *
     *
     */
    private final Map<Integer, Layer> layerMap = new ConcurrentHashMap<>();

    /**
     * Карта синапсов (ключ - uid синапса)
     *
     */
    private final Map<String, Synapse> synapseMap = new ConcurrentHashMap<>();

    /**
     * Карта матриц синаптических связей (ключ - uid двух слоев)
     *
     */
    private final Map<String, Matrix> synapseMatrixMap = new ConcurrentHashMap<>();

    /**
     *   Выход нейронной сети
     */
    private Vector output;

    /**
     * Ожидаемый выход
     */
    private Vector expectedOutput;

    public Vector getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(Vector expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    /**
     * Среднеквадратичная ошибка
     */
    private double mse;

    public double getMse() {
        return mse;
    }

    public void setMse(double mse) {
        this.mse = mse;
    }

    public Vector getOutput() {
        return output;
    }

    public void setOutput(Vector output) {
        this.output = output;
    }

    public Map<Integer, Layer> getLayerMap() {
        return layerMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Соединение 2-х слоев
     *
     * @return
     */
    public boolean connectLayers(Integer layerNumber1, Integer layerNumber2) {
        boolean result = false;
        System.out.println("Соединение слоев нейросети [" + layerNumber1 + "] и [" + layerNumber2 + "] ...");

        // Инициализируем переменные со слоями, получая их из списка слоев
        Layer layer1 = layerMap.get(layerNumber1);
        Layer layer2 = layerMap.get(layerNumber2);

        /**
         * Подключение слоя ВЕКТОРОВ(layer1) к слою НЕЙРОНОВ (layer2)
         */
        if (layer1.getVector() != null && layer2.getNeuronCount() > 0) {
            connectVectorLayerToNeuronLayer(layer1, layer2);
        }

        /**
         * подключение слоя НЕЙРОНОВ(layer1) к слою НЕЙРОНОВ (layer2)
         */
        if (layer1.getNeuronCount() > 0 && layer2.getNeuronCount() > 0) {
            connectNeuronLayerToNeuronLeayer(layer1, layer2);
        }

        /**
         * подключение слоя НЕЙРОНОВ(layer1) к слою ВЕКТОРОВ (layer2)
         */
        if (layer1.getNeuronCount() > 0 && layer2.getVector() != null) {
            this.connectNeuronLayerToVectorLeayer(layer1, layer2);
        }

        return result;
    }

    /**
     * Соедиение векторного слоя со слоем нейронной сети
     *
     * @param layer1
     * @param layer2
     * @return
     */
    private boolean connectVectorLayerToNeuronLayer(Layer layer1, Layer layer2) {
        boolean result = false;

        // Получаем вектор из первого слоя
        Vector vector = layer1.getVector();
        // Получаем список нейронов из второго слоя
        List<Neuron> neuronList = layer2.getNeuronList();

        // Проверяем не пйстой ли вектор
        if (vector != null && vector.getData().length > 0) {
            // Счетчик  количества синапсов, подключенных к вектору
            //  
            int connectionCount = vector.getData().length * neuronList.size();

            StringBuilder sb = new StringBuilder();
            sb.append("\n");

            int synapseCount = 0;
            // Создаем список чисел
            List<Double> v = new ArrayList<>();
            // Заполняем список чисел данными вектора для удобства
            for (int i = 0; i < vector.getData().length; i++) {
                v.add(vector.getData()[i]);
                System.out.println("Добавлено значение в список " + vector.getData()[i]);
            }

            sb.append("\n");
            sb.append(String.format("%-25s %-7s", "Размерность вектора", "[" + vector.getData().length + "]"));
            sb.append("\n");
            sb.append(String.format("%-25s %-7s", "Количество нейронов", "[" + neuronList.size() + "]"));
            sb.append("\n");
            sb.append(String.format("%-25s %-7s", "Количество синапсов", "[" + connectionCount + "]"));
            sb.append("\n");

            // Создание матрицы синаптических связей между слоями                        
            Matrix m = new Matrix(vector.getData().length, neuronList.size());

            // Цикл поэлементного обхода вектора 
            for (int i = 0; i < v.size(); i++) {
                sb.append(String.format("%-25s %-7s", "Соединение ячейки вектора ==> ", "[" + i + "]"));
                sb.append("\n");

                // Цикл обхода нейронов, входящих в слой
                for (int j = 0; j < neuronList.size(); j++) {
                    // Создаем синапс
                    Synapse synapse = new Synapse();

                    // Чтение веса (коэффциента в синапсе)
                    Double w = synapse.getW();
                    // Записываем вес в матрицу весов между слоями
                    m.getData()[i][j] = w;

                    // Устанавливаем вход и выход синапса
                    synapse.setInputUid(vector.getDataIndexUidMap().get(i));
                    synapse.setOutputUid(neuronList.get(j).getUid());
                    synapseCount++;
                    sb.append(String.format("%-15s %-15s %-15s %12.3f %-15s", "Создан синапс ", "[" + synapseCount + "]", " c коэффциентом передачи: ", synapse.getW(), "нейрон [" + j + "]"));
                    sb.append("\n");

                    // Получаем uid синапса
                    String synapseUid = synapse.getUid();
                    // Добавляем синапс в карту синапсов нейросети
                    synapseMap.put(synapseUid, synapse);
                    // Устанавливаем синапсы в слои (первый слой - на выход, второй - на вход)
                    layer1.getOutputSynapsesIdList().add(synapseUid);
                    layer2.getInputSynapsesIdList().add(synapseUid);
                }
            }

            // Запись матрицы синаптических связей
            String matrixKey = layer1.getUid().trim() + ":" + layer2.getUid().trim();
            this.synapseMatrixMap.put(matrixKey, m);
            sb.append(String.format("%-15s %-15s %-15s %s", "Матрица сязей", "слой [" + layer1.getLayaerNumber() + "]", "слой [" + layer2.getLayaerNumber() + "]", "ключ: " + matrixKey));
            sb.append("\n");
            sb.append(String.format("%-15s %-15s %-15s %s", "Соединение", "слой [" + layer1.getLayaerNumber() + "]", "слой [" + layer2.getLayaerNumber() + "]", "ЗАВЕРШЕНО"));
            sb.append("\n");
            System.out.println(sb.toString());
            System.out.println(m);

        }

        return result;
    }

    /**
     * Формирование списка синапсов для подключения слоя нейронов к слою
     * векторному
     *
     * @param l1 слой нейронов
     * @param l2 векторный слой
     * @return
     */
    public boolean connectNeuronLayerToVectorLeayer(Layer l1, Layer l2) {
        boolean result = false;

        System.out.println("Соедиенение слоя нейронов с векторным слоем...");

        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        // Получение списка нейронов из слоя нейронов
        List<Neuron> neuronList = l1.getNeuronList();
        // Получение вектора из векторного слоя
        Vector vector = l2.getVector();

        // Создание матрицы связей                         
        Matrix m = new Matrix(neuronList.size(), vector.getData().length);

        // Количество синаптических  связей
        int connectionCount = neuronList.size()*vector.getData().length;
        
        sb.append("\n");
        sb.append(String.format("%-25s %-7s", "Количество нейронов", "[" + neuronList.size() + "]"));
        sb.append("\n");
        sb.append(String.format("%-25s %-7s", "Размерность вектора", "[" + vector.getData().length + "]"));
        sb.append("\n");
        sb.append(String.format("%-25s %-7s", "Количество синапсов", "[" + connectionCount + "]"));
        sb.append("\n");

        // Обход списка нейронов
        for (int i = 0; i < neuronList.size(); i++) {
            System.out.println("Обход списка нейронов, номер нейрона [" + i + "]");

            // ОБход элементов вектора
            for (int j = 0; j < vector.getData().length; j++) {
                System.out.println("Обход списка элементов векторов, элемент [" + j + "]");

                // Создание синапса
                Synapse synapse = new Synapse();

                // Чтение веса (коэффциента в синапсе) и запись в матрицу весов
                Double w = synapse.getW();
                m.getData()[i][j] = w;

                // Получение идентификатора элемена вектора с иденксом j
                String vectorUid = vector.getDataIndexUidMap().get(j);
                System.out.println("Формирование правого подключения элемент вектора [" + j + "] uid вектора [" + vectorUid + "]");

                // Получение идентификатра нейрона с индексом i
                String neuronUid = neuronList.get(i).getUid();
                System.out.println("Формирование левого подключения нейрона [" + i + "] uid нейрона [" + neuronUid + "]");
                // Установление входа и выхода синапса
                synapse.setOutputUid(vectorUid);
                synapse.setInputUid(neuronUid);

                // Добавление синапса в карту синапсов
                synapseMap.put(synapse.getUid(), synapse);
                // Устанавливаем синапсы в слои (первый слой - на выход, второй - на вход)
                l1.getOutputSynapsesIdList().add(synapse.getUid());
                l2.getInputSynapsesIdList().add(synapse.getUid());
            }
        }

        // Создание уникального идентифкатора матрицы синаптических связей
        String matrixKey = l1.getUid().trim() + ":" + l2.getUid().trim();

        // Сохранение сформированной матрицы синаптических связей в карте матриц
        this.synapseMatrixMap.put(matrixKey, m);

        sb.append(String.format("%-15s %-15s %-15s %s", "Матрица сязей", "слой [" + l1.getLayaerNumber() + "]", "слой [" + l2.getLayaerNumber() + "]", "ключ: " + matrixKey));
        sb.append("\n");
        sb.append(String.format("%-15s %-15s %-15s %s", "Соединение", "слой [" + l1.getLayaerNumber() + "]", "слой [" + l2.getLayaerNumber() + "]", "ЗАВЕРШЕНО"));
        sb.append("\n");
        System.out.println(sb.toString());
        System.out.println(m);

        return result;
    }

    /**
     * Соединение 2-х слоев, соодрежащих нейроны
     *
     * @param l1
     * @param l2
     * @return
     */
    private boolean connectNeuronLayerToNeuronLeayer(Layer l1, Layer l2) {
        boolean result = false;
        
        StringBuilder sb = new StringBuilder();
        sb.append("\n");

        // Получаем списки нейронов
        List<Neuron> neuronListL1 = l1.getNeuronList();
        List<Neuron> neuronListL2 = l2.getNeuronList();

        // Создание матрицы связей                         
        Matrix m = new Matrix(neuronListL1.size(), neuronListL2.size());

        if ((neuronListL1 != null && neuronListL1.size() != 0) || (neuronListL2 != null && neuronListL2.size() != 0)) {
            // Счетчик  количества синапсов, подключенных к вектору
            //  
            int connectionCount = neuronListL1.size() * neuronListL2.size();

            int synapseCount = 0;

            sb.append(String.format("%-25s %-7s", "Количество нейронов в первом слое", "[" + neuronListL1.size() + "]"));
            sb.append("\n");
            sb.append(String.format("%-25s %-7s", "Количество нейронов во втором слое", "[" + neuronListL2.size() + "]"));
            sb.append("\n");
            sb.append(String.format("%-25s %-7s", "Количество синапсов", "[" + connectionCount + "]"));
            sb.append("\n");

            // Обход нейронов первого слоя
            for (int i = 0; i < neuronListL1.size(); i++) {
                sb.append(String.format("%-25s %-7s", "Соединение нейрона первого слоя ==> ", "[" + i + "]"));
                sb.append("\n");
                // Обход нейронов второго слоя
                for (int j = 0; j < neuronListL2.size(); j++) {
                    // СОздаем синапс
                    Synapse synapse = new Synapse();
                    // Чтение веса (коэффциента в синапсе) и запись в матрицу весов
                    Double w = synapse.getW();
                    m.getData()[i][j] = w;

                    // Установление входа и выхода синапса
                    synapse.setInputUid(neuronListL1.get(i).getUid());
                    synapse.setOutputUid(neuronListL2.get(j).getUid());
                    synapseCount++;
                    sb.append(String.format("%-15s %-15s %-15s %12.3f %-15s", "Создан синапс ", "[" + synapseCount + "]", " c коэффциентом передачи: ", synapse.getW(), "нейрон входа[" + j + "]"));
                    sb.append("\n");

                    String synapseUid = synapse.getUid();
                    // Добавление синапса в карту синапсов
                    synapseMap.put(synapseUid, synapse);
                    // Устанавливаем синапсы в слои (первый слой - на выход, второй - на вход)
                    l1.getOutputSynapsesIdList().add(synapseUid);
                    l2.getInputSynapsesIdList().add(synapseUid);
                }
            }
        }

        // Создание уникального идентифкатора матрицы синаптических связей
        String matrixKey = l1.getUid().trim() + ":" + l2.getUid().trim();

        // Сохранение сформированной матрицы синаптических связей в карте матриц
        this.synapseMatrixMap.put(matrixKey, m);

        sb.append(String.format("%-15s %-15s %-15s %s", "Матрица сязей", "слой [" + l1.getLayaerNumber() + "]", "слой [" + l2.getLayaerNumber() + "]", "ключ: " + matrixKey));
        sb.append("\n");
        sb.append(String.format("%-15s %-15s %-15s %s", "Соединение", "слой [" + l1.getLayaerNumber() + "]", "слой [" + l2.getLayaerNumber() + "]", "ЗАВЕРШЕНО"));
        sb.append("\n");
        System.out.println(sb.toString());
        System.out.println(m);
        return result;
    }

    /**
     * Вычисление отклика (выхода) нейронной сети
     *
     */
    public void calculate() {

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Вычисление выхода сети uid = " + this.uid + " ...\n");
        sb.append(String.format("%-25s %-7s", "Количество слоев", "[" + this.layerMap.size() + "]"));
        sb.append("\n");

        System.out.println(sb.toString());

        int k = 0;
        for (int i = 0; i < layerMap.size() - 1; i++) {

            k = i + 1;
            System.out.println("Формирование матрицы для слоев [" + i + "] и [" + k + "] ...");

            Layer layer1 = this.layerMap.get(i);
            Layer layer2 = this.layerMap.get(k);

            String key = layer1.getUid().trim() + ":" + layer2.getUid().trim();
            if (this.synapseMatrixMap.containsKey(key)) {
                System.out.println("Прочитана синаптическая матрица, соединяющая слои: {" + layer1.getLayaerNumber() + ", " + layer2.getLayaerNumber() + "} идентификатор матрицы: " + key);
                Matrix m = synapseMatrixMap.get(key);

                System.out.println(m);
                double[] vector;
                if (layer1.getVector() != null) {
                    vector = layer1.getVector().getData();
                    System.out.println(new Vector(vector));
                    if (!(layer2.getNeuronList().isEmpty()) || (layer2.getVector() != null)) {
                        System.out.println("Умножение вектора слоя [" + i + "] на матрицу соединяющую слои [" + i + "] и  [" + k + "] ...");
                        // Рассчитываем выход слоя layer2
                        double[] output = MathUtils.vectorByMatrixMultiplication(vector, m.getData());
                        // Запсываем выход в вектор и присваиваем его второму слою
                        Vector v = new Vector(output);
                        layer2.setOutputValues(v);
                        // Еcли второй номер - последний, то присваиваем его выходной вектор как выход нерйосети
                        if (layer2.getLayaerNumber() == layerMap.size() - 1) {
                            System.out.println("Достигнут крайний слой нейросети. Записываем его выходной вектор в выхо нейросети...");
                            setOutput(v);
                            System.out.println("Рассчитан выход нейросети:\n" + v);
                        }
                        
                    }
                } else if (layer1.getOutputValues() != null) {
                    vector = layer1.getOutputValues().getData();
                    System.out.println(new Vector(vector));
                    if (!(layer2.getNeuronList().isEmpty()) || (layer2.getVector() != null)) {
                        System.out.println("Умножение вектора слоя [" + i + "] на матрицу соединяющую слои [" + i + "] и  [" + k + "] ...");
                        // Рассчитываем выход слоя layer2
                        double[] output = MathUtils.vectorByMatrixMultiplication(vector, m.getData());
                        // Запсываем выход в вектор и присваиваем его второму слою
                        Vector v = new Vector(output);
                        layer2.setOutputValues(v);   

                        // Еcли второй номер - последний, то присваиваем его выходной вектор как выход нерйосети
                        if (layer2.getLayaerNumber() == layerMap.size() - 1) {
                            System.out.println("Достигнут крайний слой нейросети. Записываем его выходной вектор в выхо нейросети...");
                            setOutput(v);
                            System.out.println("Рассчитан выход нейросети:\n" + v);
                        }              
                    }
                }

            } else {
                System.out.println("Ошибка: не найдена синаптическая матрица весов, соотвествующая ключу: " + key);
            }

        }

        if (output != null && expectedOutput != null) {
            System.out.println(" Рассчет среднеквадратичной ошибки");
            double e = MathUtils.calculateVectorDistance(expectedOutput.getData(), output.getData());
            setMse(e);
            System.out.println("Среднеквадратичная ошибка = " + mse);
        }

    }

    /**
     * Добавление слоя в сеть
     *
     * @return
     */
    public boolean addLayer(Layer layer) {
        boolean result = false;
        // Получаем номер слоя
        Integer layaerNumber = layer.getLayaerNumber();

        if (!this.layerMap.containsKey(layaerNumber)) {
            // Если такого номера не существует в нейросети - добавляем
            this.layerMap.put(layaerNumber, layer);
            result = true;
        } else {
            System.out.println("Ошибка добавления слоя: слой с номером [] сущесвует в сети, uid сети = [" + this.uid + "]");
        }
        return result;
    }

    public String getUid() {
        return uid;
    }

    public Map<String, Synapse> getSynapseMap() {
        return synapseMap;
    }

}

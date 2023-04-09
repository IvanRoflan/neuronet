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

    
    private String name; 
    private final String uid;

    public Network() {
        this.uid = UUID.randomUUID().toString();
        System.out.println("Создана сейть нейронов  uid = [" + this.uid + "]");
    }

    /**
     * Карта слоев нейронной сети
     *
     *
     */
    private final Map<Integer, Layer> layerMap = new ConcurrentHashMap<>();

    /**
     * Карта синапсов
     *
     */
    private final Map<String, Synapse> synapseMap = new ConcurrentHashMap<>();

    /**
     * Карта матриц синаптических связей
     */
    private final Map<String, Matrix> synapseMatrixMap = new ConcurrentHashMap<>();

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

        Layer layer1 = layerMap.get(layerNumber1);
        Layer layer2 = layerMap.get(layerNumber2);

        /**
         * подключение слоя векторов(layer1) к слою нейронов (layer2)
         */
        if (layer1.getVector() != null && layer2.getNeuronCount() > 0) {
            connectVectorLayerToNeuronLayer(layer1, layer2);
        }

        /**
         * подключение слоя нейров(layer1) к слою нейронов (layer2)
         */
        if (layer1.getNeuronCount() > 0 && layer2.getNeuronCount() > 0) {
            connectNeuronLayerToNeuronLeayer(layer1, layer2);
        }

        /**
         * подключение слоя нейров(layer1) к слою векторов (layer2)
         */
        if (layer1.getNeuronCount() > 0 && layer2.getVector() != null) {
            this.connectNeuronLayerToVectorLeayer(layer1, layer2);
        }

        return result;
    }

    /**
     * Соедиенние векторного слоя со слоем нейронной сети
     *
     * @param layer1
     * @param layer2
     * @return
     */
    private boolean connectVectorLayerToNeuronLayer(Layer layer1, Layer layer2) {
        boolean result = false;

        Vector vector = layer1.getVector();
        List<Neuron> neuronList = layer2.getNeuronList();

        if (vector != null && vector.getData().length > 0) {
            // Счетчик  количества синапсов, подключенных к вектору
            //  
            int connectionCount = vector.getData().length * neuronList.size();

            StringBuilder sb = new StringBuilder();
            sb.append("\n");

            int synapseCount = 0;
            List<Double> v = new ArrayList<>();
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

            // Создание матрицы связей                         
            Matrix m = new Matrix(vector.getData().length, neuronList.size());
                
            
            // Цикл поэлементного обхода вектора 
            for (int i = 0; i < v.size(); i++) {
                sb.append(String.format("%-25s %-7s", "Соединение ячейки вектора ==> ", "[" + i + "]"));
                sb.append("\n");
                
                // Цикл обхода нейронов, входящих в слой
                for (int j = 0; j < neuronList.size(); j++) {

                    Synapse synapse = new Synapse();

                    // Чтение веса (коэффциента в синапсе)
                    Double w = synapse.getW();
                    
                    m.getData()[i][j] = w;
                    
                    synapse.setInputUid(vector.getDataIndexUidMap().get(i));
                    synapse.setOutputUid(neuronList.get(j).getUid());
                    synapseCount++;
                    sb.append(String.format("%-15s %-15s %-15s %12.3f %-15s", "Создан синапс ", "[" + synapseCount + "]", " c коэффциентом передачи: ", synapse.getW(), "нейрон [" + j + "]"));                    
                    sb.append("\n");

                    String synapseUid = synapse.getUid();
                    synapseMap.put(synapseUid, synapse);
                    layer1.getOutputSynapsesIdList().add(synapseUid);
                    layer2.getOutputSynapsesIdList().add(synapseUid);
                }
            }
            
            // Запись матрицы синаптических связей
            String matrixKey = layer1.getUid().trim()+":"+layer2.getUid().trim();
            this.synapseMatrixMap.put(matrixKey, m);
            sb.append(String.format("%-15s %-15s %-15s %s", "Матрица сязей", "слой [" + layer1.getLayaerNumber() + "]",  "слой [" + layer2.getLayaerNumber() + "]", "ключ: "+matrixKey));            
            sb.append("\n");
            sb.append(String.format("%-15s %-15s %-15s %s", "Соединение", "слой [" + layer1.getLayaerNumber() + "]",  "слой [" + layer2.getLayaerNumber() + "]", "ЗАВЕРШЕНО"));            
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

        // Получение списка нейронов из слоя нейронов
        List<Neuron> neuronList = l1.getNeuronList();
        Vector vector = l2.getVector();

        // Создание матрицы связей                         
        Matrix m = new Matrix(vector.getData().length, neuronList.size());
        
        for (int i = 0; i < neuronList.size(); i++) {
            System.out.println("Обход списка нейронов, номер нейрона [" + i + "]");

            for (int j = 0; j < vector.getData().length; j++) {
                System.out.println("Обход списка элементов векторов, элемент [" + j + "]");

                Synapse synapse = new Synapse();

                // Получение идентификатора элемена вектора с иденксом j
                String vectorUid = vector.getDataIndexUidMap().get(j);
                System.out.println("Формирование правого подключения элемент вектора [" + j + "] uid вектора [" + vectorUid + "]");

                String neuronUid = neuronList.get(i).getUid();
                System.out.println("Формирование левого подключения нейрона [" + i + "] uid нейрона [" + neuronUid + "]");
                synapse.setOutputUid(vectorUid);
                synapse.setInputUid(neuronUid);

                synapseMap.put(synapse.getUid(), synapse);
                l1.getOutputSynapsesIdList().add(synapse.getUid());
                l2.getInputSynapsesIdList().add(synapse.getUid());

            }
        }
        
        
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

        List<Neuron> neuronListL1 = l1.getNeuronList();
        List<Neuron> neuronListL2 = l2.getNeuronList();

        if ((neuronListL1 != null && neuronListL1.size() != 0) || (neuronListL2 != null && neuronListL2.size() != 0)) {
            // Счетчик  количества синапсов, подключенных к вектору
            //  
            int connectionCount = neuronListL1.size() * neuronListL2.size();

            StringBuilder sb = new StringBuilder();
            sb.append("\n");

            int synapseCount = 0;

            sb.append(String.format("%-25s %-7s", "Количество нейронов в первом слое", "[" + neuronListL1.size() + "]"));
            sb.append("\n");
            sb.append(String.format("%-25s %-7s", "Количество нейронов во втором слое", "[" + neuronListL2.size() + "]"));
            sb.append("\n");
            sb.append(String.format("%-25s %-7s", "Количество синапсов", "[" + connectionCount + "]"));
            sb.append("\n");

            for (int i = 0; i < neuronListL1.size(); i++) {
                sb.append(String.format("%-25s %-7s", "Соединение нейрона первого слоя ==> ", "[" + i + "]"));
                sb.append("\n");
                for (int j = 0; j < neuronListL2.size(); j++) {
                    Synapse synapse = new Synapse();
                    // Чтение веса (коэффциента в синапсе)
                    Double w = synapse.getW();
                    synapse.setInputUid(neuronListL1.get(i).getUid());
                    synapse.setOutputUid(neuronListL2.get(j).getUid());
                    synapseCount++;
                    sb.append(String.format("%-15s %-15s %-15s %12.3f %-15s", "Создан синапс ", "[" + synapseCount + "]", " c коэффциентом передачи: ", synapse.getW(), "нейрон входа[" + j + "]"));
                    sb.append("\n");

                    String synapseUid = synapse.getUid();
                    synapseMap.put(synapseUid, synapse);
                    l1.getOutputSynapsesIdList().add(synapseUid);
                    l2.getInputSynapsesIdList().add(synapseUid);
                }
            }
            System.out.println(sb.toString());
        }
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
                if (layer1.getVector() != null)
                {
                   if (!layer2.getNeuronList().isEmpty()) 
                   {
                       System.out.println("Умножение вектора слоя ["+i+"] на матрицу соединяющую слои ["+i+"] и  ["+k+"] ...");
                       MathUtils.vectorByMatrixMultiplication(layer1.getVector().getData(), m.getData());
                       
                   }
                }  
                
            } else {
                System.out.println("Ошибка: не найдена синаптическая матрица весов, соотвествующая ключу: " + key);
            }

        }

    }
        

    /**
     * Добавление слоя в сеть
     *
     * @return
     */
    public boolean addLayer(Layer layer) {
        boolean result = false;
        Integer layaerNumber = layer.getLayaerNumber();

        if (!this.layerMap.containsKey(layaerNumber)) {
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

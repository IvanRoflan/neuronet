package org.ael.nn.platform.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.ael.nn.planform.core.neuron.Neuron;
import org.ael.nn.platform.vector.Vector;

/**
 * Класс для представления слоев нейронной сети 
 * Слой нейронной сети может содержать список нейронов или вектор
 * 
 * 
 * @author vaganovdv
 */
public class Layer {
  
    /**
     * Номер слоя
     */
    private Integer layaerNumber;

    /**
     * Индекс для печати 
     */
    private  int index = 0;
    
    /**
     * Количество нейронов в слое
     */
    private Integer neuronCount;
    
    /**
     * Уникальный идентификатор слоя нейронной сети
     */
    private String uid;

    /**
     * Список нейронов, содежащихся в слое
     */
    private List<Neuron> neuronList = new ArrayList<>();

    /**
     * Список идентификаторов входных синапсов
     */
    private final List <String> inputSynapsesIdList = new ArrayList<>();
    
    /**
     * Список идентификаторов выходных синапсов
     */
    private final List <String> outputSynapsesIdList = new ArrayList<>();
    
    
    
    
    /**
     * Вектор, содержащийсвя в слое
     */
    private Vector vector = null;
  
    
    /**
     * Создание слоя нейронов, содержащего список нейронов
     *
     * @param layaerNumber номер слоя
     * @param neuronCount  количество нейронов в слое
     */
    public Layer(Integer layaerNumber, Integer neuronCount) {

        this.uid = UUID.randomUUID().toString();
        System.out.println("\nФормирование слоя нейронной сети...");
        System.out.println("Параметры слоя: ");
        System.out.println(String.format("%-25s %s", "Идентификатор слоя", "uid = "+uid));
        System.out.println(String.format("%-25s %s", "Номер слоя:", "["+layaerNumber+"]"));
        System.out.println(String.format("%-25s %s", "Количесвто нейронов:", ""+neuronCount));
        
        this.layaerNumber = layaerNumber;
        this.neuronCount = neuronCount;

        if (layaerNumber != null && layaerNumber >= 0) {
            // Проверка указания числа нейронов
            if (neuronCount != null && neuronCount != 0) {
                for (int i = 0; i < neuronCount; i++) {
                    Neuron n = new Neuron();
                    neuronList.add(n);
                    System.out.println("слой ["+layaerNumber+"] uid = "+uid +"  добавлен нейрон ==> id = "+n.getUid());
                }
            } else {
                System.out.println("Ошибка создания слоя нейронной сети  uid = [" + uid + "]: количество нейронов нулевое или не указано");
            }
        } else {
            System.out.println("Ошибка создания слоя нейронной сети  uid = [" + uid + "]: номер слоя не указан или отрицательный");
        }
        System.out.println("Формирование слоя ["+layaerNumber+"] ЗАВЕРШЕНО");
        
    }

    /**
     * Создание слоя, соедержащего вектор
     *
     * @param vector
     * @param layaerNumber
     */
    public Layer(Integer layaerNumber, Vector vector) {
        this.vector = vector;
        this.layaerNumber = layaerNumber;
        this.uid = UUID.randomUUID().toString();
        System.out.println("\nФормирование слоя нейронной сети...");
        System.out.println("Параметры слоя: ");
        System.out.println(String.format("%-25s %s", "Идентификатор слоя", "uid = " + uid));
        System.out.println(String.format("%-25s %s", "Номер:", "[" + layaerNumber + "]"));
        System.out.println(String.format("%-25s %-30s", "Вектор слоя :", "[" + vector.getData().length + "] элементов"));
        System.out.println("Формирование слоя [" + layaerNumber + "] ЗАВЕРШЕНО");

    }

    
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Слой нейронной сети, номер: [" + this.layaerNumber + "]\n");

        if (neuronList != null && !neuronList.isEmpty()) {
            sb.append(String.format("   %-25s %-10s", "Количество нейронов:", "[" + neuronList.size() + "]"));
            sb.append("\n");

        }

        if (vector != null && vector.getData() != null && vector.getData().length > 0) {
            sb.append(String.format("   %-25s %-20s", "Вектор размерности:", "[" + vector.getData().length + "] элементов"));
            sb.append("\n");
        }

        sb.append(String.format("   %-25s %-20s", "Список входных синапсов:", "[" + this.inputSynapsesIdList.size() + "] записей"));
        sb.append("\n");
        sb.append(String.format("   %-25s %-20s", "Список выходных синапсов:", "[" + this.outputSynapsesIdList.size() + "] записей"));
        sb.append("\n");

        if (!inputSynapsesIdList.isEmpty()) {
            index = 0;
            sb.append(String.format("%-70s", "Список входных связей [" + inputSynapsesIdList.size() + "] записей:"));
            inputSynapsesIdList.stream().forEach(synapseUid -> {
                index++;
                sb.append("[" + index + "] " + synapseUid);
                sb.append("\n");
            });
        }
        if (!outputSynapsesIdList.isEmpty()) {
            index = 0;
            sb.append(String.format("%-70s", "Список выходных связей [" + outputSynapsesIdList.size() + "] записей"));
            outputSynapsesIdList.stream().forEach(synapseUid -> {
                index++;
                sb.append("[" + index + "] " + synapseUid);
                sb.append("\n");
            });
        }
        return sb.toString();
    }

    
    
    public Vector getVector() {
        return vector;
    }


    

    public Integer getLayaerNumber() {
        return layaerNumber;
    }

    public void setLayaerNumber(Integer layaerNumber) {
        this.layaerNumber = layaerNumber;
    }

    public Integer getNeuronCount() {
        return neuronList.size();
    }

    public void setNeuronCount(Integer neuronCount) {
        this.neuronCount = neuronCount;
    }

    public List<Neuron> getNeuronList() {
        return neuronList;
    }

    public void setNeuronList(List<Neuron> neuronList) {
        this.neuronList = neuronList;
    }

    public String getUid() {
        return uid;
    }

    public List<String> getInputSynapsesIdList() {
        return inputSynapsesIdList;
    }

    public List<String> getOutputSynapsesIdList() {
        return outputSynapsesIdList;
    }

    
    
}

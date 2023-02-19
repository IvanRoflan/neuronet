package org.ael.nn.platform.layer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.ael.nn.planform.core.neuron.Neuron;
import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.vector.Vector;

/**
 * Класс для представления слоев нейронной сети 
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
     * Список нейронов
     */
    private List<Neuron> neuronList = new ArrayList<>();

    /**
     * Список входных синапсов
     */
    private List<Synapse> inputSynapses = new ArrayList<>();
    
    
    
      /**
     * Создание слоя нейронов
     *
     * @param layaerNumber количество
     * @param neuronCount
     */
    public Layer(Integer layaerNumber, Integer neuronCount) {

        this.uid = UUID.randomUUID().toString();
        System.out.println("\nФормирование слоя нейронной сети...");
        System.out.println("Параметры слоя: ");
        System.out.println(String.format("%-25s %s", "Идентификатор слоя", "uid = "+uid));
        System.out.println(String.format("%-25s %s", "Номер слоя:", ""+layaerNumber));
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

    @Override
    public String toString() {        
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Слой нейронов ["+layaerNumber+"]\n");
        sb.append(String.format("   %-25s %-10s", "Количество нейронов: ",neuronList.size()+"\n"));                
        return sb.toString();       
    }

    /**
     * Добавление вектора в слой нейронной сети
     *
     * @param vector добавляемый вектор метод
     */
    public List<Synapse> addInputVector(Vector vector) {
        List<Synapse> synapsList = new ArrayList<>();

        if (vector != null && vector.getData().size() > 0) {
            int connectionCount = vector.getData().size() * neuronList.size();

            System.out.println("Формирование [" + connectionCount + "] синапсов от вектора uid = [" + vector.getUid() + "] к слою [" + this.uid + "] номер слоя: " + layaerNumber);

            StringBuilder sb = new StringBuilder();
            sb.append("\n");            
            
            int synapseCount = 0;
            List<Double> v = new ArrayList<>(vector.getData().values());
            
            for (int i = 0; i < v.size(); i++) {              
                
                sb.append(String.format("%-25s %-5s","Номер строки вектора ==> ", "["+i+"], "));                
                sb.append("\n");
                for (int j = 0; j < neuronList.size(); j++) {
                    sb.append(String.format("  %-15s %-7s","номер нейрона:", "["+j+"]"));                    
                    Synapse synapse = new Synapse();                    
                    synapse.setInputUid(uid);
                    synapse.setOutputUid(neuronList.get(j).getUid());                    
                    synapsList.add(synapse);  
                    synapseCount++;
                    sb.append(String.format("%-35s %-7s %s","["+synapseCount+"]-ая синаптическая связь:", "["+i+" "+j+"]", "uid = "+synapse.getUid()));
                    sb.append("\n");
                }
                

            }
            System.out.println(sb.toString());
        }
        return synapsList;
    }
    
    /**
     * Соединение с другим слоем нейронной сети
     * 
     * @param layer подключаемый слой
     */
    public List<Synapse> connectToLayer(Layer layer) {
        List<Synapse> synapsList = new ArrayList<>();
        if (layer != null && layer.getNeuronList().size() != 0) {
            int connectionCount = neuronCount * layer.neuronCount;

            System.out.println("Формирование [" + connectionCount + "] синапсов от слоя = [" + layaerNumber + "] к слою [" + this.uid + "] номер слоя: " + layer.layaerNumber);
            
            StringBuilder sb = new StringBuilder();
            sb.append("\n");

            int synapseCount = 0;
            for (int i = 0; i < neuronCount; i++) {
                System.out.println("Номер входного нейрона: " + neuronList.get(i));
                for (int j = 0; j < layer.getNeuronCount(); j++) {
                    System.out.println("Номер выходного нейрона: " + layer.getNeuronList().get(j).getUid());
                    Synapse synapse = new Synapse();                    
                    synapse.setInputUid(layer.getNeuronList().get(j).getUid());
                    synapse.setOutputUid(neuronList.get(i).getUid());
                    neuronList.get(i).addSynapse(synapse);  
                    layer.getNeuronList().get(j).addSynapse(synapse);                  
                    synapsList.add(synapse);  
                    synapseCount++;
                    sb.append(String.format("%-35s %-7s %s","["+synapseCount+"]-ая синаптическая связь:", "["+i+" "+j+"]", "uid = "+synapse.getUid()));
                    sb.append("\n");
                }
            }
            System.out.println(sb.toString());
        }
        return synapsList;
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

    
    
}

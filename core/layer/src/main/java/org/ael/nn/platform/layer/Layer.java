package org.ael.nn.platform.layer;

import java.util.ArrayList;
import java.util.List;
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

    private  int index = 0;
    
    /**
     * Количество нейронов в слое
     */
    private Integer neuronCount;

    /**
     * Список нейронов
     */
    private List<Neuron> neuronList = new ArrayList<>();

    public Layer(Integer layaerNumber, Integer neuronCount) {
        this.layaerNumber = layaerNumber;
        this.neuronCount = neuronCount;
        if (neuronCount != null && neuronCount != 0) {
            for (int i = 0; i < neuronCount; i++) {
                neuronList.add(new Neuron());
            }
        }
    }

    @Override
    public String toString() {        
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Слой нейронов ["+layaerNumber+"]\n");
        sb.append(String.format("   %-25s %-10s", "Количество нейронов: ",neuronList.size()+"\n"));        
        sb.append(String.format("   %-25s %s", "Список нейронов:", "\n"));        
        sb.append(String.format("   %-25s %s", "Список нейронов:", "\n"));        
        return sb.toString();       
    }

    /**
     * Добавление вектора
     */
    public List<Synapse> addInput(Vector vector) {
        List<Synapse> synapsList = new ArrayList<>();
        
        if (vector != null && vector.getData().size() > 0) {
            vector.getData().forEach( ( uid, value) -> {                
                neuronList.stream().forEach( neuron-> {
                    Synapse synapse = new Synapse();
                    
                    synapse.setInputUid(uid);
                    synapse.setOutputUid(neuron.getUid());
                    synapsList.add(synapse);
                    
                    
                });               
            });                
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

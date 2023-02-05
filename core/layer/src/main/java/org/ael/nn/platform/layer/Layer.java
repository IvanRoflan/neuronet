package org.ael.nn.platform.layer;

import java.util.ArrayList;
import java.util.List;
import org.ael.nn.planform.core.neuron.Neuron;

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


}

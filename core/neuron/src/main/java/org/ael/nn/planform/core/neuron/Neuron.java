
package org.ael.nn.planform.core.neuron;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.ael.nn.platform.connection.Synapse;




public class Neuron {
 
    /**
     * Поле идентификатор нейрона
     */
    private String uid;

    private Double output;

    /**
     * Get the value of output
     *
     * @return the value of output
     */
    public Double getOutput() {
        return output;
    }

    /**
     * Set the value of output
     *
     * @param output new value of output
     */
    public void setOutput(Double output) {
        this.output = output;
    }

    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    
    
    /**
     * Список входных связей
     *
     */
    private List<Synapse> synapses = new ArrayList<>();

    /**
     *
     */
    private Long synapseCount;

    public Neuron() {
        this.uid = UUID.randomUUID().toString();
    }

    /**
     * Добавление синапса
     *
     * @param synapse
     */
    public void addSynapse(Synapse synapse) {
        if (synapse != null) {
            synapses.add(synapse);
        }
    }

    public List<Synapse> getSynapses() {
        return synapses;
    }
    
    
    /**
     * Конструктор класса 
     * 
     * @param synapseCount 
     */
    public Neuron(Long synapseCount) {
        this.uid = UUID.randomUUID().toString();
        if (synapseCount != null && synapseCount > 0) {
            for (int i = 0; i < synapseCount; i++) {
                synapses.add(new Synapse());
            }
        }
    }

    /**
     * Вычисление выхода
     */
    public void calculate() {

    }

    /**
     * Чтение поля uid
     *
     * @return
     */
    public String getUid() {
        return uid;
    }

    /**
     * Запись uid
     *
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Нейрон uid = " + uid + ", количество входных синапсов: [" +synapses.size()+ "]";
    }

    /**
     * Вычисление сигмоидальной функции активации
     *
     * @param x
     * @return
     */
    private Double sigmoid(Double x) {
        double y = 1 / (1 + Math.exp(-1.0 * x));
        return y;
    }

    
}

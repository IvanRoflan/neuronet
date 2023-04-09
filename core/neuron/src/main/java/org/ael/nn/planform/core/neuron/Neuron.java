
package org.ael.nn.planform.core.neuron;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.UUID;




public class Neuron {
 
    /**
     * Поле идентификатор нейрона
     */
    private String uid;

    
    /**
     * Значение выхода нейронной сети 
     */
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
     * 
     * @param weights 
     */
    
    /*
    public void  setSynapseWeights (double [] weights)
    {
 
        System.out.println("Установка весов синапсов для нейрона   uid = ["+this.uid+"]... ");
        if (weights != null && weights.length != 0)
        {
            
            if (!this.synapses.isEmpty())
            {
                if (this.synapses.size() == weights.length)                
                for (int i = 0; i < synapses.size(); i++) {
                    synapses.get(i).setW(weights[i]);
                }
                
                Vector v = new Vector(weights);
                System.out.println("Установка весов синапсов для нейрона   uid = ["+this.uid+"] ЗАВЕРШЕНА");
                System.out.println("Установлены веса: "+v.toString());
                
            }
        
        }
    }
    */
    
  
    
    
   
    /**
     *
     */
    private Long synapseCount;

    public Neuron() {
        this.uid = UUID.randomUUID().toString();
    }

    
    
    
   
    /**
     * Вычисление выхода нейрона
     * печать матрицы (вектора)
     * 
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
        return "Нейрон uid = " + uid;
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

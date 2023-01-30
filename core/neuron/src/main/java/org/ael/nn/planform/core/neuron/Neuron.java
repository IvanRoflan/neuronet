
package org.ael.nn.planform.core.neuron;

import java.util.HashMap;
import java.util.Map;


public class Neuron {
    
    /**
     * Поле идентификатор нейрона
     */
    private  String uid;
    
    
    /**
     * Карта входных сигналов 
     * 
     *       иденттификатор  уровень сигнала
     *             /         /  
     */                        
    private  Map<String, Double> inputSignals = new HashMap<>();
    
    
    /**
     * Уровень выходного сигнала нейрона 
     */
    private  Double output = 0.0;
    
    
    /**
     * Вычисление выхода 
     */
    public void  calculate()
    {
        inputSignals.forEach( (uid, signal) -> {
        
            
        });
        
        
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
        return "Neuron {" + "uid = " + uid + '}';
    }
    
    
    private Double sigmoid (Double x)
    {
        return x;        
    }   
    
    
}

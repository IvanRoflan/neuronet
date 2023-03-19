

package org.ael.nn.platform.connection;

import java.util.UUID;


public class Synapse {
    
    
    
    /**
     * Идентификатор соединения 
     */
    private String uid;  
    
    /**
     * Коэффциент передачи связи 
     */
    private Double w;
    
    /**
     * Индетификатор нейрона входа
     */
     private String inputUid;
     
    /**
     * Инддетификатор нейрона выхода
    */
    private String outputUid;

    
    
    public Synapse() {
        
        // Генерация случайного идентификатора, преобразование идентификатора в строку
        this.uid = UUID.randomUUID().toString();       
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Double getW() {
        return w;
    }

    public void setW(Double w) {
        this.w = w;
    }

    public String getInputUid() {
        return inputUid;
    }

    public void setInputUid(String inputUid) {
        this.inputUid = inputUid;
    }

    public String getOutputUid() {
        return outputUid;
    }

    public void setOutputUid(String outputUid) {
        this.outputUid = outputUid;
    }
    

    @Override
    public String toString() {
        return "синаптическая связь №" + uid + " с весом " + w + " ==> " + "uid входного нейрона = " + inputUid + ", uid выходного нейрона =" + outputUid + '}';
    }
     
     
}


package org.ael.nn.planform.core.neuron;


public class Neuron {
    
    /**
     * Поле инлентификатор нейрона
     */
    private  String uid;

    
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
    
    
    
    
    
}

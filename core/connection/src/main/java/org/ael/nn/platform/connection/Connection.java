

package org.ael.nn.platform.connection;


public class Connection {

    /**
     * Индетификатор нейрона входа
     */
     private String inputUid;
     
    /**
     * Инддетификатор нейрона выхода
    */
 private String outputUid;

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
        return "Связь между нейронами {" + "inputUid=" + inputUid + ", outputUid=" + outputUid + '}';
    }
     
     
     
    
}

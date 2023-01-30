 
package org.ael.nn.planform.core.neuron;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.ael.nn.platform.connection.Connection;
import org.testng.annotations.Test;


/**
 *
 * @author vaganovdv
 */
public class NeuronTest {
    
    /**
     * Тест создания  нейцрона и соединения между нейронами
    */
    @Test(priority = 1, groups = {"platform-core"})
    public void createNeuronTest() {

      List <Neuron> neuronList = new ArrayList<>();
        
      Neuron neuron1 = new Neuron();
      Neuron neuron2 = new Neuron();
      
      neuron1.setUid("7");
      neuron2.setUid("45");
      
      System.out.println(neuron1.toString());
      System.out.println(neuron2.toString());
      
      Connection connection1 = new Connection();
      connection1.setInputUid(neuron1.getUid());
      connection1.setOutputUid(neuron2.getUid());
      
      System.out.println(connection1.toString());

    }

     /**
     * Тест умножения вектора на матрицу
    */
    @Test(priority = 2, groups = {"platform-core"})
    public void vectorMatrixMultiplication() {

      

    }
    
    
}

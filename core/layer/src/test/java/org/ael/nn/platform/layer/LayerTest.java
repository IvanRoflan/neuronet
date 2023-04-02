 
package org.ael.nn.platform.layer;


import org.ael.nn.platform.vector.Vector;
import org.testng.annotations.Test;


/**
 *
 * @author vaganovdv
 */
public class LayerTest {
  
    private int index = 0;

    /**
     * Тест создания слоя содержащего вектор
     */
    @Test(priority = 1, groups = {"platform-core"})
    public void createLayerWithVector() {

        // Входной вектор 
        double[] data = {-0.3, 3.1, 0.5};
        Vector v = new Vector(data);
        Layer layer = new Layer(0, v);
        
        System.out.println(layer);

    }
    
    
    /**
     * Тест создания слоя содержащего список нейронов
     */
    @Test(priority = 2, groups = {"platform-core"})
    public void createLayerWithNeurons() {

        Layer layer = new Layer(1,3);
        
        System.out.println(layer);

    }

  
}

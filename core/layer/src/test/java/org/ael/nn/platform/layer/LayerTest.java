 
package org.ael.nn.platform.layer;


import java.util.List;
import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.vector.Vector;
import org.testng.annotations.Test;


/**
 *
 * @author vaganovdv
 */
public class LayerTest {
    
    /**
     * Тест создания вектора
    */
    @Test(priority = 1, groups = {"platform-core"})
    public void createLayer() {

        Layer layer = new Layer(0, 1);
        Vector v1 = new Vector(3);
        List<Synapse> addInput = layer.addInput(v1);
        
        
        
        
        
        System.out.println(layer);
      
    }

    
    
}

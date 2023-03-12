 
package org.ael.nn.platform.vector;


import org.testng.annotations.Test;

/**
 *
 * @author vaganovdv
 */
public class VectorTest {

    /**
     * Тест создания вектора
     */
    @Test(priority = 1, groups = {"platform-core"})
    public void createVector() {        
        Vector v1 = new Vector(10);
        System.out.println(v1.toString());        
    }
}

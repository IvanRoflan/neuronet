/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.planform.core.network;

import org.ael.nn.platform.layer.Layer;
import org.ael.nn.platform.vector.Vector;
import org.testng.annotations.Test;

/**
 *
 * @author vaganovdv
 */
public class NetworkTest {

    @Test(priority = 1, groups = {"platform-core"})
    public void createNetworkWith_2_Layers() {

        // Входной вектор 
        double[] data = {-0.3, 3.1, 0.5};
        Vector v = new Vector(data);
        Layer layer1 = new Layer(0, v);
        Layer layer2 = new Layer(1, 1);

        System.out.println(layer1);
        System.out.println(layer2);

        Network network = new Network();

        network.addLayer(layer1);
        network.addLayer(layer2);
        
        network.connectLayers(layer1.getLayaerNumber(), layer2.getLayaerNumber());

        System.out.println(layer1);
        System.out.println(layer2);
        
        network.calculate();

    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.platform.database;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.ael.nn.planform.core.network.Network;
import org.ael.nn.platform.layer.Layer;
import org.ael.nn.platform.vector.Vector;
import org.testng.annotations.Test;

/**
 *
 * @author vaganovdv
 */
public class DatabaseTest {
    
     // Местоположение тестовых файлов JSON для формирования графа
    private Path resourceDirectory = Paths.get("src", "main", "resources", "neuro-network-json");
    private final String graphFileName = "neuronet.json";
    private final String graphPath = resourceDirectory.toFile().getAbsolutePath() + File.separator + File.separator + graphFileName;
    private final File graphFile = new File(graphPath);

    
    
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
        network.setName("ТЕСТОВАЯ НЕЙРОННАЯ СЕТЬ");

        network.addLayer(layer1);
        network.addLayer(layer2);

        network.connectLayers(layer1.getLayaerNumber(), layer2.getLayaerNumber());
        
        
        
        Database.writeNerworkToFile(network, graphFile);
        Database.readNerworkFromFile(graphFile);
       
       
    }

       
    
}

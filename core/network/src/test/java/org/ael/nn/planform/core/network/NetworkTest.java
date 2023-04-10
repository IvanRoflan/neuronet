/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.planform.core.network;

import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.layer.Layer;
import org.ael.nn.platform.vector.Vector;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 *
 * @author vaganovdv
 */
public class NetworkTest {

 
    /**
     * тест создания нейросети с 2-мя слоями
     */
    @Ignore
    @Test(priority = 1, groups = {"platform-core"})
    public void createNetworkWith_2_Layers() {

        // Входной вектор 
        double[] data = {-0.3, 3.1, 0.5};
        Vector vector = new Vector(data);
        // Создание векторного слоя
        Layer layer1 = new Layer(0, vector);
        
        // Создание нейронного слоя
        Layer layer2 = new Layer(1, 3);

        // Вывод на печать слоев
        System.out.println(layer1);
        System.out.println(layer2);

        // Создание нейросети
        Network network = new Network();

        //  Добавление слоев в нейросеть
        network.addLayer(layer1);
        network.addLayer(layer2);

        network.connectLayers(layer1.getLayaerNumber(), layer2.getLayaerNumber());
        
        //network.calculate();
       
    }

    
     /**
     * тест создания нейросети с 2-мя слоями
     */
    @Test(priority = 2, groups = {"platform-core"})
    public void connectNeuronAndVectorLayer() {

        // Входной вектор 
        double[] data = {-0.3, 3.1, 0.5};
        Vector vector = new Vector(data);
        // Создание векторного слоя
        Layer layer1 = new Layer(1, 3);
                
        
        // Создание нейронного слоя
        Layer layer2 = new Layer(0, vector);

        // Вывод на печать слоев
        System.out.println(layer1);
        System.out.println(layer2);

        // Создание нейросети
        Network network = new Network();

        //  Добавление слоев в нейросеть
        network.addLayer(layer1);
        network.addLayer(layer2);

        network.connectLayers(layer1.getLayaerNumber(), layer2.getLayaerNumber());
        
        //network.calculate();
       
    }
    
    
    @Ignore
    @Test(priority = 3, groups = {"platform-core"})
    public void connectLayers() {

        double[] data = {-0.3, 3.1, 0.5};
        Vector v = new Vector(data);

        int neuronCount = 5;

        for (int i = 0; i < v.getData().length; i++) {
            System.out.println("Элемент вектора [" + i + "]  uid элемента:" + v.getDataIndexUidMap().get(i));
            for (int j = 0; j < neuronCount; j++) {
                System.out.println("Нейрон [" + j + "]");
                Synapse synapse = new Synapse();
                System.out.println("Создан синапс: " + synapse.toString());
            }

        }

    }

    @Ignore
    @Test(priority = 4, groups = {"platform-core"})
    public void createSynapseMap() {

        Synapse s1 = new Synapse();
        Synapse s2 = new Synapse();
        System.out.println("Уникальный идентифкатор синапса 1: " + s1.getUid());
        System.out.println("Уникальный идентифкатор синапса 2: " + s2.getUid());

        Network network = new Network();

        network.getSynapseMap().put(s1.getUid(), s1);
        network.getSynapseMap().put(s2.getUid(), s2);

        System.out.println("сеть содержит [" + network.getSynapseMap().size() + "] синпсов");

        Synapse s = network.getSynapseMap().get(s2.getUid());
        System.out.println("Прочитан синапс: " + s);

    }
    
    
   
    @Ignore
    @Test(priority = 4, groups = {"platform-core"})
    public void connectNeuronLayerToVectorLayer() {

        // Создание сети
        Network network = new Network();

        // Создание слоя нейронов
        Layer layer1 = new Layer(0, 2);
        
        // Создание векторного слоя
        double[] data = {-0.3, 3.1, 0.5};
        Vector v = new Vector(data);
        Layer layer2 = new Layer(1, v);
        
        network.addLayer(layer1);
        network.addLayer(layer2);
        network.connectLayers(layer1.getLayaerNumber(),layer2.getLayaerNumber());

        System.out.println(layer1);
        System.out.println(layer2);
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.platform.database;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ael.nn.planform.core.network.Network;
import org.ael.nn.planform.core.neuron.Neuron;
import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.layer.Layer;
import org.ael.nn.platform.vector.Vector;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 *
 * @author vaganovdv
 */
public class DatabaseTest {
    
    // Местоположение тестовых файлов JSON для формирования нейросети
    private  final  Path resourceDirectory = Paths.get("src", "main", "resources", "neuro-network-json");  
    private  final String neuronFileName = "pi-91.json";
    private  final String path = resourceDirectory.toFile().getAbsolutePath() + File.separator  + neuronFileName;
    private  final File networkFile = new File(path);

    
      private static ObjectMapper mapper = JsonMapper.builder()
            .addModule(new ParameterNamesModule())  // подключение модулей
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .registerSubtypes(Network.class)
            .registerSubtypes(Layer.class)
            .registerSubtypes(Synapse.class)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();
    
    
   
    @Test(priority = 1, groups = {"platform-core"})
    public void createNetworkWith_2_Layers() {

        // Входной вектор 
        double[] data = {-0.3, 3.1, 0.5};
        Vector v = new Vector(data);
        
        // Формирование слоев нейросети
        Layer layer1 = new Layer(0, v);
        Layer layer2 = new Layer(1, 1);

        // Печать слоев
        System.out.println(layer1);
        System.out.println(layer2);

        // Создание экземпляра нейросети
        Network network = new Network();
        network.setName("ТЕСТОВАЯ НЕЙРОННАЯ СЕТЬ для групп ПИ-91");

        // Добавление созданных слоев в нейросеть
        network.addLayer(layer1);
        network.addLayer(layer2);

        // Содинение слоев
        network.connectLayers(layer1.getLayaerNumber(), layer2.getLayaerNumber());
        
        
        //Запись нейросети в файл
        Database.writeNerworkToFile(network, networkFile);
        
        // Чтение нейросети из файла
        Network nerworkFromFile = Database.readNerworkFromFile(networkFile);
        System.out.println("Чтение завершено");
        
       
    }

    @Ignore
    @Test(priority = 1, groups = {"platform-core"})
    public void toJson() {
        
        Neuron neuron = new Neuron();
        try {
           String json =  mapper.writeValueAsString(neuron);
            System.out.println("Строка представляющая экземляр класса Neuron:\n"+json);
           
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
         Layer layer2 = new Layer(1, 3);
         
         try {
           String json =  mapper.writeValueAsString(layer2);
            System.out.println("Строка представляющая экземляр класса Layer:\n"+json);
           
        } catch (JsonProcessingException ex) {
            Logger.getLogger(DatabaseTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
       
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package org.ael.nn.platform.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import org.ael.nn.planform.core.network.Network;
import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.layer.Layer;

/**
 *
 * @author vaganovdv
 */
public class Database {

    private static ObjectMapper mapper = JsonMapper.builder()
            .addModule(new ParameterNamesModule())
            .addModule(new Jdk8Module())
            .addModule(new JavaTimeModule())
            .registerSubtypes(Network.class)
            .registerSubtypes(Layer.class)
            .registerSubtypes(Synapse.class)
            .enable(SerializationFeature.INDENT_OUTPUT)
            .build();

     public static void writeNerworkToFile(Network network, File file) {
        try {
            if (file.exists()) {
                file.delete();
                System.out.println("Удален файл: " + file.getAbsolutePath());
            }

            file.createNewFile();
            try {
                OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8").newEncoder());
                mapper.writeValue(output, network);
                String info = "Файл нейронной сети записан: " + file.getAbsolutePath();

                System.out.println(info);
            } catch (IOException ex) {
                String error = "Ошибка записи файла нейронной сети [" + network.getName() + "] в файл [" + file.getName() + "], описание ошибки: " + ex.toString();

                System.out.println(error);
            }
        } catch (IOException ex) {
            String error = "Ошибка создания файла: [" + file.getAbsolutePath() + "] ==> " + ex.toString();
            System.out.println(error);

        }

    }
    
    
    public static void readNerworkFromFile(File file) {
        
        if (file.exists()) {
                try {
                    InputStreamReader input = new InputStreamReader(new FileInputStream(file), Charset.forName("UTF-8").newDecoder());
                    Network network = mapper.readValue(input, Network.class);
                    if (network != null) {                        
                        StringBuilder sb = new StringBuilder();
                        sb.append("\n");
                        sb.append(String.format("%-35s %s", "Прочитан файл нейросети: ", "[" + network.getName() + "]")); sb.append("\n");                        
                        System.out.println(sb.toString());                        
                        
                    } else {
                        String error = "Ошибка чтения нейросети из файла [" + file.getName() + "]: функция чтения JSON вернула пустой экземпляр нейросети";
                        System.out.println(error);                        
                    }
                } catch (IOException ex) {
                    String error = "Ошибка чтения нейросети из файл [" + file.getName() + "], описание ошибки: " + ex.toString();                    
                    System.out.println(error);                        
                }
            }
         else {
            String error = "Ошибка чтения графа из файла [" + file.getName() + "]: файл не существует";            
            System.out.println(error);                        
        }
       
    }
    
   

}

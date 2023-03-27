 
package org.ael.nn.platform.layer;


import java.util.List;
import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.vector.Vector;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;


/**
 *
 * @author vaganovdv
 */
public class LayerTest {
    
   private int index = 0;
    
    /**
     * Тест создания слоя нейронов
     */
    @Ignore
    @Test(priority = 1, groups = {"platform-core"})
    public void createLayer() {

        // Создается слой нейронов, который 
        // с номером 0, содержащий 1 нейрон        
        Layer layer = new Layer(0, 3);

        // Создается вектор с разменостью 3 элемента
        Vector v1 = new Vector(4);

        // Добавление вектора 
        layer.addInputVector(v1);

        // печать слоя на терминале
        System.out.println(layer);

    }

    /**
     * Тест 
     */
    @Ignore
    @Test(priority = 2, groups = {"platform-core"})
    public void createMiniNetwork() {
        // Создается входной слой нейронов
        // с номером 0, содержащий 3 нейрона       
        Layer inputLayer = new Layer(0, 3);

        // Создается промежуточный слой нейронов
        // с номером 1, содержащий 1 нейрон       
        Layer innerLayer = new Layer(1, 1);

        //Соединяем два слоя синапсами
        List<Synapse> synapseList = inputLayer.connectToLayer(innerLayer);

        //Выводим информацию
        System.out.println(inputLayer.toString() + "\n" + "соединен с " + innerLayer.toString() + "\n" + "с помощью синапсов:");
        for (int i = 0; i < synapseList.size(); i++) {
            System.out.println("#" + (i+1) + " " + synapseList.get(i).toString());
        }
    }
        
    /**
     * Тест продключения входного вектора
     */
    @Test(priority = 3, groups = {"platform-core"})
    public void connectVectorToLayer() {

        // Формирование слоя номер [0] c 1-м нейроном
        // 
        Layer inputLayer = new Layer(0, 1);
        
        // Формирование входного массива чисел
        double [] inp = {0.7, 0.1, 0.3};
        double [] w  = {-0.3, 3.1, 0.5};
        
        // Сохдание вектора на основе массива чисел
        Vector v = new Vector(inp);
        
        // Подлючение вектора к входному слою
        inputLayer.addInputVector(v);
        
        index = -1;
        inputLayer.getInputSynapses()
                .stream()
                .forEach(synapse
                        -> {
                    index++;
                    synapse.setW(w[index]);
                    System.out.println(synapse);
                }
                );
        
        
        

    }
   
    
}

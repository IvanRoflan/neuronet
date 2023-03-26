package org.ael.nn.platform.layer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.ael.nn.planform.core.neuron.Neuron;
import org.ael.nn.platform.connection.Synapse;
import org.ael.nn.platform.matrix.Matrix;
import org.ael.nn.platform.vector.Vector;

/**
 * Класс для представления слоев нейронной сети 
 * 
 * @author vaganovdv
 */
public class Layer {
  
    /**
     * Номер слоя
     */
    private Integer layaerNumber;

    /**
     * Индекс для печати 
     */
    private  int index = 0;
    
    /**
     * Количество нейронов в слое
     */
    private Integer neuronCount;
    
    /**
     * Уникальный идентификатор слоя нейронной сети
     */
    private String uid;

    /**
     * Список нейронов, содежащихся в слое
     */
    private List<Neuron> neuronList = new ArrayList<>();

    /**
     * Список входных синапсов
     */
    private final List<Synapse> inputSynapses = new ArrayList<>();
    
    
    
    /**
     *  Матрица связей
     */
    private Matrix W;
    
    
      /**
     * Создание слоя нейронов
     *
     * @param layaerNumber количество
     * @param neuronCount
     */
    public Layer(Integer layaerNumber, Integer neuronCount) {

        this.uid = UUID.randomUUID().toString();
        System.out.println("\nФормирование слоя нейронной сети...");
        System.out.println("Параметры слоя: ");
        System.out.println(String.format("%-25s %s", "Идентификатор слоя", "uid = "+uid));
        System.out.println(String.format("%-25s %s", "Номер слоя:", ""+layaerNumber));
        System.out.println(String.format("%-25s %s", "Количесвто нейронов:", ""+neuronCount));
        
        this.layaerNumber = layaerNumber;
        this.neuronCount = neuronCount;

        if (layaerNumber != null && layaerNumber >= 0) {
            // Проверка указания числа нейронов
            if (neuronCount != null && neuronCount != 0) {
                for (int i = 0; i < neuronCount; i++) {
                    Neuron n = new Neuron();
                    neuronList.add(n);
                    System.out.println("слой ["+layaerNumber+"] uid = "+uid +"  добавлен нейрон ==> id = "+n.getUid());
                }
            } else {
                System.out.println("Ошибка создания слоя нейронной сети  uid = [" + uid + "]: количество нейронов нулевое или не указано");
            }
        } else {
            System.out.println("Ошибка создания слоя нейронной сети  uid = [" + uid + "]: номер слоя не указан или отрицательный");
        }
        System.out.println("Формирование слоя ["+layaerNumber+"] ЗАВЕРШЕНО");
        
    }

    @Override
    public String toString() {        
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Слой нейронов ["+layaerNumber+"]\n");
        sb.append(String.format("   %-25s %-10s", "Количество нейронов: ",neuronList.size()+"\n"));                
        return sb.toString();       
    }

    /**
     * Добавление вектора в слой нейронной сети
     *
     * @param vector добавляемый вектор метод
     */
    public void addInputVector(Vector vector) {
        
        // Очистка списка синапсов
        inputSynapses.clear();
        
        if (vector != null && vector.getData().length > 0) {
            // Счетчик  количества синапсов, подключенных к вектору
            //  
            int connectionCount = vector.getData().length * neuronList.size();

            // Создание матрицы синптических связей
            W  = new Matrix (vector.getSize(),neuronList.size() );            
            System.out.println("Формирование [" + connectionCount + "] синапсов от вектора uid = [" + vector.getUid() + "] к слою [" + this.uid + "] номер слоя: " + layaerNumber);

            StringBuilder sb = new StringBuilder();
            sb.append("\n");            
            
            int synapseCount = 0;
            List<Double> v = new ArrayList<>();
            for (int i = 0; i < vector.getData().length; i++) {
                v.add(vector.getData()[i]);                
                System.out.println("Добавлено значение в список "+vector.getData()[i]);
            }
            
            System.out.println("Формирование "+v.size()+" синапсов для входного слоя...");
            
            // Цикл поэлементного обхода вектора 
            for (int i = 0; i < v.size(); i++) {              
                
                sb.append(String.format("%-25s %-5s","Номер строки вектора ==> ", "["+i+"]"));                
                sb.append("\n");
                
               
                  // Цикл обхода нейронов, входящих в слой
                for (int j = 0; j < neuronList.size(); j++) {
                    sb.append(String.format("  %-15s %-7s","номер нейрона:", "["+j+"]")); 
                    sb.append("\n");
                    Synapse synapse = new Synapse();
                    sb.append(String.format("%-35s %12.3f",  "Создан синапс c коэффциентом передачи: ", synapse.getW()));                                        
                    // Чтение веса (коэффциента в синапсе)
                    Double w = synapse.getW();
                    // Заполнение матрицы синаптических связей
                    W.getData()[i][j] = w;                    
                    synapse.setInputUid(vector.getDataIndexUidMap().get(i));
                    synapse.setOutputUid(neuronList.get(j).getUid());                    
                    this.inputSynapses.add(synapse);  
                    synapseCount++;
                    sb.append(String.format("%-35s %-7s %s","["+synapseCount+"]-ая синаптическая связь:", "["+i+" "+j+"]", "uid = "+synapse.getUid()));
                    sb.append("\n");                                       
                }               
            }
            
            System.out.println(sb.toString());
            System.out.println("Добавление вектора размером ["+vector.getData().length+"] элементов  к слою ["+this.layaerNumber+"] завершено" );   
            System.out.println("Входной верктор: \n"+vector);   
            System.out.println("Сформирована матрица синаптических связей: \n"+this.W);   
        }
    }
    
    /**
     * Соединение с другим слоем нейронной сети
     * 
     * @param layer подключаемый слой
     */
    @Deprecated
    public List<Synapse> connectToLayer(Layer layer) {
        List<Synapse> synapsList = new ArrayList<>();
        if (layer != null && layer.getNeuronList().size() != 0) {
            int connectionCount = neuronCount * layer.neuronCount;

            System.out.println("Формирование [" + connectionCount + "] синапсов от слоя = [" + layaerNumber + "] к слою [" + this.uid + "] номер слоя: " + layer.layaerNumber);
            
            StringBuilder sb = new StringBuilder();
            sb.append("\n");

            int synapseCount = 0;
            for (int i = 0; i < neuronCount; i++) {
                System.out.println("Номер входного нейрона: " + neuronList.get(i));
                for (int j = 0; j < layer.getNeuronCount(); j++) {
                    System.out.println("Номер выходного нейрона: " + layer.getNeuronList().get(j).getUid());
                    Synapse synapse = new Synapse();                    
                    synapse.setInputUid(layer.getNeuronList().get(j).getUid());
                    synapse.setOutputUid(neuronList.get(i).getUid());
                    neuronList.get(i).addSynapse(synapse);  
                    layer.getNeuronList().get(j).addSynapse(synapse);                  
                    synapsList.add(synapse);  
                    synapseCount++;
                    sb.append(String.format("%-35s %-7s %s","["+synapseCount+"]-ая синаптическая связь:", "["+i+" "+j+"]", "uid = "+synapse.getUid()));
                    sb.append("\n");
                }
            }
            System.out.println(sb.toString());
        }
        return synapsList;
    }
    
    
      
    /**
     * Подключение входного вектора 
     *
     * @param v
     */
    @Deprecated
    public void connectInputVector(Vector v) {

        if (v != null) {
            System.out.println("Подключение входного вектора uid = " + v.getUid() + " размерность вектора: [" + v.getData().length + "] элементов к слою uid = "+this.uid);

            StringBuilder sb = new StringBuilder();
            sb.append("\n");
            
            // Обход  элементов вектора
            for (int i = 0; i < v.getData().length; i++) {

                sb.append("Подключение к слою элемента вектора с индексом ["+i+"],  размер вектора ["+v.getData().length+"] элементов\n");
                // Обход списка нейронов 
                sb.append("Обход списка ["+neuronList.size()+"] нейронов слоя\n ");
                for (int j = 0; j < neuronList.size(); j++) {
                    // Получение нейрона из списка нейронов слоя                    
                    Neuron neuron = neuronList.get(j);
                    sb.append("Извлечен из слоя нейрон с индексом ["+j+"]\n");
                    // Создание и подключение синапса и элемента вектора
                    Synapse s = new Synapse();    
                    sb.append("Создан синапс ui = "+uid+"\n");
                    
                    // i - номер компоненты вектора
                    // Поучение идентификатора компоненты в векторе
                    String vectorComponentUID = v.getDataIndexUidMap().get(i);
                    s.setInputUid(vectorComponentUID);
                    s.setOutputUid(neuron.getUid());
                    sb.append("Компонента вектора ["+i+"] поключена к нейрону ["+j+"] c использованием синапса uid = "+s.getUid()+"\n");                                        
                    // Добавление синапса в нейрон
                    neuron.addSynapse(s);
                   
                }
            }
            
            System.out.println(sb.toString());
        } else {
            System.out.println("Ошибка: невозможно подключить пустой вектор к слою: uid = " + this.uid);
        }

    }

    public List<Synapse> getInputSynapses() {
        return inputSynapses;
    }

    
    
    
    public Integer getLayaerNumber() {
        return layaerNumber;
    }

    public void setLayaerNumber(Integer layaerNumber) {
        this.layaerNumber = layaerNumber;
    }

    public Integer getNeuronCount() {
        return neuronList.size();
    }

    public void setNeuronCount(Integer neuronCount) {
        this.neuronCount = neuronCount;
    }

    public List<Neuron> getNeuronList() {
        return neuronList;
    }

    public void setNeuronList(List<Neuron> neuronList) {
        this.neuronList = neuronList;
    }

    
    
}

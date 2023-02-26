package org.ael.nn.planform.core.neuron;

import java.util.ArrayList;
import java.util.List;

import org.ael.nn.platform.connection.Synapse;
import org.testng.annotations.Test;

/**
 *
 * @author vaganovdv
 */
public class NeuronTest {

    /**
     * Тест создания нейцрона и соединения между нейронами
     */
    @Test(priority = 1, groups = {"platform-core"})
    public void createSingleNeuron() {
        // Создаем нейрон
        long synapseCount = 3;
        Neuron neuron = new Neuron(synapseCount);

        System.out.println(neuron.toString());

        //Выводим список синапсов
        List<Synapse> synapseList = neuron.getSynapses();
        for (int i = 0; i < synapseList.size(); i++) {
            System.out.println("№" + (i+1) + " " + synapseList.get(i).toString());
        }
    }

    @Test(priority = 1, groups = {"platform-core"})
    public void createNetwork() {
        // Создаем единичный нейрон
        long count = 3;
        Neuron singleN = new Neuron(count);

        // Получаем список синапсов
        List<Synapse> synapseList = singleN.getSynapses();

        // Создаем нейроны входа и настраиваем синапсы
        List<Neuron> neuronList = new ArrayList<>();
        for (int i = 0; i < synapseList.size(); i++) {
            Neuron n = new Neuron();
            synapseList.get(i).setOutputUid(singleN.getUid());
            synapseList.get(i).setInputUid(n.getUid());
            n.addSynapse(synapseList.get(i));
            neuronList.add(n);
        }

        // Выводим информацию о сети
        System.out.println("Выходной нейрон: " + singleN.getUid() + " с " + count + " выходными синапсами:");
        for (int i = 0; i < synapseList.size(); i++) {
            System.out.println("№" + (i+1) + " " + synapseList.get(i).toString());
        }
        System.out.println("Нейроны в которые эти синапсы входят:");
        for (int i = 0; i < neuronList.size(); i++) {
            System.out.println("№" + (i+1) + " - " + "Нейрон " + neuronList.get(i).getUid());
        }
    }

}

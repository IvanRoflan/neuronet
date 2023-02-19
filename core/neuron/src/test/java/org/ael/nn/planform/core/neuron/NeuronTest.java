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
        List<Neuron> neuronList = new ArrayList<>();
        Neuron neuron1 = new Neuron();
    }

    /**
     * Тест умножения вектора на матрицу
     */
    @Test(priority = 2, groups = {"platform-core"})
    public void createSynapse() {

        // Создаем сиинапс
        Synapse synapse = new Synapse();

        // Создаем 2 нейрона для связи
        Neuron n1 = new Neuron();
        Neuron n2 = new Neuron();

        // Устанавливаем вес синапса
        synapse.setW(3.2);

        // Устанавливаем ид нейронов входа и выхода
        synapse.setInputUid(n1.getUid());
        synapse.setOutputUid(n2.getUid());

        // Выводим все поля синапса
        System.out.println(synapse.toString());

    }

    @Test(priority = 2, groups = {"platform-core"})
    public void createSynapseList() {
        // Создаем нейрон выхода
        Neuron outputN = new Neuron();

        // Создадим список нейронов входа
        List<Neuron> inputNList = new ArrayList();

        // Заполняем список
        for (int i = 0; i < 5; i++) {
            inputNList.add(new Neuron());
        }

        // Создадим и заполним список синапсов
        List<Synapse> synapseList = new ArrayList();
        for (int i = 0; i < inputNList.size(); i++) {
            Synapse s = new Synapse();
            s.setOutputUid(outputN.getUid());
            s.setInputUid(inputNList.get(i).getUid());
            synapseList.add(s);
        }

        // Выводим список синапсов
        for (int i = 0; i < synapseList.size(); i++) {
            System.out.println("№" + (i+1) + " " + synapseList.get(i).toString());
        }
    }

}

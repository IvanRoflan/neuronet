package org.ael.nn.planform.core.neuron;

import java.util.ArrayList;
import java.util.List;
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
    public void vectorMatrixMultiplication() {

    }

}

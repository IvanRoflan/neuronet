/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.platform.data.loader;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.testng.annotations.Test;

/**
 *
 * @author developer
 */
public class DataLoaderTest {
    
    // Каталог для разамещения входных образов
    Path path = Paths.get("src", "main", "resources", "input", "images");
    
    // Файлы с входными образами       
    String fileName = "digits.dat";
    
    /**
     * Тест проверки наличия 
     */
    @Test(priority = 1, groups = {"platform-core"})
    public void loadImageFromFileNoPath() {

        DataLoader loader = new DataLoader();
        loader.loadVectorList(path.toString(),fileName, 7);

    }

    
}

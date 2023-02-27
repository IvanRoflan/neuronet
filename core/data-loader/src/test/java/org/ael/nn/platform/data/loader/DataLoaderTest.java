/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.ael.nn.platform.data.loader;

import org.testng.annotations.Test;

/**
 *
 * @author developer
 */
public class DataLoaderTest {
    
    
    String path = "/home/developer/git/gitlab/ael/neuronet-framework/neuronet-core/core/data-loader/src/test/java/test/data";
    String fileName = "digits.dat";
    
    /**
     * Тест проверки наличия 
     */
    @Test(priority = 1, groups = {"platform-core"})
    public void loadImageFromFileNoPath() {

        DataLoader loader = new DataLoader();
        loader.load("opopop[", "");

    }

    /**
     * Тест загрузки изображения из файла
     */
    @Test(priority = 2, groups = {"platform-core"})
    public void loadImageFromFile() {

        DataLoader loader = new DataLoader();
        loader.load(path, fileName);

    }
    
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.ael.nn.platform.vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Класс для предствления вектора
 * 
 * @author vaganovdv
 */
public class Vector {
    
    /**
     * Уникальный идентификатор вектора
     */
    private String uid;
    
    /**
     * Размер массива
     */   
    private int size = 0; 
    
    /**
     * Объявление одномерного массива 
     */
    private double [] data;
    
    private final Map<Integer, String> dataIndexUidMap = new HashMap<>();
    
    public Vector(double[] data) {
        this.uid = UUID.randomUUID().toString();
        int len = data.length;
        
        if (data != null && len != 0) {
            this.data = data;
            // Формирование уникального идентифкатора для каждой компоненты вектора
            for (int i = 0; i < data.length; i++) {                
                dataIndexUidMap.put(i, UUID.randomUUID().toString());
            }                        
        }        
    }

    public Map<Integer, String> getDataIndexUidMap() {
        return dataIndexUidMap;
    }
   
    

    public Vector(Integer size) {
        this.uid = UUID.randomUUID().toString();
        if (size != null && size > 0) {
            this.size = size;
            
            data = new double[size];
            for (int i = 0; i < size; i++) {
                data[i] = (Double) Math.random() * 2 - 1;
            }
            for (int i = 0; i < data.length; i++) {
                dataIndexUidMap.put(i, UUID.randomUUID().toString());
            }
        }
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }
   
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vector: ");

        sb.append(" [" + data.length + "] элементов ");

        if (this.uid != null && !uid.isEmpty()) {
            sb.append(" uid = " + this.uid);
        }
        sb.append("\n{ \n");
        if (data.length > 0) {
            for (int i = 0; i < data.length; i++) {
                sb.append(String.format("%7s", "[" + i + "]"));
                sb.append(String.format("%7s", ""));
                sb.append(String.format("%12.4f", data[i]));
                sb.append(String.format("%7s", ""));
                sb.append(String.format("%-70s", " uid = " + this.dataIndexUidMap.get(i)));
                sb.append("\n");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    
    
    
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.ael.nn.platform.vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author vaganovdv
 */
public class Vector {
    
    /**
     * Уникальный идентификатор вектора
     */
    private String uid;
    
    /**
     * Карта чисел двойной точности, 
     */
    private Map <String, Double> data = new HashMap<>();
    private int size = 0; 
    
    
    public Vector() {
        this.uid = UUID.randomUUID().toString();
    }

    public Vector(Integer size) {
        if (size != null && size > 0)
        {
            this.size = size;
            for (int i = 0; i < size; i++) {
                data.put(UUID.randomUUID().toString(), (Double) Math.random() * 2 - 1 );                
            }            
        }
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Map<String, Double> getData() {
        return data;
    }

    public void setData(Map<String, Double> data) {
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
        sb.append("[ ");
        if (data.size()>0)
        {
             data.forEach( (key,value) -> {
                sb.append(String.format("%7.4f", value ));
                sb.append(" ");             
             });            
        }    
        sb.append("]");
        return sb.toString();
    }
    
    
    
    
   
}

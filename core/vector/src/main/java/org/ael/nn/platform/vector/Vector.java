/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.ael.nn.platform.vector;

import java.util.Locale;
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
     * Размер массива
     */   
    private int size = 0; 
    
    /**
     * Объявление одномерного массива 
     */
    private double [] data;
    

    public Vector(Integer size) {
        this.uid = UUID.randomUUID().toString();
        if (size != null && size > 0)
        {
            this.size = size;
            data = new double [size];
            
            for (int i = 0; i < size; i++) {
                data[i] = (Double) Math.random() * 2 - 1 ;                
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
        sb.append("[ ");
        if (data.length > 0)
        {
            for (int i = 0; i < data.length; i++) {
                sb.append(String.format("%7.4f", data[i]));
                sb.append(String.format("%-3s", ""));             
            }            
        }    
        sb.append("]");
        return sb.toString();
    }
    
    
    
    
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.ael.nn.platform.vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author vaganovdv
 */
public class Vector {
    
    private String uid;
    private List<Double> data = new ArrayList<>();
    private int size = 0; 
    
    
    public Vector() {
        this.uid = UUID.randomUUID().toString();
    }

    public Vector(Integer size) {
        if (size != null && size > 0)
        {
            this.size = size;
            for (int i = 0; i < size; i++) {
                data.add(Math.random() * 2 - 1);
            }            
        }
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
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
            
            for (int i = 0; i < data.size(); i++) {
                sb.append(String.format("%7.4f", data.get(i) ));
                sb.append(" ");
            }            
        }    
        sb.append("]");
        return sb.toString();
    }
    
    
    
    
   
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.type;

/**
 *
 * @author isi
 */
public enum EmailType {
    PERSONNAL("personnel"),PROFESSIONNAL("professionnel");
    
    private final String label;
    
    private EmailType(String label){
        this.label =label;
    }
    
    public String getLabel(){
        return this.label;
    }
    
    
    
}

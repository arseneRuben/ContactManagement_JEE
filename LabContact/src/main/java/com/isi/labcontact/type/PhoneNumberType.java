/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.isi.labcontact.type;

/**
 *
 * @author isi
 */
public enum PhoneNumberType {
    HOUSE("Maison"), WORK("Travail"), CELL("Celulaire");
    
     private final String label;
    
    private PhoneNumberType(String label){
        this.label =label;
    }
    
    public String getLabel(){
        return this.label;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.entity;

import com.isi.labcontact.type.PhoneNumberType;

/**
 *
 * @author isi
 */
public class PhoneNumber {
    private String number;
    private PhoneNumberType type;
    private int contactId;

    public PhoneNumber(String number,  int contactId, PhoneNumberType type) {
        this.number = number;
        this.type = type;
        this.contactId = contactId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public void setType(PhoneNumberType type) {
        this.type = type;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    
    
}

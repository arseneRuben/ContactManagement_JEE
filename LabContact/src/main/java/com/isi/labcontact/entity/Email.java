/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.entity;

import com.isi.labcontact.type.EmailType;

/**
 *
 * @author isi
 */
public class Email {

    private int id;
    private String adress;
    private int contactId;
    private EmailType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public EmailType getType() {
        return type;
    }

    public static EmailType getType(String str) {
        EmailType output = null;
        switch (str) {
            case "PERSONNEL":
                output = EmailType.PERSONNAL;
                break;
            case "PROFESSIONNEL":
                output = EmailType.PROFESSIONNAL;
                break;
        }
        return output;
    }

    public void setType(EmailType type) {
        this.type = type;
    }

    public Email(int id, String adress, EmailType type, int contactId) {
        this.id = id;
        this.adress = adress;
        this.contactId = contactId;
        this.type = type;
    }

    public Email(String adress, int contactId, EmailType type) {
        this.adress = adress;
        this.contactId = contactId;
        this.type = type;
    }

}

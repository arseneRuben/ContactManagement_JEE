/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.entity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isi
 */
public class Contact {

    private int id;
    private String name;
    private List<PhoneNumber> phoneNumbers;
    private List<Email> emails;

    public Contact(int id, String name, List<PhoneNumber> phoneNumbers, List<Email> emails) {
        this.id = id;
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
    }

    public Contact(String name) {
        this.name = name;
        this.phoneNumbers = new ArrayList<PhoneNumber>();
        this.emails = new ArrayList<Email>();
    }

    public Contact(int id, String name) {
        this.id = id;
        this.name = name;
        this.phoneNumbers = new ArrayList<PhoneNumber>();
        this.emails = new ArrayList<Email>();
    }

    public Contact(String name, List<PhoneNumber> phoneNumbers, List<Email> emails) {
        this.name = name;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id + " " + this.name + " " + this.phoneNumbers.size() + " " + this.emails.size();
    }
}

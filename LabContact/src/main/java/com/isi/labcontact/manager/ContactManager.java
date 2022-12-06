/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isi.labcontact.manager;

import com.isi.labcontact.entity.Contact;
import com.isi.labcontact.entity.Email;
import com.isi.labcontact.entity.PhoneNumber;
import com.isi.labcontact.type.EmailType;
import com.isi.labcontact.type.PhoneNumberType;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isi
 */
public class ContactManager {

    private static ArrayList<Contact> contacts = new ArrayList<Contact>();

    static {
        Contact herzt = new Contact("Alain Herzt");
        herzt.getEmails().add( new Email("alain.herzt@polymtl.ca", 1, EmailType.PROFESSIONNAL));
        herzt.getEmails().add( new Email("burno.agard@polymtl.ca", 1, EmailType.PROFESSIONNAL));
        herzt.getPhoneNumbers().add( new PhoneNumber("456987147", 1, PhoneNumberType.CELL));
        herzt.getPhoneNumbers().add( new PhoneNumber("456983347", 1, PhoneNumberType.WORK));

        
        Contact tanenbaum = new Contact("Andrew Tanenbaum");
        tanenbaum.getEmails().add(new Email("agnes.billon@purolator.ca", 2, EmailType.PERSONNAL));
        tanenbaum.getEmails().add(new Email("arielle.billon@google.ca", 2, EmailType.PROFESSIONNAL));
         tanenbaum.getPhoneNumbers().add( new PhoneNumber("8896987147", 1, PhoneNumberType.CELL));
        tanenbaum.getPhoneNumbers().add( new PhoneNumber("9236587777", 1, PhoneNumberType.HOUSE));
        //Contact linux = new Contact("Andrew Tanenbaum");
        //Contact bonde = new Contact("Bonde Lossan");
        
        contacts.add(herzt);
        contacts.add(tanenbaum);

    }
    
    static ArrayList<Contact> findAll() {
        return contacts;
    }
    
    static Contact findById(int id){
        Contact output = null;
        for(Contact c : ContactManager.contacts){
            if(c.getId()==id){
                output = c;
            }
        }
        return output;
    }
    
    public static void main(String args[]){
        System.out.print(ContactManager.findAll());
    }
}

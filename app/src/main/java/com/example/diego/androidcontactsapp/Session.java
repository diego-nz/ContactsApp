package com.example.diego.androidcontactsapp;

import com.example.diego.androidcontactsapp.Models.UserDataModel;

import java.util.ArrayList;


public class Session
{

    private static final Session ourInstance = new Session();

    public static Session getInstance() {
        return ourInstance;
    }

    // public UserDataModel userData;
    public ArrayList<UserDataModel> contactsList;

    private Session()
    {
        if (contactsList == null)
        {
            contactsList = new ArrayList<UserDataModel>();
        }
    }

    public ArrayList<UserDataModel> getContacts() {
        return this.contactsList;
    }

    public void setNewContact(UserDataModel newContact){
        this.contactsList.add(newContact);
    }

    public UserDataModel getContact(int position){
        return this.contactsList.get(position);
    }

    public void deleteContact(int position){
        this.contactsList.remove(position);
    }

    public void updateContact(int position, UserDataModel newContactData){
        this.contactsList.set(position,newContactData);
    }

}

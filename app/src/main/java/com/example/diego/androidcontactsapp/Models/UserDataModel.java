package com.example.diego.androidcontactsapp.Models;

import android.graphics.Bitmap;
import android.net.Uri;

import java.util.List;

/**
 * Created by Diego_Nunez on 26/07/18.
 */

public class UserDataModel
{
    public String Name;
    public String LastName;
    public String Surname;
    public String Phone;
    public String Address;
    public Bitmap Image;

    public UserDataModel(String name, String lastName, String surname, String phone, String address, Bitmap image) {
        Name = name;
        LastName = lastName;
        Surname = surname;
        Phone = phone;
        Address = address;
        Image = image;
    }
}


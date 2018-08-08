package com.example.diego.androidcontactsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.diego.androidcontactsapp.Models.UserDataModel;

public class ContactDetailActivity extends AppCompatActivity {

    private TextView tvNewName,tvNewLastname,tvNewSurname,tvNewPhone,tvNewAddress;
    private ImageView ivNewImageContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        ivNewImageContact = findViewById(R.id.iv_new_image_contact);
        tvNewName = findViewById(R.id.et_new_name);
        tvNewLastname = findViewById(R.id.et_new_lastname);
        tvNewSurname = findViewById(R.id.et_new_surname);
        tvNewPhone = findViewById(R.id.et_new_phone);
        tvNewAddress = findViewById(R.id.et_new_address);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getContactInformation();
    }

    private void getContactInformation(){
        Session appSession = Session.getInstance();
        Bundle extras = getIntent().getExtras();
        int position;
        if (extras != null){
            position = extras.getInt("contactPosition");
            final UserDataModel contact = appSession.getContact(position);
            ivNewImageContact.setImageBitmap(contact.Image);
            tvNewName.setText(contact.Name);
            tvNewLastname.setText(contact.LastName);
            tvNewSurname.setText(contact.Surname);
            tvNewPhone.setText(contact.Phone);
            tvNewAddress.setText(contact.Address);
        }
    }
}

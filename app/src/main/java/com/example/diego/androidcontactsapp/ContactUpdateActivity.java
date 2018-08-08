package com.example.diego.androidcontactsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.androidcontactsapp.Models.UserDataModel;

import java.io.IOException;

public class ContactUpdateActivity extends AppCompatActivity {

    private EditText etNewName,etNewLastname,etNewSurname,etNewPhone,etNewAddress;
    private ImageView ivNewImageContact;
    private Button btnUpdateContact;
    int position;
    private static final int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_update);

        ivNewImageContact = findViewById(R.id.iv_new_image_contact);
        etNewName = findViewById(R.id.et_new_name);
        etNewLastname = findViewById(R.id.et_new_lastname);
        etNewSurname = findViewById(R.id.et_new_surname);
        etNewPhone = findViewById(R.id.et_new_phone);
        etNewAddress = findViewById(R.id.et_new_address);
        btnUpdateContact = findViewById(R.id.btn_update_contact);

        btnUpdateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields())
                    updateContact();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getContactInformation();
    }

    private void imagePicker(){
        Intent imagePicker = new Intent(Intent.ACTION_PICK);
        imagePicker.setType("image/*");
        startActivityForResult(imagePicker, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    try {
                        Bitmap yourSelectedImage =  MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        ivNewImageContact.setImageBitmap(yourSelectedImage);
                    }catch (IOException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    private void getContactInformation(){
        Session appSession = Session.getInstance();
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            position = extras.getInt("contactPosition");
            final UserDataModel contact = appSession.getContact(position);
            ivNewImageContact.setImageBitmap(contact.Image);
            etNewName.setText(contact.Name);
            etNewLastname.setText(contact.LastName);
            etNewSurname.setText(contact.Surname);
            etNewPhone.setText(contact.Phone);
            etNewAddress.setText(contact.Address);
        }
    }

    public boolean validateFields(){
        return true;
    }

    private void updateContact(){
        Bitmap contactImage = ((BitmapDrawable)ivNewImageContact.getDrawable()).getBitmap();
        UserDataModel contact = new UserDataModel(etNewName.getText().toString(),
                etNewLastname.getText().toString(),etNewSurname.getText().toString(),
                etNewPhone.getText().toString(),etNewAddress.getText().toString(), contactImage);
        Session appSession = Session.getInstance();
        appSession.updateContact(position, contact);
        Toast.makeText(ContactUpdateActivity.this,"¡Contact updated!", Toast.LENGTH_LONG).show();
        finish();
    }
}

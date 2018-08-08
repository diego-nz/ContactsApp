package com.example.diego.androidcontactsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.diego.androidcontactsapp.Models.UserDataModel;

import java.io.IOException;

public class NewContactActivity extends AppCompatActivity {

    private EditText etNewName,etNewLastname,etNewSurname,etNewPhone,etNewAddress;
    private ImageView ivNewImageContact;
    private Button btnNewContact;
    private static final int SELECT_PHOTO = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        ivNewImageContact = findViewById(R.id.iv_new_image_contact);
        etNewName = findViewById(R.id.et_new_name);
        etNewLastname = findViewById(R.id.et_new_lastname);
        etNewSurname = findViewById(R.id.et_new_surname);
        etNewPhone = findViewById(R.id.et_new_phone);
        etNewAddress = findViewById(R.id.et_new_address);
        btnNewContact = findViewById(R.id.btn_create_new_contact);

        btnNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (validateInformation())
                     saveContact();
            }
        });

        ivNewImageContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker();
            }
        });

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

    private boolean validateInformation(){
        return true;
    }

    private void saveContact(){
        Bitmap bitmap = ((BitmapDrawable)ivNewImageContact.getDrawable()).getBitmap();
        Session appSession = Session.getInstance();
        UserDataModel contact = new UserDataModel(etNewName.getText().toString(),
                etNewLastname.getText().toString(),etNewSurname.getText().toString(),
                etNewPhone.getText().toString(),etNewAddress.getText().toString(), bitmap);
        appSession.setNewContact(contact);
        Toast toast = Toast.makeText(NewContactActivity.this,"¡Contact saved!", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();

        finish();
    }
}

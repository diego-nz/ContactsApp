package com.example.diego.androidcontactsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.diego.androidcontactsapp.CustomView.ContactListViewAdapter;
import com.example.diego.androidcontactsapp.Models.UserDataModel;

public class ContactListActivity extends AppCompatActivity {

    ListView lvContactList;
    Button btnCreateNewContact;
    ContactListViewAdapter myContactListAdapter;
    TextView tvEmptyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        btnCreateNewContact = findViewById(R.id.btn_create_new_contact);
        tvEmptyListView = findViewById(R.id.tv_empty_listview);

        btnCreateNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });

        getContacts();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Session appSession = Session.getInstance();
        if (appSession.contactsList.size() == 0)
            doAnimation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Session appSession = Session.getInstance();
        if (appSession.contactsList.size() > 0)
            reloadData();
    }

    private void getContacts(){
        final Session appSession = Session.getInstance();
        myContactListAdapter = new ContactListViewAdapter(this, appSession.getContacts());
        lvContactList = findViewById(R.id.lv_contacts);
        lvContactList.setAdapter(myContactListAdapter);

        /*lvContactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContactListActivity.this,ContactDetailActivity.class);
                intent.putExtra("contactPosition",position);
                startActivity(intent);
            }
        });*/
    }

    private void reloadData(){
        myContactListAdapter.notifyDataSetChanged();
        tvEmptyListView.setVisibility(View.GONE);
        lvContactList.setVisibility(View.VISIBLE);
    }

    private void doAnimation(){
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.shake);
        btnCreateNewContact.startAnimation(anim);
        CountDownTimer timer = new CountDownTimer(1800,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                anim.cancel();
            }
        };
        timer.start();
    }
}

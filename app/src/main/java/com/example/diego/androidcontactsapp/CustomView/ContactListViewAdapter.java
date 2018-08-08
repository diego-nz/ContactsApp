package com.example.diego.androidcontactsapp.CustomView;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.diego.androidcontactsapp.ContactDetailActivity;
import com.example.diego.androidcontactsapp.ContactListActivity;
import com.example.diego.androidcontactsapp.ContactUpdateActivity;
import com.example.diego.androidcontactsapp.Models.UserDataModel;
import com.example.diego.androidcontactsapp.NewContactActivity;
import com.example.diego.androidcontactsapp.R;
import com.example.diego.androidcontactsapp.Session;

import java.util.ArrayList;
import java.util.List;

public class ContactListViewAdapter extends ArrayAdapter<UserDataModel> {

    private Context context;
    private ArrayList<UserDataModel> contact;

    public ContactListViewAdapter(@NonNull Context context, @NonNull ArrayList<UserDataModel> contact) {
        super(context, R.layout.activity_contact_list_view_adapter, contact);
        this.context = context;
        this.contact = contact;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.activity_contact_list_view_adapter, null, true);

        LinearLayout llContactRow = rowView.findViewById(R.id.ll_contact_row);

        ImageView ivPicture = rowView.findViewById(R.id.iv_image);
        ImageView ivDelete = rowView.findViewById(R.id.iv_delete);
        ImageView ivEdit = rowView.findViewById(R.id.iv_edit);

        TextView tvName = rowView.findViewById(R.id.tv_name);
        TextView tvPhone = rowView.findViewById(R.id.tv_phone);

        ivPicture.setImageBitmap(contact.get(position).Image);
        tvName.setText(contact.get(position).Name);
        tvPhone.setText(contact.get(position).LastName);

        llContactRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ContactDetailActivity.class);
                intent.putExtra("contactPosition",position);
                context.startActivity(intent);
            }
        });

        ivPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ContactDetailActivity.class);
                intent.putExtra("contactPosition",position);
                context.startActivity(intent);
            }
        });

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"¡Contact deleted!",
                        Toast.LENGTH_LONG).show();
                Session appSession = Session.getInstance();
                appSession.deleteContact(position);
                notifyDataSetChanged();
            }
        });

        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactUpdateActivity.class);
                intent.putExtra("contactPosition",position);
                context.startActivity(intent);
                notifyDataSetChanged();
            }
        });

        return rowView;
    }
}

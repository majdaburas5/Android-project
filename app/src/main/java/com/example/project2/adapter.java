package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.project2.ui.home.Car;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class adapter extends ArrayAdapter<Car> {
    String phone;

    // constructor for our list view adapter.
    public adapter(@NonNull Context context, ArrayList<Car> dataModalArrayList) {
        super(context, 0, dataModalArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // below line is use to inflate the
        // layout for our item of list view.
        View listitemView = convertView;
        if (listitemView == null) {
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.hatsaga, parent, false);
        }

        // after inflating an item of listview item
        // we are getting data from array list inside
        // our modal class.
        Car dataModal = getItem(position);

        // initializing our UI components of list view item.
        TextView type = listitemView.findViewById(R.id.type2);
        TextView model=listitemView.findViewById(R.id.model2);
        TextView year=listitemView.findViewById(R.id.year2);
        Button addtocart=listitemView.findViewById(R.id.Addbtn);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(adapter.this.getContext(), (dataModal.getType()), Toast.LENGTH_SHORT).show();
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectroo-1b18c-default-rtdb.firebaseio.com/");

                databaseReference.child("CurrentUser").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        phone=snapshot.getValue().toString();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String,String> map=new HashMap<>();
                        map.put("type",dataModal.getType());
                        map.put("model",dataModal.getModel());
                        map.put("year",dataModal.getYear());
                        databaseReference.child("Cart").child(phone).push().setValue(map);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        // after initializing our items we are
        // setting data to our view.
        // below line is use to set data to our text view.
        type.setText(dataModal.getType());
        model.setText(dataModal.getModel());
        year.setText(dataModal.getYear());

        // in below line we are using Picasso to
        // load image from URL in our Image VIew.

        // below line is use to add item click listener
        // for our item of list view.
        listitemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on the item click on our list view.
                // we are displaying a toast message.
                Toast.makeText(getContext(), "Item clicked is : " + dataModal.getType(), Toast.LENGTH_SHORT).show();
            }
        });
        return listitemView;
    }
}

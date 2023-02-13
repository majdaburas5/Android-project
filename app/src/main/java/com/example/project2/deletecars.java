package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class deletecars extends AppCompatActivity {


    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectroo-1b18c-default-rtdb.firebaseio.com/");
    private Button Deletebtn;
    EditText idTxt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletecars);
        getSupportActionBar().hide();

        idTxt = (EditText) findViewById(R.id.idTxt);


    }

    public void deletebtn(View view) {
        final String idT=idTxt.getText().toString();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("cars");
        String txt1 = idTxt.getText().toString();

        if(idT.isEmpty())
        {
            Toast.makeText(deletecars.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else
        {
            databaseReference.child(txt1).setValue(null);

            Toast.makeText(deletecars.this, "car deleted", Toast.LENGTH_SHORT).show();
            databaseReference.child("cars").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(idT)){
                        Toast.makeText(deletecars.this,"car is already in",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}

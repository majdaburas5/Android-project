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

public class addcars extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectroo-1b18c-default-rtdb.firebaseio.com/");
    private Button Addbtn;
    private Button Deletebtn;


    EditText txt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcars);
        getSupportActionBar().hide();
     final EditText id=findViewById(R.id.idTxt);
        final EditText type=findViewById(R.id.year);
        final EditText model=findViewById(R.id.model);
        final EditText year=findViewById(R.id.year);
        txt=(EditText) findViewById(R.id.deleteId);

        final Button Addbtn=findViewById(R.id.Addbtn);
        final TextView Deletebtn=findViewById(R.id.Deletebtn);

        Addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final String idTxt=id.getText().toString();
                final String typeTxt=type.getText().toString();
                final String modelTxt=model.getText().toString();
                final String yearTxt=year.getText().toString();

                if(idTxt.isEmpty() || typeTxt.isEmpty() || modelTxt.isEmpty() || yearTxt.isEmpty())
                {
                    Toast.makeText(addcars.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
               else
                {
                    databaseReference.child("cars").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(idTxt)){
                                Toast.makeText(addcars.this,"car is already in",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                databaseReference.child("cars").child(idTxt).child("type").setValue(typeTxt);
                                databaseReference.child("cars").child(idTxt).child("model").setValue(modelTxt);
                                databaseReference.child("cars").child(idTxt).child("year").setValue(yearTxt);
                                //list.add(new addcars(typeTxt,modelTxt,yearTxt));
                                Toast.makeText(addcars.this, "car added successfully.", Toast.LENGTH_SHORT).show();
                                finish();

                            }
                        }
                        public void deletebtn(View view) {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("cars");
                            String txt1 = txt.getText().toString();
                            databaseReference.child(txt1).setValue(null);
                        };

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });



}

    }

package com.example.project2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

public class MainActivity extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://projectroo-1b18c-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in2);
        final EditText phone=findViewById(R.id.phone);
        final EditText password=findViewById(R.id.password);
        final Button loginBtn=findViewById(R.id.loginBtn);
        final TextView registerNowBtn=findViewById(R.id.registerNowBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String phoneTxt=phone.getText().toString();
                final String passwordTxt=password.getText().toString();

                if(phoneTxt.isEmpty() || passwordTxt.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"please enter your mobile or password",Toast.LENGTH_SHORT).show();
                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneTxt))
                            {
                                final String getPassword=snapshot.child(phoneTxt).child("password").getValue(String.class);
                                final String getManager=snapshot.child(phoneTxt).child("manager").getValue(String.class);
                                if (getPassword.equals(passwordTxt)){
                                    if(getManager.equals(("0"))) {
                                        Toast.makeText(MainActivity.this, "Successfully Logged in ", Toast.LENGTH_SHORT).show();
                                        databaseReference.child("CurrentUser").setValue(phoneTxt);

                                        startActivity(new Intent(MainActivity.this,navBar.class));
                                        finish();
                                    }
                                    else{
                                        startActivity(new Intent(MainActivity.this,man.class));
                                        finish();
                                    }
                                }

                                else
                                {
                                    Toast.makeText(MainActivity.this,"Wrong Password ",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this,"Wrong Password ",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }


            }
        });
        registerNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Register.class));
            }
        });

    }

}
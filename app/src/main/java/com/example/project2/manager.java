package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class manager extends AppCompatActivity {
    Button Delete;
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        txt=(EditText) findViewById(R.id.deleteId);
       Delete=(Button) findViewById(R.id.Deletebtn);
    }

    public void addcars(View view) {
        startActivity(new Intent(manager.this,addcars.class));
        finish();
    }

    public void deletebtn(View view) {
        startActivity(new Intent(manager.this,deletecars.class));
        finish();
    }

/*    public void deletebtn(View view) {
       startActivity(new Intent(manager.this,deletecars.class));
        finish();
    }

    public void addcars(View view) {
        startActivity(new Intent(manager.this,addcars.class));
        finish();
    }
*/
    
}

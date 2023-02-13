package com.example.project2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class man extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man);
    }



    public void del(View view) {
        startActivity(new Intent(man.this,deletecars.class));
        finish();
    }

    public void addcar(View view) {
        startActivity(new Intent(man.this,addcars.class));
        finish();
    }
}
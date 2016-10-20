package com.example.the_dagger.learnit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.the_dagger.learnit.R;

/**
 * Created by mayank on 15-10-2016.
 */

public class SignUp extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        Button b = (Button) findViewById(R.id.sign_up);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SignUp.this, "Successfully Signed Up", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SignUp.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}

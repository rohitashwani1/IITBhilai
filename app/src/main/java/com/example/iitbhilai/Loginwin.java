package com.example.iitbhilai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginwin extends AppCompatActivity {
    private EditText mail,assword;
    private Button but;
    private String m,a;
    private ProgressDialog g;
    private FirebaseAuth fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginwin);

        mail = findViewById(R.id.email);
        assword = findViewById(R.id.assword);
        but = findViewById(R.id.button3);
        g = new ProgressDialog(Loginwin.this);
        g.setTitle("Login");
        g.setMessage("Logging into your account");
        getSupportActionBar().hide();
        fb = FirebaseAuth.getInstance();
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m = mail.getText().toString();
                a = assword.getText().toString();
                if(TextUtils.isEmpty(m) || TextUtils.isEmpty(a)){
                    Toast.makeText(Loginwin.this, "Empty Credentials !!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    g.show();
                    fb.signInWithEmailAndPassword(m,a).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            g.dismiss();
                            if(task.isSuccessful()){
                                Toast.makeText(Loginwin.this, "Login Successful !!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Loginwin.this,MainActivity.class));
                                finish();
                            }
                            else{
                                Toast.makeText(Loginwin.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        if(fb.getCurrentUser() != null){
            startActivity(new Intent(Loginwin.this,MainActivity.class));
            finish();
        }
    }
}
package com.example.iitbhilai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.iitbhilai.Model.User;
import com.example.iitbhilai.databinding.ActivitySignuBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signu extends AppCompatActivity {
    ActivitySignuBinding bd;
    FirebaseAuth fb;
    FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = ActivitySignuBinding.inflate(getLayoutInflater());
        setContentView(bd.getRoot());
        fb = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        bd.butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = bd.mail1.getText().toString();
                String assword = bd.assword1.getText().toString();
                String name = bd.name1.getText().toString();
                String branch = bd.branch1.getText().toString();
                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(assword) || TextUtils.isEmpty(name) || TextUtils.isEmpty(branch)){
                    Toast.makeText(signu.this, "Empty Credentials !!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    fb.createUserWithEmailAndPassword(email,assword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user = new User(name,branch,email,assword);
                                db.getReference().child("Users").child(task.getResult().getUser().getUid()).setValue(user);
                                Toast.makeText(signu.this, "Successfully registered!!!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signu.this,Login.class));
                                finish();
                            }
                            else{
                                Toast.makeText(signu.this, task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
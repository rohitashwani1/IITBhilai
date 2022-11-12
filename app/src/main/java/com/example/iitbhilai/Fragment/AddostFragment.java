package com.example.iitbhilai.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.iitbhilai.Model.Ost;
import com.example.iitbhilai.Model.User;
import com.example.iitbhilai.R;
import com.example.iitbhilai.databinding.FragmentAddostBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Date;

public class AddostFragment extends Fragment {
    FragmentAddostBinding bd;
    Uri uri;
    ProgressDialog dialog;
    public AddostFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialog = new ProgressDialog(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bd = FragmentAddostBinding.inflate(inflater, container, false);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Post Uploading");
        dialog.setMessage("Please Wait....");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        bd.imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,10);
            }
        });
        FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    Picasso.get().load(user.getCoveric()).fit().centerCrop().placeholder(R.drawable.logo).into(bd.img);
                    bd.name.setText(user.getName());
                    bd.textView16.setText(user.getBranch());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        bd.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                StorageReference ref = FirebaseStorage.getInstance().getReference().child("Posts").child(FirebaseAuth.getInstance().getUid()).child(new Date().getTime()+"");
                ref.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if(task.isSuccessful()) {
                                    uri = task.getResult();
                                    Ost ost = new Ost();
                                    ost.setOstimg(uri.toString());
                                    ost.setOstedby(FirebaseAuth.getInstance().getUid());
                                    ost.setOstedat(new Date().getTime());
                                    FirebaseDatabase.getInstance().getReference().child("Posts").push().setValue(ost).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            dialog.dismiss();
                                            Toast.makeText(getContext(), "Posted successfully", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                    Toast.makeText(getContext(), "Fail to Post !!!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        return bd.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data.getData() != null){
            uri = data.getData();
            bd.imageView5.setImageURI(uri);
        }
    }
}
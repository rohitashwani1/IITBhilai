package com.example.iitbhilai.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.example.iitbhilai.Adater.UserAdater;
import com.example.iitbhilai.Model.User;
import com.example.iitbhilai.R;
import com.example.iitbhilai.databinding.FragmentSearchBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {
    ArrayList<User> list = new ArrayList<>();
    FragmentSearchBinding bd;
    public SearchFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        bd = FragmentSearchBinding.inflate(inflater, container, false);
        UserAdater ad = new UserAdater(getContext(),list);
        LinearLayoutManager lm = new LinearLayoutManager(getContext());
        bd.userrv.setLayoutManager(lm);
        bd.userrv.setAdapter(ad);
        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds : snapshot.getChildren()){
                    User user = ds.getValue(User.class);
                    if(FirebaseAuth.getInstance().getUid().equals(ds.getKey())){}
                    else{
                        list.add(user);
                    }
                }
                ad.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return bd.getRoot();
    }
}
package com.example.iitbhilai.Adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iitbhilai.Model.Ost;
import com.example.iitbhilai.Model.User;
import com.example.iitbhilai.R;
import com.example.iitbhilai.databinding.MainsamleBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DashboardAdater extends RecyclerView.Adapter<DashboardAdater.ViewHolder>{
    ArrayList<Ost> list;
    Context context;
    MainsamleBinding binding;

    public DashboardAdater(ArrayList<Ost> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DashboardAdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mainsamle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdater.ViewHolder holder, int position) {
        Ost model = list.get(position);
        Picasso.get().load(model.getOstimg()).fit().centerCrop().placeholder(R.drawable.logo).into(binding.imageView1);
        FirebaseDatabase.getInstance().getReference().child("Users").child(model.getOstedby()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    User user = snapshot.getValue(User.class);
                    Picasso.get().load(user.getCoveric()).fit().centerCrop().placeholder(R.drawable.logo).into(binding.img);
                    binding.name.setText(user.getName());
                    binding.about.setText(user.getBranch());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = MainsamleBinding.bind(itemView);
        }
    }
}

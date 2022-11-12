package com.example.iitbhilai.Adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iitbhilai.Model.User;
import com.example.iitbhilai.R;
import com.example.iitbhilai.databinding.UsrsamleBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdater extends RecyclerView.Adapter<UserAdater.ViewHolder>{
    Context context;
    ArrayList<User> list;

    public UserAdater(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup arent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.usrsamle,arent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int osition) {
        User user = list.get(osition);
        Picasso.get().load(user.getCoveric()).fit().centerCrop().placeholder(R.drawable.logo).into(holder.bd.img);
        holder.bd.name.setText(user.getName());
        holder.bd.textView16.setText(user.getBranch());
        holder.bd.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        UsrsamleBinding bd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bd = UsrsamleBinding.bind(itemView);
        }
    }
}

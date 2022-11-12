package com.example.iitbhilai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.iitbhilai.Fragment.AddostFragment;
import com.example.iitbhilai.Fragment.HomeFragment;
import com.example.iitbhilai.Fragment.RofileFragment;
import com.example.iitbhilai.Fragment.SearchFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transaction.replace(R.id.container, new HomeFragment());
        transaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meenu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.rofile:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new RofileFragment());
                transaction.commit();
                break;
            case R.id.addost:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new AddostFragment());
                transaction.commit();
            case R.id.feed:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new HomeFragment());
                transaction.commit();
                break;
            case R.id.usr:
                transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, new SearchFragment());
                transaction.commit();
            case R.id.messd:
                startActivity(new Intent(MainActivity.this,messdio.class));
                break;
            case R.id.messe:
                startActivity(new Intent(MainActivity.this,messena.class));
                break;
            case R.id.cal:
                startActivity(new Intent("android.intent.action.VIEW",Uri.parse("https://openlake.github.io/iitbh-calendar/")));
                break;
            case R.id.los:

                break;
            case R.id.tn:

                break;
            case R.id.ma:

                break;
            case R.id.abo:

                break;
            case R.id.Logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startActivity(new Intent(MainActivity.this,Loginwin.class));
    }
}
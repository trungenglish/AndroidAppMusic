package com.example.meidiamusic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.meidiamusic.fragment.HomeFragment;
import com.example.meidiamusic.fragment.LibraryFragment;
import com.example.meidiamusic.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePageActivity extends AppCompatActivity {
    BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        bottomNav = findViewById(R.id.bottomNav);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
        addEvent();
    }
    void loadFragment(Fragment fmNew){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.fragment_container,fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();
    }
    private void addEvent() {
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //load Fragment
                //khi chon item tren bottomNav
                Fragment fmNew;
                if (item.getItemId() == R.id.bottomHome){
                    fmNew = new HomeFragment();
                    loadFragment(fmNew);
                    return true;
                }
                if (item.getItemId() == R.id.bottomSearch){
                    fmNew = new SearchFragment();
                    loadFragment(fmNew);
                    return true;
                }
                if (item.getItemId() == R.id.bottomLibrary){
                    fmNew = new LibraryFragment();
                    loadFragment(fmNew);
                    return true;
                }
                return true;
            }
        });
    }
}
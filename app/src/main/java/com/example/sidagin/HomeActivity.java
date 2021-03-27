package com.example.sidagin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.sidagin.home.AboutFragment;
import com.example.sidagin.home.DashboardFragment;
import com.example.sidagin.home.ProductAddFragment;
import com.example.sidagin.home.ProfileFragment;
import com.example.sidagin.service.PermManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG ="NOAH" ;
    Bundle myBundle;
    Fragment fragmentSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.myBundle = getIntent().getExtras();
       // String cachet = String.valueOf(getCacheDir());
        PermManager.verifyStoragePermissions(HomeActivity.this);
        //myBundle.putString("cache.dir",cachet);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        fragmentSelected = new DashboardFragment(getApplicationContext());
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentSelected).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()){
                case R.id.nav_profil:
                    fragmentSelected = new ProfileFragment(getApplicationContext());
                    break;
                case R.id.nav_add_product:
                    fragmentSelected = new ProductAddFragment(getApplicationContext());
                    break;
                case R.id.nav_sidagin:
                    Log.d(TAG, "onNavigationItemSelected: ");
                    fragmentSelected = new AboutFragment(getApplicationContext());
                    break;
                default:
                    fragmentSelected = new DashboardFragment(getApplicationContext());
                    break;
            }
            fragmentSelected.setArguments(myBundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,fragmentSelected).commit();
            return true;
        }
    };

    public void aktifitasCrop(){

    }
}
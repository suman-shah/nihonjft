package com.japan.nihonjft;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.japan.nihonjft.databinding.ActivityMainBinding;
import com.japan.nihonjft.ui.account.AccountFragment;
import com.japan.nihonjft.ui.category.CategoryFragment;
import com.japan.nihonjft.ui.leaderboard.LeaderboardFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout using binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Initialize DrawerLayout and NavigationView
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        // Set up the toolbar and toggle for the drawer
        setSupportActionBar(binding.appBarMain.toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, binding.appBarMain.toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Load default fragment (Home)
        setFragment(new CategoryFragment());
        Toast.makeText(MainActivity.this, "Home Fragment Loaded", Toast.LENGTH_SHORT).show();

        // BottomNavigationView handling with if-else
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new CategoryFragment();
                    Toast.makeText(MainActivity.this, "Home Fragment Selected", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_leaderboard) {
                    selectedFragment = new LeaderboardFragment();
                    Toast.makeText(MainActivity.this, "Leaderboard Fragment Selected", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_account) {
                    selectedFragment = new AccountFragment();
                    Toast.makeText(MainActivity.this, "Account Fragment Selected", Toast.LENGTH_SHORT).show();
                }

                // If a valid fragment is selected, replace it
                if (selectedFragment != null) {
                    setFragment(selectedFragment);
                    return true;
                }

                return false;
            }
        });

        // NavigationView handling with if-else
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                if (item.getItemId() == R.id.nav_home) {
                    selectedFragment = new CategoryFragment();
                    Toast.makeText(MainActivity.this, "Home from Drawer Selected", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_leaderboard) {
                    selectedFragment = new LeaderboardFragment();
                    Toast.makeText(MainActivity.this, "Leaderboard from Drawer Selected", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.nav_account) {
                    selectedFragment = new AccountFragment();
                    Toast.makeText(MainActivity.this, "Account from Drawer Selected", Toast.LENGTH_SHORT).show();
                }

                // Replace the fragment and close the drawer
                if (selectedFragment != null) {
                    setFragment(selectedFragment);
                    drawerLayout.closeDrawers();  // Close the drawer after selection
                }

                return true;
            }
        });
    }

    // Fragment replacement method
    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(navigationView)) {
            drawerLayout.closeDrawer(navigationView);
        } else {
            super.onBackPressed();
        }
    }
}

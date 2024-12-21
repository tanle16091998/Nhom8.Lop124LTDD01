package com.example.nhom8lop124ltdd01;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        loadFragment(new Fragment1());
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ThòngTinCaNhan"); // Replace "your_node" with the Firebase node name

        // Add value to Firebase
        addValueToFirebase("Hello Firebase!");


        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.home)
                {
                    fragment = new Fragment1();
                }
                else
                if (item.getItemId() == R.id.account)
                {

                    fragment = new Fragment2();
                }
                else
                if (item.getItemId() == R.id.info)
                {
                    fragment = new Fragment3();
                }

                if (fragment != null) {
                    loadFragment(fragment);
                    return true;
                }

                return false;
            }
        });
    }


    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }
    private void addValueToFirebase(String value) {
        // Push value into the node
        databaseReference.push().setValue(value)
                .addOnSuccessListener(aVoid -> {
                    // Success
                    Toast.makeText(MainActivity.this, "Value added successfully!", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Failure
                    Toast.makeText(MainActivity.this, "Failed to add value: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
    }

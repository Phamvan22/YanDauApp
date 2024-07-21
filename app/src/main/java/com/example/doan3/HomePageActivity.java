package com.example.doan3;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doan3.Adapter.BestRestaurantAdapter;
import com.example.doan3.Adapter.CategoryAdapter;
import com.example.doan3.Model.Category;
import com.example.doan3.Model.Restaurant;
import com.example.doan3.databinding.ActivityHomeBinding;
import com.example.doan3.databinding.ActivityHomePageBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    private ActivityHomePageBinding binding;
    private FirebaseDatabase database;
    private ArrayList<Restaurant> list;

    private RecyclerView recyclerViewCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        binding = ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        list = new ArrayList<>();

        recyclerViewCategory = findViewById(R.id.recycleCategoryFood);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this));

        categories = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(this, categories);
        recyclerViewCategory.setAdapter(categoryAdapter);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("categories");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categories.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Category category = postSnapshot.getValue(Category.class);
                    categories.add(category);
                }
                categoryAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomePageActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        initBestRestaurant();
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initBestRestaurant() {
        Query query = database.getReference("Restaurant").orderByChild("bestRestaurant").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        Restaurant restaurant = issue.getValue(Restaurant.class);
                        if (restaurant != null) {
                            list.add(restaurant);
                            Log.d("RestaurantData", "Name: " + restaurant.getName() + ", Description: " + restaurant.getDescription() + ", BestRestaurant: " + restaurant.isBestRestaurant());
                        } else {
                            Log.d("RestaurantData", "Restaurant data is null");
                        }
                    }
                    if (list.size() > 0) {
                        binding.recycleBestRestaurantFood.setLayoutManager(new LinearLayoutManager(HomePageActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        BestRestaurantAdapter adapter = new BestRestaurantAdapter(list);
                        binding.recycleBestRestaurantFood.setAdapter(adapter);
                    } else {
                        Log.d("RestaurantData", "No restaurants found in the list");
                    }

                } else {
                    Log.d("RestaurantData", "No matching data found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("RestaurantData", "Error: " + error.getMessage());

            }
        });

    }
}
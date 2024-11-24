package com.japan.nihonjft;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.japan.nihonjft.ui.category.CategoryFragment;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {
    private RecyclerView test_recycler_view;
    private List<TestModel> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Use androidx.appcompat.widget.Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(true);

            // Retrieve category index and set title
            int cat_index = getIntent().getIntExtra("cat_index", 0);
            getSupportActionBar().setTitle(DbQuery.g_catList.get(cat_index).getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        test_recycler_view = findViewById(R.id.test_recycler_view);

        // Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        test_recycler_view.setLayoutManager(layoutManager);

        loadTestData();

        TestAdapter adapter = new TestAdapter(testList);
        test_recycler_view.setAdapter(adapter);
    }

    private void loadTestData() {
        // Populate testList with your data
        testList = new ArrayList<>();
        testList.add(new TestModel("1", 100, 60));
        testList.add(new TestModel("2", 50, 75));
        testList.add(new TestModel("3", 200, 90));
        testList.add(new TestModel("4", 250, 105));
        testList.add(new TestModel("5", 300, 120));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            TestActivity.this.finish();

        }
        return super.onOptionsItemSelected(item);
    }
}

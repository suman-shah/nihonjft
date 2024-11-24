package com.japan.nihonjft;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private TestAdapter adapter;
    private Dialog progressDialog;
    private TextView dialogText;

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


            getSupportActionBar().setTitle(DbQuery.g_catList.get(DbQuery.g_selected_cat_index).getName());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        test_recycler_view = findViewById(R.id.test_recycler_view);
        progressDialog = new Dialog(TestActivity.this);
        progressDialog.setContentView(R.layout.dialog_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogText = progressDialog.findViewById(R.id.dialogtext);
        dialogText.setText("Loading Test Data...");

        progressDialog.show();

        // Set up RecyclerView
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        test_recycler_view.setLayoutManager(layoutManager);

        //loadTestData();
        DbQuery.loadTestData(new MyCompleteListener() {
            @Override
            public void onSuccess() {
                TestAdapter adapter = new TestAdapter(DbQuery.g_testList);
                test_recycler_view.setAdapter(adapter);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure() {
                Toast.makeText(TestActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            TestActivity.this.finish();

        }
        return super.onOptionsItemSelected(item);
    }
}

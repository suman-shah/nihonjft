package com.japan.nihonjft.ui.category;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.japan.nihonjft.DbQuery;
import com.japan.nihonjft.R;
import com.japan.nihonjft.TestActivity;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private List<CategoryModel> catList;

    public CategoryAdapter(List<CategoryModel> catList) {
        this.catList = catList;
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View myView;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            myView = inflater.inflate(R.layout.cat_item_layout, parent, false);
        } else {
            myView = convertView;
        }

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click here
                DbQuery.g_selected_cat_index = position;

                Intent intent = new Intent(v.getContext(), TestActivity.class);

                v.getContext().startActivity(intent);

            }
        });

        TextView catName = myView.findViewById(R.id.cat_name);
        TextView catNoOfTests = myView.findViewById(R.id.cat_no_of_tests);
        catName.setText(catList.get(position).getName());
        catNoOfTests.setText(String.valueOf(catList.get(position).getNoOfTests())+" Tests");



        return myView;
    }
}

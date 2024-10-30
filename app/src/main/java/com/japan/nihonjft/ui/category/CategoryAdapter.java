package com.japan.nihonjft.ui.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.japan.nihonjft.R;

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
        TextView catName = myView.findViewById(R.id.cat_name);
        TextView catNoOfTests = myView.findViewById(R.id.cat_no_of_tests);
        catName.setText(catList.get(position).getName());
        catNoOfTests.setText(String.valueOf(catList.get(position).getNoOfTests()));


        return myView;
    }
}

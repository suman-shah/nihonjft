package com.japan.nihonjft;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {
    private List<TestModel> testList;

    public TestAdapter(List<TestModel> testList) {
        this.testList = testList;
    }

    @NonNull
    @Override
    public TestAdapter.TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout here
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item_layout, parent, false);
        return new TestViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.TestViewHolder holder, int position) {

        int progress = testList.get(position).getTopScore();
        holder.setData(position,progress);
    }

    @Override
    public int getItemCount() {
        return testList.size();
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {

        private TextView testNo;
        private ProgressBar testProgressbar;
        private TextView scoreTest;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            testNo = itemView.findViewById(R.id.testNo);
            testProgressbar = itemView.findViewById(R.id.testProgressbar);
            scoreTest = itemView.findViewById(R.id.scoreTest);
        }

        private void setData(int position,int progress) {

            testNo.setText("Test No:"+ String.valueOf(position+1));
            testProgressbar.setProgress(progress);
            scoreTest.setText(String.valueOf(progress)+" %");
            testProgressbar.setProgress(progress);

        }
    }
}

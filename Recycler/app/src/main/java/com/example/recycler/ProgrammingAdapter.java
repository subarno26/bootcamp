package com.example.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgrammingViewHolder> {
    private String[] data;
    public ProgrammingAdapter(String[] names) {
        this.data = names;
    }

    @NonNull
    @Override
    public ProgrammingAdapter.ProgrammingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.listitemview,parent,false);
        return new ProgrammingViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ProgrammingAdapter.ProgrammingViewHolder holder, int position) {
        String title = data[position];
        holder.txt.setText(title);
        holder.numbers.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class ProgrammingViewHolder extends RecyclerView.ViewHolder {
        TextView txt, numbers;
        public ProgrammingViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.textTitle);
            numbers = itemView.findViewById(R.id.number);
        }
    }
}

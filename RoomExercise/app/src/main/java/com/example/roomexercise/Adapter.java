package com.example.roomexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<ViewHolder> {
    Context context;
    List<EmployeeEntity> employeeEntities;
    EmployeeEntity employeeEntity;

    public Adapter(Context context, List<EmployeeEntity> employeeEntities) {
        this.context = context;
        this.employeeEntities = employeeEntities;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(rowLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        employeeEntity = employeeEntities.get(position);
        holder.nameTextView.setText(employeeEntity.getName());
        holder.addressTextView.setText(employeeEntity.getAddress());
        holder.mobileTextView.setText(employeeEntity.getMobile());
        holder.openPoPMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.openPoPMenu);
                popupMenu.inflate(R.menu.menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                deleteRecord(employeeEntity.get(position), position);
                                break;
                            case R.id.update:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });


    }

    private void deleteRecord(final EmployeeEntity employeeEntity, final int position) {

    }

    @Override
    public int getItemCount() {
        return employeeEntities.size();
    }
}

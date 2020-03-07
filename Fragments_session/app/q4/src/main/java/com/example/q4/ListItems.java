package com.example.q4;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class ListItems extends ListFragment {
    private String[] dataArray = {"Apple", "Samsung", "OnePlus"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListAdapter listAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, dataArray);
        setListAdapter(listAdapter);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_items, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("itemPosition", position);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ListDesc descFragment = new ListDesc();
        descFragment.setArguments(bundle);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentTransaction.replace(R.id.description, descFragment);
            fragmentTransaction.addToBackStack("descFrag");
            fragmentTransaction.commit();
        }else{
            fragmentTransaction.replace(R.id.listContainer,descFragment);
            fragmentTransaction.addToBackStack("descFrag");
            fragmentTransaction.commit();
        }
    }

}

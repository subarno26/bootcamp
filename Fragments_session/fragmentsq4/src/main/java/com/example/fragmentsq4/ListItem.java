package com.example.fragmentsq4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

public class ListItem extends ListFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listitems,container,false);
        String[] listItemss = {"Apple","Samsung","OnePlus","Nokia"};
        ListView list = view.findViewById(R.id.list);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,listItemss);
        list.setAdapter(listViewAdapter);
        return view;


    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putInt("itemPosition",position);

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        DescriptionFragment descriptionFragment = new DescriptionFragment();
        descriptionFragment.setArguments(bundle);
        ft.replace(R.id.descFrame, descriptionFragment);
        ft.addToBackStack("descFrag");
        ft.commit();
    }


}

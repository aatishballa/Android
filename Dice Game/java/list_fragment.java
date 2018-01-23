package com.example.mc9509dw.dicefinal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class list_fragment extends ListFragment implements AdapterView.OnItemClickListener {

    protected secondActivity ob;
    protected ArrayList<String> n;
    protected String[] names;
    protected int turn=0;

    public list_fragment() {
    }

    public String[] getNames(){
        return names;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ob= (secondActivity) getActivity();
        n = ob.namelist;
        names = new String[n.size()];
        names= n.toArray(names);
        setListAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_activated_1, names));
        getListView().setOnItemClickListener(this);

        /*
        *
        * FIXED: listview populate
        *
        secondActivity ob= (secondActivity) getActivity();
        ArrayList<String> n = ob.namelist;
        String[] names = new String[n.size()];
        names= n.toArray(names);
        setListAdapter(new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_activated_1, names));
        getListView().setOnItemClickListener(this);
        *
        * */

        /*
         * populate listView with XML resources

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.test, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (turn==position){
            Intent intent = new Intent(getActivity(),gamePlayActivity.class);
            intent.putExtra("current_player",names[position].toString());
            startActivity(intent);
            turn++;
        }else{
            Toast.makeText(getActivity(), "It is " + names[turn] + " turn to play." , Toast.LENGTH_LONG).show();
        }

        gamePlay_fragment fragment = new gamePlay_fragment();
        Bundle bundle = new Bundle();
        bundle.putString("current_player",names[position].toString());
        fragment.setArguments(bundle);
    }

}

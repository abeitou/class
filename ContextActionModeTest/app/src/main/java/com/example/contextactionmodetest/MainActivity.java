package com.example.contextactionmodetest;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {

    private List<Item> items = new ArrayList<>();
    private ItemAdapter adapter;
    private ListView listview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ItemAdapter(MainActivity.this, R.layout.item, items);
        listview = (ListView) findViewById(R.id.list_view);

        init();

        listview.setAdapter(adapter);

        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

        listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            private int n = 0;
            @Override
            public void onItemCheckedStateChanged(android.view.ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    n++;

                    adapter.setNewSelection(position, checked);
                } else {
                    n--;
                    adapter.removeSelection(position);
                }
                mode.setTitle(n + " selected");
            }

            @Override
            public boolean onCreateActionMode(android.view.ActionMode mode, Menu menu) {
                n = 0;
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(android.view.ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(android.view.ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.item_delete:
                        n = 0;
                        adapter.clearSelection();
                        mode.finish();
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(android.view.ActionMode mode) {

            }


        });



    }

    private void init() {
        Item One = new Item("One", R.drawable.ic_launcher);
        items.add(One);
        Item Two = new Item("Two", R.drawable.ic_launcher);
        items.add(Two);
        Item Three = new Item("Three", R.drawable.ic_launcher);
        items.add(Three);
        Item Four = new Item("Four", R.drawable.ic_launcher);
        items.add(Four);
        Item Five = new Item("Five", R.drawable.ic_launcher);
        items.add(Five);

    }





}

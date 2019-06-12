package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Animal> animals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        AnimalAdapter adapter = new AnimalAdapter(MainActivity.this, R.layout.animal, animals);
        final ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animal a = animals.get(position);
                Toast.makeText(MainActivity.this, a.getName(), Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void init() {
        Animal Lion = new Animal("Lion", R.drawable.lion);
        animals.add(Lion);
        Animal Tiger = new Animal("Tiger", R.drawable.tiger);
        animals.add(Tiger);
        Animal Monkey = new Animal("Monkey", R.drawable.monkey);
        animals.add(Monkey);
        Animal Dog = new Animal("Dog", R.drawable.dog);
        animals.add(Dog);
        Animal Cat = new Animal("Cat", R.drawable.cat);
        animals.add(Cat);
        Animal Elephant = new Animal("Elephant", R.drawable.elephant);
        animals.add(Elephant);

    }
}

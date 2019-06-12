package com.example.menutest_xml;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.small:
                textView.setTextSize(10);
                break;
            case R.id.medium:
                textView.setTextSize(16);
                break;
            case R.id.big:
                textView.setTextSize(20);
                break;
            case R.id.toast:
                Toast.makeText(this, "点击普通菜单项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.red:
                textView.setTextColor(getResources().getColor(R.color.red));
                break;
            case R.id.black:
                textView.setTextColor(getResources().getColor(R.color.black));
                break;
        }
        return super.onOptionsItemSelected(item);
    }





}

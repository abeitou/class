package com.example.z.intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button Go = (Button) findViewById(R.id.go);
        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = (EditText) findViewById(R.id.url);
                final String URL = editText.getText().toString();
                Log.e("URL", URL);

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(URL));
                startActivity(intent);
            }
        });
    }
}

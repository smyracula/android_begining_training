package com.small.smyracula.cvapp_1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.small.smyracula.cvapp_1.model.Cv;

import java.io.IOException;
import java.io.InputStream;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button name;
    Cv cv;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        cv = new Gson().fromJson(readJSONFromAsset(), Cv.class);
        name.setText(cv.getName() + " " + cv.getSurname());
        name.setOnClickListener(this);
    }

    private String readJSONFromAsset() {
        String json = "";
        try {
            InputStream inputStream = getAssets().open("cv.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.name: {
                startActivity(DetailActivity.newIntent(this, cv));
            }
        }
    }
}

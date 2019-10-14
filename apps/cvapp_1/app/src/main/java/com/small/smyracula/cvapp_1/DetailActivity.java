package com.small.smyracula.cvapp_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.small.smyracula.cvapp_1.model.Cv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private static final String KEY_CV = "key_cv";
    private TextView name;
    private TextView surname;
    private TextView born;
    private TextView from;
    private TextView university;
    private TextView university_graduated;
    private TextView highSchool;
    private TextView highSchool_graduated;
    private TextView corporate;
    private Cv cv;

    public static Intent newIntent(Context context,@NonNull Cv cv) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(DetailActivity.KEY_CV, cv);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            cv = (Cv) bundle.get(KEY_CV);
        }

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        born = findViewById(R.id.born);
        from = findViewById(R.id.from);
        university = findViewById(R.id.university);
        university_graduated = findViewById(R.id.university_graduated);
        highSchool = findViewById(R.id.highSchool);
        highSchool_graduated = findViewById(R.id.highSchool_graduated);
        corporate = findViewById(R.id.corporate);
        setInfo(cv);

    }

    private void setInfo(Cv cv){

        if(cv != null){
            name.setText(cv.getName());
            surname.setText(cv.getSurname());
            born.setText(String.valueOf(cv.getBorn()));
            from.setText(cv.getFrom());
            university.setText(cv.getUniversity());
            university_graduated.setText(String.valueOf(cv.getUniversity_graduated()));
            highSchool.setText(cv.getHighSchool());
            highSchool_graduated.setText(String.valueOf(cv.getHighSchool_graduated()));
            corporate.setText(cv.getCorporate());
        }
    }
}

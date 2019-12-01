package com.example.cs125finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinnerSetUp();
        Button startGame = findViewById(R.id.startGame);
        intent = new Intent(this, Main2Activity.class);
        View.OnClickListener startGameClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView nameText = findViewById(R.id.playerName);
                intent.putExtra("newPlayerName", nameText.getText().toString());
                startActivity(intent);
                finish();
            }
        };
        startGame.setOnClickListener(startGameClick);
    }
    /** creates spinner to hold topic choices and sets on choice listeners. */
    private void spinnerSetUp() {
        Spinner spinner = findViewById(R.id.gameTopic);
        List<String> list = new ArrayList<>();
        list.add("topic 1");
        list.add("topic 2");
        list.add("topic 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                intent.putExtra("chosenTopic", i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        };
        spinner.setOnItemSelectedListener(listener);
    }
}

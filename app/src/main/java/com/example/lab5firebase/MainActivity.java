package com.example.lab5firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView textView_IntValue, textView_LED1Value, textView_LED2Value;
    private Button buttonLed1, buttonLed2;
    private Switch switchLed1, switchLed2;
    private int count = 0, count1 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_IntValue = (TextView) findViewById(R.id.textView_IntValue);
        textView_LED1Value = (TextView) findViewById(R.id.textView_LED1Value);
        textView_LED2Value = (TextView) findViewById(R.id.textView_LED2Value);

        buttonLed1 = (Button) findViewById(R.id.button_led1);
        buttonLed2 = (Button) findViewById(R.id.button_led2);

        switchLed1 = (Switch) findViewById(R.id.switch_led1);
        switchLed2 = (Switch) findViewById(R.id.switch_led2);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String IntValue = dataSnapshot.child("Integer").getValue().toString();
                textView_IntValue.setText(IntValue);
                String LED1Value = dataSnapshot.child("LED1").getValue().toString();
//                valueLED1 = (int) dataSnapshot.child("LED1").getValue();
                textView_LED1Value.setText(LED1Value);
                String LED2Value = dataSnapshot.child("LED2").getValue().toString();
//                valueLED2 = (int) dataSnapshot.child("LED2").getValue();
                textView_LED2Value.setText(LED2Value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        buttonLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("LED1").setValue(count % 2);
                if (count%2 == 0) {
                    buttonLed1.setText("LED 1 OFF");
                    switchLed1.setChecked(false);
                    Snackbar snackbar = Snackbar.make(view, "LED 1 is OFF", Snackbar.LENGTH_SHORT).setDuration(1000);
                    snackbar.show();
                } else {
                    buttonLed1.setText("LED 1 ON");
                    switchLed1.setChecked(true);
                    Snackbar snackbar = Snackbar.make(view, "LED 1 is ON", Snackbar.LENGTH_SHORT).setDuration(1000);
                    snackbar.show();
                }
                count++;
            }
        });

        buttonLed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("LED2").setValue(count1 % 2);
                if (count1%2 == 0) {
                    buttonLed2.setText("LED 2 OFF");
                    switchLed2.setChecked(false);
                    Snackbar snackbar = Snackbar.make(view, "LED 2 is OFF", Snackbar.LENGTH_SHORT).setDuration(1000);
                    snackbar.show();
                } else {
                    buttonLed2.setText("LED 2 ON");
                    switchLed2.setChecked(true);
                    Snackbar snackbar = Snackbar.make(view, "LED 2 is ON", Snackbar.LENGTH_SHORT).setDuration(1000);
                    snackbar.show();
                }
                count1++;
            }
        });

        switchLed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("LED1").setValue(switchLed1.isChecked());
                if (switchLed1.isChecked()) {
                    buttonLed1.setText("LED 1 ON");
                    Snackbar snackbar = Snackbar.make(view, "LED 1 is ON", Snackbar.LENGTH_SHORT).setDuration(1000);
                    snackbar.show();
                } else {
                    buttonLed1.setText("LED 1 OFF");
                    Snackbar snackbar = Snackbar.make(view, "LED 1 is OFF", Snackbar.LENGTH_SHORT).setDuration(1000);
                    snackbar.show();
                }
            }
        });

        switchLed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("LED2").setValue(switchLed2.isChecked());
                if (switchLed2.isChecked()) {
                    buttonLed2.setText("LED 2 ON");
                    Snackbar snackbar = Snackbar.make(view, "LED 2 is ON", Snackbar.LENGTH_SHORT).setDuration(2000);
                    snackbar.show();
                } else {
                    buttonLed2.setText("LED 2 OFF");
                    Snackbar snackbar = Snackbar.make(view, "LED 2 is OFF", Snackbar.LENGTH_SHORT).setDuration(2000);
                    snackbar.show();
                }
            }
        });




    }
}
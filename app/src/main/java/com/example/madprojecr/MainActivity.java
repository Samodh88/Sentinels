package com.example.madprojecr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    public Button button4;
    public Button button12;

    EditText et_massage1;
    EditText et_massage2;
    EditText et_massage3;
    EditText et_massage4;
    EditText et_massage5;

    Button btn2;
    Button btn12;
    DatabaseReference reference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button4 = (Button) findViewById(R.id.btn3) ;
        button12 = (Button) findViewById(R.id.btn12) ;

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Home.class);
                startActivity(intent); }

        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,View_Details.class);
                startActivity(intent); }

        });
        et_massage1 = findViewById(R.id.et_massage1);
        et_massage2 = findViewById(R.id.et_massage2);
        et_massage3 = findViewById(R.id.et_massage3);
        et_massage4 = findViewById(R.id.et_massage4);
        et_massage5 = findViewById(R.id.et_massage5);

        btn2 = findViewById(R.id.btn2);
        btn12 = findViewById(R.id.btn12);

        reference1 = FirebaseDatabase.getInstance().getReference().child("Delivery");

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDeliveryData();
            }
        }); }
    private void insertDeliveryData(){
        String prnumber = et_massage1.getText().toString();
        String cname = et_massage2.getText().toString();
        String pnumber = et_massage3.getText().toString();
        String address = et_massage4.getText().toString();
        String date = et_massage5.getText().toString();

        Delivery delivery = new Delivery(prnumber,cname,pnumber,address,date);

        reference1.push().setValue(delivery);
        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
    }
}
package com.example.madprojecr;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class payment extends AppCompatActivity {

    public Button button3;


    EditText et_massage6;
    EditText et_massage7;
    EditText et_massage8;
    TextView text_view1;
    TextView text_view2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        et_massage6 = findViewById(R.id.et_massage6);
        et_massage7 = findViewById(R.id.et_massage7);
        et_massage8 = findViewById(R.id.et_massage8);
        text_view1 = findViewById(R.id.text_view1);
        text_view2 = findViewById(R.id.text_view2);
    }

        public void Add(View view){
            EditText et_massage6 =(EditText)findViewById(R.id.et_massage6);
            EditText et_massage7 =(EditText)findViewById(R.id.et_massage7);
            EditText et_massage8 =(EditText)findViewById(R.id.et_massage8);
            TextView text_view1 =(EditText)findViewById(R.id.text_view1);
            TextView text_view2 =(EditText)findViewById(R.id.text_view2);

            int n1 = Integer.parseInt(et_massage6.getText().toString());
            int n2 = Integer.parseInt(et_massage7.getText().toString());
            int n3 = Integer.parseInt(et_massage8.getText().toString());

            int tax = 0;
            if(0<=n1 && n1<=5){
                 tax = 200;
            }
            else if(6<=n1 && n1<=10){
                 tax = 400;
            }
            else if(7<=n1 && n1<=20){
                 tax = 800;
            }
            else{
                text_view1.setText("Wrong");
                text_view2.setText("Wrong"); }
            int TP = tax + n2;
            int bal = n3 - TP;
            text_view1.setText("Rs." + TP +".00");
            text_view2.setText("Rs." + bal +".00");

            button3 = (Button) findViewById(R.id.btn13) ;

            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(payment.this,Home.class);
                    startActivity(intent);
                }

            });
    }
}
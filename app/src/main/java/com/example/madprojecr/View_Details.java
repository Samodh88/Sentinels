package com.example.madprojecr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class View_Details extends AppCompatActivity {

    DatabaseReference reference2;
    private ListView listData;
    List<Delivery> deliveryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);

        listData = (ListView)findViewById(R.id.listData);
        reference2 = FirebaseDatabase.getInstance().getReference("Delivery");

        ValueEventListener event =new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                View_Details(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        };
        reference2.addListenerForSingleValueEvent(event); }
    private void View_Details(DataSnapshot snapshot){
        if(snapshot.exists()){
            ArrayList<String> listDelivery = new ArrayList<>();

            for(DataSnapshot ds:snapshot.getChildren()){
                Delivery delivery = new Delivery(ds.child("prnumber").getValue(String.class),ds.child("cname").getValue(String.class),ds.child("pnumber").getValue(String.class),ds.child("address").getValue(String.class),ds.child("date").getValue(String.class),ds.child("prnumber").getValue(String.class));
                listDelivery.add(delivery.getPrnumber()+"\n"+delivery.getCname()+"\n"+delivery.getPnumber()+"\n"+delivery.getAddress()+"\n"+delivery.getDate()); }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listDelivery);
            listData.setAdapter(arrayAdapter);
        }else{
            Log.d("Delivery","No dara avaible");
        }




        //////////////////////////////////////update.........

        listData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Delivery delivery = deliveryList.get(i);
                showUpdateDetails(delivery.getKey(),delivery.getPrnumber());
                return false;
            }
        });
    }

    private void showUpdateDetails(String key,String prnumber){
        AlertDialog.Builder mDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View mDialogView = inflater.inflate(R.layout.update_dialog,null);

        mDialog.setView(mDialogView);

        EditText etUpdateprnumber = mDialogView.findViewById(R.id.etUpdateprnumber);
        EditText etUpdatecname = mDialogView.findViewById(R.id.etUpdatecname);
        EditText etUpdatepnumber = mDialogView.findViewById(R.id.etUpdatepnumber);
        EditText etUpdateaddress = mDialogView.findViewById(R.id.etUpdateaddress);
        EditText etUpdatedate = mDialogView.findViewById(R.id.etUpdatedate);

        Button btnUpdate = mDialogView.findViewById(R.id.btnUpdate);
        Button btnDelect = mDialogView.findViewById(R.id.btnDelect);

        mDialog.setTitle("Updating" +prnumber+ "record");

        mDialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newprnumber = etUpdateprnumber.getText().toString();
                String newcname = etUpdatecname.getText().toString();
                String newpnumber = etUpdatepnumber.getText().toString();
                String newaddress = etUpdateaddress.getText().toString();
                String newdate = etUpdatedate.getText().toString();

                updateData(key,newprnumber,newcname,newpnumber,newaddress,newdate);

                Toast.makeText(View_Details.this, "Record updated", Toast.LENGTH_SHORT).show();


            }
        });

        btnDelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delectRecord(key);}
        });
    }
    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void delectRecord(String id){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Delivery").child(id);
        Task<Void> mTask = reference2.removeValue();
        mTask.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void avoid) {
                showToast("Deleted "); }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                showToast("Error delecting record");
            }
        });

    }
    private void updateData(String key, String prnumber, String cname, String pnumber, String address, String date){
        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Delivery").child(key);
        Delivery delivery = new Delivery(key,prnumber,cname, pnumber, address, date);
        reference2.setValue(delivery);
    }
}
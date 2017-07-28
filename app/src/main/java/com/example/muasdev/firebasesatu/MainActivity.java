package com.example.muasdev.firebasesatu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    public static String clickedItem;
    TextView tv;
    TextView tv1;
    Button btn;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference myChild = myRef.child("nama");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.value);
        tv1 = (TextView) findViewById(R.id.tvnama);
        btn = (Button) findViewById(R.id.button1);

        // Write a message to the database


        tv.setText("sisini firebase nanti . .. ");

    }

    @Override
    protected void onStart() {
        super.onStart();

        myChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String getdatadarifirebase = dataSnapshot.getValue(String.class);
                tv.setText(getdatadarifirebase);
                tv1.setText(getdatadarifirebase);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myChild.setValue("wisuda");
            }
        });
    }
}

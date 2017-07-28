package com.example.muasdev.firebasesatu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListUtama extends AppCompatActivity {

    ListView mListView;
    private ArrayList<String> mArraySectionLesson = new ArrayList<>();
    public static String clickedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_utama);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("judul");

        //listview
        mListView = (ListView) findViewById(R.id.listview);
        final ArrayAdapter<String> arrayAdapterLesson = new ArrayAdapter<String>(this, R.layout.list_view,
                R.id.label, mArraySectionLesson);
        mListView.setAdapter(arrayAdapterLesson);

        //dapat semua kunci dari data firebase
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {

                    String value = child.getKey();
                    mArraySectionLesson.add(value);
                    arrayAdapterLesson.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //listening to single list item on click
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //selected item
                clickedItem = (String) (mListView.getItemAtPosition(i));

                //launcing new activity on selecting single list item
                Intent a = new Intent(getApplicationContext(), SecondActivity.class);

                //sending data to new activity
                a.putExtra(clickedItem, clickedItem);

                startActivity(a);
            }
        });
    }
}

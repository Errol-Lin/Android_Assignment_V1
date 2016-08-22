package com.memeda.assignmentv2;

import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import android.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.DialogInterface;
import android.widget.ListView;

import com.memeda.assignmentv2.Model.EventModel;
import com.memeda.assignmentv2.Model.Events;
import com.memeda.assignmentv2.Model.SimpleEvents;

public class MainActivity extends AppCompatActivity {

    private ArrayList<SimpleEvents> simpleEvents;
    private ArrayAdapter<SimpleEvents> eventAdapter;
    private ListView eventListView;

    private View dialogView;
    private EditText contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        eventListView = (ListView) findViewById(R.id.list_view);
        simpleEvents = new ArrayList<SimpleEvents>();
        eventAdapter = new ArrayAdapter<SimpleEvents>(this,android.R.layout.simple_list_item_1,simpleEvents);
        eventListView.setAdapter(eventAdapter);

        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_view,null);
        contact = (EditText) dialogView.findViewById(R.id.attendees_text);

//        setListAdapter(new ArrayAdapter<Events>(this,android.R.layout.simple_list_item_1, EventModel.getSingletonInstance().getEvents()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void pickContact(View v){
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    contactPicked(data);
                    break;
            }
        }else{
            Log.e("MainActivity","Failed to pick contact");
        }
    }

    private void contactPicked(Intent data){
        Cursor cursor = null;
        try{
            String name = null;
            Uri uri = data.getData();

            cursor = getContentResolver().query(uri,null,null,null,null);
            cursor.moveToFirst();

            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            name = cursor.getString(nameIndex);
            contact.setText(name);
        }catch(Exception e){

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.add_event){

//            final View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_view,null);

            final EditText startDate = (EditText) this.dialogView.findViewById(R.id.start_date);
            final EditText endDate = (EditText) dialogView.findViewById(R.id.end_date);
            final EditText title = (EditText) dialogView.findViewById(R.id.event_title);
//            final EditText contact = (EditText) dialogView.findViewById(R.id.attendees_text);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Add New Event");
            builder.setView(dialogView);

            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    simpleEvents.add(new SimpleEvents(title.getText().toString()));
                    eventListView.setAdapter(eventAdapter);
                }
            });
            builder.setNegativeButton("Cancel",null);

            AlertDialog alert = builder.create();
            alert.show();

            // display the date picker
            startDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    int mYear = mcurrentDate.get(Calendar.YEAR);
                    int mMonth = mcurrentDate.get(Calendar.MONTH);
                    int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker;
                    mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            i1 = i1+1;
                            startDate.setText(" " + i2 + "/" + i1 + "/" + i);
                        }
                    },mYear,mMonth,mDay);
                    mDatePicker.setTitle("Select Date");
                    mDatePicker.show();
                }
            });

            endDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar mcurrentDate = Calendar.getInstance();
                    int mYear = mcurrentDate.get(Calendar.YEAR);
                    int mMonth = mcurrentDate.get(Calendar.MONTH);
                    int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog mDatePicker;
                    mDatePicker = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            i1 = i1+1;
                            endDate.setText(" " + i2 + "/" + i1 + "/" + i);
                        }
                    },mYear,mMonth,mDay);
                    mDatePicker.setTitle("Select Date");
                    mDatePicker.show();
                }
            });

            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pickContact(view);
                }
            });
        }



        return super.onOptionsItemSelected(item);
    }
}

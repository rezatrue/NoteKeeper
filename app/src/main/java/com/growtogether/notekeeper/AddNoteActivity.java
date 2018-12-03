package com.growtogether.notekeeper;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNoteActivity extends AppCompatActivity  implements View.OnClickListener {

    EditText titleET, noteET;
    Button button;
    TextView setdateTV;
    DatabaseOperation databaseOperation;
    private Calendar calendar;
    private int day,month,year;
    private SimpleDateFormat sdf;
    String uniqueId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        calendar = Calendar.getInstance(Locale.getDefault());
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        uniqueId = String.valueOf(System.currentTimeMillis()/1000);
        Log.i("notes", "uniqueId : "+ uniqueId);

        titleET = findViewById(R.id.etTitle);
        noteET = findViewById(R.id.etNote);
        button = findViewById(R.id.btnAdd);
        setdateTV = findViewById(R.id.tvSetDate);
        setdateTV.setFocusable(true);
        setdateTV.setEnabled(true);
        setdateTV.setClickable(true);
        setdateTV.setFocusableInTouchMode(true);

        String title ="";
        String notes = "";
        String date = "";
        try {
            title = getIntent().getExtras().getString(DatabaseHelper.TABLE_COL_TITLE);
            notes = getIntent().getExtras().getString(DatabaseHelper.TABLE_COL_NOTE);
            date = getIntent().getExtras().getString(DatabaseHelper.TABLE_COL_DATE);
            uniqueId = getIntent().getExtras().getString(DatabaseHelper.TABLE_COL_UID);
        }catch(Exception e){;}

        if(!title.isEmpty()){
            titleET.setText(title);
            noteET.setText(notes);
            setdateTV.setText(date);
            button.setText("Edit");}

        databaseOperation = new DatabaseOperation(this);
        button.setOnClickListener(this);

        setdateTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("notes", "setdateTV");
                selectDate(v);
            }
        });

    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnAdd){
            String title = titleET.getText().toString();
            String notes = noteET.getText().toString();
            String date = setdateTV.getText().toString();


            if(!title.isEmpty() && !notes.isEmpty()) {

                Note note = new Note();
                note.setTitle(title);
                note.setNote(notes);
                note.setDate(date);
                note.setUid(uniqueId);


                if (button.getText().equals("Edit"))
                    databaseOperation.updateContact(note);
                else
                    databaseOperation.addNote(note);

                Intent intent = new Intent(AddNoteActivity.this, NoteListActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Please add all into", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void selectDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,dateListener,year,month,day);
        datePickerDialog.show();

    }

    private DatePickerDialog.OnDateSetListener dateListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    sdf = new SimpleDateFormat("dd/MM/yyyy");
                    calendar.set(year,month,dayOfMonth);
                    String newDate = sdf.format(calendar.getTime());
                    setdateTV.setText(newDate);
                }
            };

}

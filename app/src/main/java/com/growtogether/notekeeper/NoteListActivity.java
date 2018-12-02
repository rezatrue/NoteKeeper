package com.growtogether.notekeeper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.nio.file.attribute.AclEntryFlag;
import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {
    private ListView mListView;
    private ArrayList<Note> notes;
    private Note note;
    private NoteAdapter noteAdapter;
    private DatabaseOperation databaseOperation;

    private AlertDialog.Builder myCustomAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        myCustomAlert = new AlertDialog.Builder(this);

        mListView = findViewById(R.id.listView);
        notes = new ArrayList<>();
        note = new Note();

        databaseOperation =  new DatabaseOperation(this);



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });



        notes = databaseOperation.getAllNotes();
        Log.i("notes", databaseOperation.getAllNotes().size()+"");
        //notes = note.getAllNotes();


        noteAdapter = new NoteAdapter(this,notes);
        mListView.setAdapter(noteAdapter);



        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDialog(view, position);

            }
        });

    }


    public void showDialog(View view, int position) {
         final int index = position;
        myCustomAlert.setTitle("What do you want to do ?");

        myCustomAlert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(NoteListActivity.this, "Deleting..."+ index, Toast.LENGTH_SHORT).show();
                Note note = notes.get(index);
                databaseOperation.deleteContact(note);
                finish();
                startActivity(getIntent());
            }
        });
        myCustomAlert.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(NoteListActivity.this, "Editing..."+ index, Toast.LENGTH_SHORT).show();

                Toast.makeText(getApplicationContext(), "Hello : "+ index, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
                Note note = notes.get(index);
                intent.putExtra(DatabaseHelper.TABLE_COL_TITLE, note.getTitle());
                intent.putExtra(DatabaseHelper.TABLE_COL_NOTE, note.getNote());
                intent.putExtra(DatabaseHelper.TABLE_COL_DATE, note.getDate());
                intent.putExtra(DatabaseHelper.TABLE_COL_UID, note.getUid());
                startActivity(intent);


            }
        });
        myCustomAlert.show();
    }



}

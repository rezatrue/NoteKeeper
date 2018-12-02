package com.growtogether.notekeeper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter  extends ArrayAdapter<Note> {

    private Context context;
    private ArrayList<Note> notes;

    public NoteAdapter(@NonNull Context context, ArrayList<Note>notes) {
        super(context, R.layout.single_row,notes);
        this.context = context;
        this.notes = notes;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.single_row,parent,false);

        TextView titltTV = convertView.findViewById(R.id.tvTitle);
        TextView dateTV = convertView.findViewById(R.id.tvDate);
        TextView noteTV = convertView.findViewById(R.id.tvNote);

        titltTV.setText(notes.get(position).getTitle());
        dateTV.setText(notes.get(position).getDate());
        noteTV.setText(notes.get(position).getNote());

        return convertView;
    }


}

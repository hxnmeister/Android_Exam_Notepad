package com.ua.project.android_exam_notepad;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ua.project.android_exam_notepad.adapters.NoteAdapter;
import com.ua.project.android_exam_notepad.data.DBHelper;
import com.ua.project.android_exam_notepad.databinding.ActivityNotesBinding;

public class NotesActivity extends AppCompatActivity {
    private ActivityNotesBinding binding;
    private DBHelper dbHelper = new DBHelper(NotesActivity.this);
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityNotesBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        noteAdapter = new NoteAdapter(NotesActivity.this, dbHelper.findAll());
        binding.notesRecyclerView.setAdapter(noteAdapter);
        binding.notesRecyclerView.setLayoutManager(new LinearLayoutManager(NotesActivity.this, RecyclerView.VERTICAL, false));
    }
}
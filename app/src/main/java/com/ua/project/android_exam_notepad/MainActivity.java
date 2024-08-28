package com.ua.project.android_exam_notepad;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ua.project.android_exam_notepad.data.DBHelper;
import com.ua.project.android_exam_notepad.databinding.ActivityMainBinding;
import com.ua.project.android_exam_notepad.models.Note;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.saveNoteButton.setOnClickListener(v -> {
            try (DBHelper dbHelper = new DBHelper(MainActivity.this)) {
                String noteText = binding.noteMainTextEditText.getText().toString();
                String noteTitle = binding.noteTitleEditText.getText().toString();

                if(!dbHelper.doesTableExist()) {
                    dbHelper.createTable();
                }

                if(noteText.isEmpty()) {
                    Toast.makeText(MainActivity.this, R.string.empty_main_text, Toast.LENGTH_LONG).show();
                }
                else {
                    dbHelper.insert(Note.builder().title(noteTitle).text(noteText).build());
                }
            }
            catch (Exception e) {
                Log.e("TAG", "onCreate: ", e);
            }
        });
    }
}
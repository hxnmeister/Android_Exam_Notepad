package com.ua.project.android_exam_notepad.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ua.project.android_exam_notepad.MainActivity;
import com.ua.project.android_exam_notepad.R;
import com.ua.project.android_exam_notepad.models.Note;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private final List<Note> noteList;
    private final Activity activity;

    public class NoteHolder extends RecyclerView.ViewHolder {
        private Integer id;
        private TextView noteTitle;
        private TextView noteText;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            noteTitle = itemView.findViewById(R.id.titleTextView);
            noteText = itemView.findViewById(R.id.noteTextView);
        }
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteHolder(LayoutInflater.from(activity)
                .inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note note = noteList.get(position);

        holder.noteTitle.setText(note.getTitle());
        holder.noteText.setText(note.getText());
        holder.id = note.getId();

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("current_note", note);

            activity.startActivity(intent);
            activity.finish();
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }
}

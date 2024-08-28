package com.ua.project.android_exam_notepad.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ua.project.android_exam_notepad.models.Note;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class DBHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    @Getter
    @Setter
    private static String dbName = "notes.db";
    @Getter
    @Setter
    private String tableName = "notes";

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, VERSION);
    }

    public DBHelper(@Nullable Context context, String tableName) {
        super(context, dbName, null, VERSION);

        this.tableName = tableName;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createTable() {
        String query = """
            CREATE TABLE IF NOT EXISTS %s(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title VARCHAR(100),
                text TEXT
            )
        """;

        getWritableDatabase().execSQL(String.format(query, tableName));
    }

    public void dropTable() {
        String query = "DROP TABLE IF EXISTS " + tableName;

        getWritableDatabase().execSQL(query);
    }

    public void insert(@NonNull Note note) {
        ContentValues values = new ContentValues();

        values.put("title", note.getTitle());
        values.put("text", note.getText());

        getWritableDatabase().insert(tableName, null, values);
    }

    public void insertMany(@NonNull List<Note> contactsList) {
        for (Note note : contactsList) {
            insert(note);
        }
    }

    @SuppressLint("Range")
    public List<Note> findAll() {
        String query = "SELECT * FROM " + tableName;
        List<Note> noteList = new ArrayList<>();

        try (Cursor cursor = getReadableDatabase().rawQuery(query, new String[]{})) {
            while (cursor.moveToNext()) {
                noteList.add(Note.builder()
                        .id(cursor.getInt(cursor.getColumnIndex("id")))
                        .title(cursor.getString(cursor.getColumnIndex("title")))
                        .text(cursor.getString(cursor.getColumnIndex("text")))
                        .build());
            }
        }
        catch (Exception e) {
            Log.e("TAG", "db.findAll: ", e);
        }

        return noteList;
    }

    public boolean doesTableExist() {
        String query = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";

        try(Cursor cursor = getReadableDatabase().rawQuery(query, new String[]{tableName})) {
            return cursor.getCount() > 0;
        }
        catch (Exception e) {
            Log.e("TAG", "doesTableExist: ", e);

            return false;
        }
    }
}

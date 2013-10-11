package com.ezveus.notes;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException; // TODO : Catch those ones
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

@SuppressWarnings("unused") // TODO: Remove this one
class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "notes_db";
	private static final String TABLE_NAME = "notes";
	private static final String FIELD_TITLE = "title";
	private static final String FIELD_TEXT = "text";
	private SQLiteDatabase db;
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		this.db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		String cmd = String.format("CREATE TABLE %s (id INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT);", 
				TABLE_NAME, FIELD_TITLE, FIELD_TEXT);
		this.db.execSQL(cmd);
		
		ContentValues cv = new ContentValues();
		
		cv.put(FIELD_TITLE, "First Note");
		cv.put(FIELD_TEXT, "Simply go back to the new note view\nPut a title, a body and store your note using the \"Store note\" button. Your note will then be visible from here");
		this.db.insert(TABLE_NAME, FIELD_TITLE, cv);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO : Select all data and put them in the new database
		db.execSQL(String.format("DROP TABLE IF EXISTS %s", DATABASE_NAME));
		onCreate(db);
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
	}

	public void add_note(String title, String body) {
		ContentValues cv = new ContentValues();
		
		cv.put(FIELD_TITLE, title);
		cv.put(FIELD_TEXT, body);
		db.insert(TABLE_NAME, FIELD_TITLE, cv);
	}
	
	void add_note(String title, String body, Activity activity) {
		add_note(title, body);
		if (activity != null) {
			Toast.makeText(activity, "Adding a note", Toast.LENGTH_SHORT).show();
		}
	}
	
	List<String[]> get_all_notes() {
		List<String[]> notes = new ArrayList<String[]>();
		Cursor cursor = db.query(TABLE_NAME, new String[]{ FIELD_TITLE, FIELD_TEXT }, null, null, null, null, null);
		
		if (cursor.moveToFirst()) {
			do {
				notes.add(new String[] { cursor.getString(0), cursor.getString(1) });
			} while (cursor.moveToNext());
		}
		return notes;
	}
	
	static String[] getFields() {
		return new String[]{ FIELD_TITLE, FIELD_TEXT };
	}
}

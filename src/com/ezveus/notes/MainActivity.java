package com.ezveus.notes;

import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.ezveus.notes.DatabaseHelper;

@SuppressWarnings("unused") // TODO : Remove this one
public class MainActivity extends Activity {
	EditText title_edit;
	EditText text_edit;
	Button view_store_button;
	Button store_note_button;
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		title_edit = (EditText)findViewById(R.id.title_edit);
		text_edit = (EditText)findViewById(R.id.text_edit);
		view_store_button = (Button)findViewById(R.id.view_store_button);
		store_note_button = (Button)findViewById(R.id.store_note_bt);
		db = new DatabaseHelper(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onViewStored(View v) {
		Intent intent = new Intent(this, ViewNotesActivity.class);
		startActivity(intent);
	}
	
	public void onStoreNote(View v) {
		if (db.add_note(title_edit.getText().toString(), text_edit.getText().toString())) {
			title_edit.setText("");
			text_edit.setText("");
			Toast.makeText(this, R.string.note_added, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(this, R.string.validation_error, Toast.LENGTH_SHORT).show();
		}
	}
}

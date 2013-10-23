package com.ezveus.notes;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewNotesActivity extends Activity {
	public static final String TITLE = "com.ezveus.intent.title";
	public static final String VALUE = "com.ezveus.intent.value";
	private ListView layout;
	private List<String> notes;
	private DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_notes);

		layout = (ListView)findViewById(R.id.layout_view_notes);
		db = new DatabaseHelper(this);
		notes = db.get_all_notes_titles();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, 
				android.R.id.text1, notes);
		layout.setAdapter(adapter);
		
		layout.setOnItemClickListener(new OnItemClickListener() {
			@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				String itemValue = (String)layout.getItemAtPosition(position);
				Intent intent = new Intent(getApplicationContext(), ViewNoteActivity.class);
				String value;

				if ((value = db.get_note(itemValue)) != null) {
					intent.putExtra(TITLE, itemValue);
					intent.putExtra(VALUE, value);
				}
				startActivity(intent);
			}
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view_notes, menu);
		return true;
	}
}

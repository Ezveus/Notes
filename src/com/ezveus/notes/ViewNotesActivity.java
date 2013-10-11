package com.ezveus.notes;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewNotesActivity extends Activity {
	LinearLayout layout;
	TextView no_notes_text_view;
	List<TextView> notes;
	DatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_notes);
		layout = (LinearLayout)findViewById(R.id.layout);
		no_notes_text_view = (TextView)findViewById(R.id.no_notes_text_view);
		db = new DatabaseHelper(this);
		notes = new ArrayList<TextView>();

		fillNotes();
		fillLayout();
	}

	private void fillNotes() {
		List<String[]> notes_values = db.get_all_notes();
		
		if (notes != null && notes_values != null) {
			for (String[] values : notes_values) {
				TextView tv = new TextView(this);
				String tmp = values[0];
				
				tmp += "\n";
				tmp += values[1];

				tv.setText(tmp);
				notes.add(tv);
			}
		}
	}
	
	private void fillLayout() {
		if (notes != null && notes.size() > 0) {
			no_notes_text_view.setVisibility(View.INVISIBLE);
			showNotes();
		} else {
			no_notes_text_view.setVisibility(View.VISIBLE);
		}
	}
	
	private void showNotes() {
		for (TextView tv : notes) {
			layout.addView(tv);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_notes, menu);
		return true;
	}
}

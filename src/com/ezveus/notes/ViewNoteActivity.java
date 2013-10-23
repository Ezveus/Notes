package com.ezveus.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ViewNoteActivity extends Activity {
	private TextView title;
	private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_note);

		Intent intent = getIntent();
		title = (TextView)findViewById(R.id.title);
		text = (TextView)findViewById(R.id.text);

		title.setText(intent.getStringExtra(ViewNotesActivity.TITLE));
		text.setText(intent.getStringExtra(ViewNotesActivity.VALUE));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.view_note, menu);
		return true;
	}

}

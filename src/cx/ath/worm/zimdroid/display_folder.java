package cx.ath.worm.zimdroid;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.NavUtils;

import cx.ath.worm.zimdroid.ZimNotepad;
import cx.ath.worm.zimdroid.ZimNotepad.ZimPage;



public class display_folder extends Activity {
	
	FolderViewAdapter pages_adapter = null;
	ListView lstFiles;
	Button btnRootNote;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_folder);
    	lstFiles = (ListView) findViewById(R.id.lstFiles);
    	if(lstFiles == null)
    		Log.i("ZimDroid", "lstFiles NULL!");
        Log.i("ZimDroid", "inicjalizacja activity");
        Bundle bundle = this.getIntent().getExtras();
        String notepad_file = bundle.getString("notepad_path");
        Log.i("ZimDroid", "Notepad path is "+notepad_file);
        String folder_inside = bundle.getString("folder_inside");
        if(notepad_file == null)
        	finish();
        ZimNotepad notepad = new ZimNotepad(notepad_file);
        if(folder_inside == null) {
        	this.pages_adapter = new FolderViewAdapter(this, R.layout.rowfile, notepad.pages);
       		Log.i("ZimDroid", "Pages in root dir: "+String.valueOf(notepad.pages.size()));
        	lstFiles.setAdapter(this.pages_adapter);
        	pages_adapter.notifyDataSetChanged();
        	lstFiles.setVisibility(ListView.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_folder, menu);
        return true;
    }

    
}

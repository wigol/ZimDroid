package cx.ath.worm.zimdroid;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v4.app.NavUtils;
import android.util.Log;


public class add_notepad extends Activity {
	final String PREFS_NAME = "ZimDroidSetv1";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notepad);
    	
        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        
        final Button btnAdd = (Button) findViewById(R.id.btnAdd);
        final EditText edtName = (EditText) findViewById(R.id.edtName);
        btnAdd.setOnClickListener(new View.OnClickListener() {	
		@Override
		public void onClick(View v) {
			//Adding text... (test)
			Log.i("ZimDroid", "Dodawanie rekordu");
			SharedPreferences.Editor editor = settings.edit();
			String temp;
			temp = settings.getString("list_of_notepads", "NONE");
			if(temp.equals(""))
				editor.putString("list_of_notepads", edtName.getText().toString());
			else
				editor.putString("list_of_notepads", temp+";"+edtName.getText().toString());
			editor.commit();
			Intent refresh = new Intent(v.getContext(), select_notepad.class);
			startActivity(refresh);
		}
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_notepad, menu);
        return true;
    }

    
}

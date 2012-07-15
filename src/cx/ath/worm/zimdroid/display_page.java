package cx.ath.worm.zimdroid;

import com.petebevin.markdown.MarkdownProcessor;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import us.feras.mdv.MarkdownView;

public class display_page extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);
        MarkdownView mdView = (MarkdownView) findViewById(R.id.mdView);
        //mdView.loadMarkDownData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_page, menu);
        return true;
    }

    
}

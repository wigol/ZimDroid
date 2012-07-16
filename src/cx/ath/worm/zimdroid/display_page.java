package cx.ath.worm.zimdroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import com.petebevin.markdown.MarkdownProcessor;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.support.v4.app.NavUtils;

import us.feras.mdv.MarkdownView;

public class display_page extends Activity {

	public static String iso (String s)	{
		byte bytes[] = EncodingUtils.getBytes(s,"iso-8859-1");
		return new String(bytes);
	}
	
	public static String utf (String s)	{
		byte bytes[] = EncodingUtils.getBytes(s,"utf-8");
		return new String(bytes);
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_page);
        Bundle bundle = this.getIntent().getExtras();
        File path = new File(bundle.getString("page_path"));
        if(path == null)
        	finish();
        else {
        	WebView mdView = (WebView) findViewById(R.id.mdView);
        	try {
        		String Content = "";
        		BufferedReader reader = new BufferedReader(new FileReader(path));
				String line;
				while( (line = reader.readLine()) != null) {
					line = line.replaceAll("([*][*]([A-Za-z0-9: +-]+)[*][*])","<b>$2</b>");
					line = line.replaceAll("([/][/]([A-Za-z0-9: +-]+)[/][/])","<em>$2</em>");
					line = line.replaceAll("([=]{6}([\\w: +-]+)[=]{6})","<h3>$2</h3>");
					line = line.replaceAll("(^[•] ([\\w: +-]+))","<li>$2</li>");
					Content+=line;
					Content+="<br />";
				}
				//Ooh, so dirty:
				//Content = Content.replace("<br /><br />", "<br />");
				Content = Content.replace("<br /><h3>", "<h3>");
				Content = Content.replace("</h3><br />", "</h3>");
				reader.close();
				mdView.getSettings().setDefaultTextEncodingName("utf-8");
				mdView.loadData(Content, "text/html", "utf-8");
        	}
        	catch(IOException e) {
        		Log.i("ZimDroid", "Cannot read file:"+path.getPath());
        	}
        	
        }       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_display_page, menu);
        return true;
    }

    
}

package cx.ath.worm.zimdroid;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.text.format.Time;
import android.util.Log;

public class ZimNotepad {
	
	class ZimPage {
		private String Name = "";
		private String Content = "";
		private File Location;
		private ArrayList<ZimPage> children = new ArrayList<ZimPage>();
		
		ZimPage(String name, File location) {
			File fullPath = new File(location.getPath()+"/"+name+".txt");
			Location = fullPath;
			Name = name;
			try {
				if(fullPath.exists()) {
					BufferedReader reader = new BufferedReader(new FileReader(fullPath));
					String line;
					while( (line = reader.readLine()) != null) {
						Content += line;
						Content += "\n";
					}
					reader.close();
				}
				else {
					fullPath.createNewFile();
					Content = getDefaultContent();
					FileWriter writer = new FileWriter(fullPath);
					writer.append(Content);
					writer.close();
				}
			}
			catch(IOException e) {
				Log.i("ZimDroid", "Failed to read page "+fullPath+".");
			}
			}
			
		public int getChildren() {
			File kat = new File(Location.getPath()+Name);
			children.clear();
			if(kat.exists() && kat.isDirectory()) {
				String[] pliki = kat.list();
				for(String apped : pliki) {
					if(apped.contains(".txt"))
						children.add(new ZimPage(apped.substring(0,apped.length()-4), kat));
				}
				return children.size();
			}
			else
				return 0;
			}
		
		private String getDefaultContent() {
			Time today = new Time(Time.getCurrentTimezone());
			today.setToNow();
			return("==="+Name+"===\n"+"Created on "+today.format("%d %B %G")+"\n");
		}
		
		public String getName() {
			return Name;
		}
	}
	
	public String name = null;
	public String version = null;
	public String home = null;
	public File zimFile;
	public ArrayList<ZimPage> pages = new ArrayList<ZimNotepad.ZimPage>();
	
	ZimNotepad(String PathToFile) {
		zimFile = new File(PathToFile);
		try {
			//loading *.zim file, reading settings.
			BufferedReader reader = new BufferedReader(new FileReader(zimFile));
			Map<String,String> prefs = new HashMap<String, String>();
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains("=") && line.split("=").length > 1) {
					prefs.put(line.split("=")[0], line.split("=")[1]);
				}
			}
			reader.close();
			name = prefs.get("name");
			version = prefs.get("version");
			home = prefs.get("home");
			
			//loading pages at top level:
			File kat = zimFile.getParentFile();
			if(kat.exists() && kat.isDirectory()) {
				String[] pliki = kat.list();
				for(String apped : pliki) {
					if(apped.contains(".txt"))
						pages.add(new ZimPage(apped.substring(0,apped.length()-4), kat));
				}
			}
		}
		catch(FileNotFoundException e) {
			Log.i("ZimDroid", "EXC: Cannot find "+zimFile.getPath()+". ");
		}
		catch(IOException e) {
			Log.i("ZimDroid", "EXC: IOException while reading zim file. ");
		}
	}
}

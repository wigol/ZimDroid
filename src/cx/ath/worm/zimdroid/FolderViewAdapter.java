package cx.ath.worm.zimdroid;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import cx.ath.worm.zimdroid.ZimNotepad.ZimPage;

public class FolderViewAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<ZimPage> mPages = new ArrayList<ZimPage>();
	
	public FolderViewAdapter(Context context, int res, ArrayList<ZimPage> Pages) {
		mContext = context;
		mPages = Pages;
	}
	
	@Override
	public int getCount() {
		return mPages.size();
	}

	@Override
	public Object getItem(int position) {
		return mPages.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	public static class ViewHolder {
		public TextView pagename;
		public Button nextdir;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View row = convertView;
		final ViewHolder holder = new ViewHolder(); 
		if(row == null) {
			Log.i("ZimDroid", "Creating row layout...");
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row=inflater.inflate(R.layout.row, parent, false);
			holder.pagename = (TextView)row.findViewById(R.id.txtPage);
			holder.nextdir = (Button)row.findViewById(R.id.btnEnter);
			holder.pagename.setText(mPages.get(position).getName());
			Log.i("ZimDroid", "Adding item: "+mPages.get(position).getName());
			if(mPages.get(position).getChildren() == 0) {
				holder.nextdir.setVisibility(0x8); //GONE
			}
		}
		return row;
	}
	
}
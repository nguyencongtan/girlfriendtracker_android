package com.tanguco.girlfriendtracker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		 	List<Sms> lstSms = new ArrayList<Sms>();
		    Sms objSms = new Sms();
		    Uri message = Uri.parse("content://sms/");
		    ContentResolver cr = this.getContentResolver();

		    Cursor c = cr.query(message, null, null, null, null);
		    this.startManagingCursor(c);
		    int totalSMS = c.getCount();

		    if (c.moveToFirst()) {
		        for (int i = 0; i < totalSMS; i++) {

		            objSms = new Sms();
		            objSms.setId(c.getString(c.getColumnIndexOrThrow("_id")));
		            objSms.setAddress(c.getString(c
		                    .getColumnIndexOrThrow("address")));
		            objSms.setMsg(c.getString(c.getColumnIndexOrThrow("body")));
		            objSms.setReadState(c.getString(c.getColumnIndex("read")));
		            objSms.setTime(new Date (c.getString(c.getColumnIndexOrThrow("date"))));
		            if (c.getString(c.getColumnIndexOrThrow("type")).contains("1")) {
		                objSms.setFolderName("inbox");
		            } else {
		                objSms.setFolderName("sent");
		            }

		            lstSms.add(objSms);
		            c.moveToNext();
		        }
		    }
		    // else {
		    // throw new RuntimeException("You have no SMS");
		    // }
		    c.close();
		    Toast.makeText(this, "hacked", 10000).show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}

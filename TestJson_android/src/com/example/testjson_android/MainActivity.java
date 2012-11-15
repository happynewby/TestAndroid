package com.example.testjson_android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static String url = "http://api.androidhive.info/contacts/";
	 
    // JSON Node names
    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_MOBILE = "mobile";
    private static final String TAG_PHONE_HOME = "home";
    private static final String TAG_PHONE_OFFICE = "office";
    JSONArray contacts = null;
    Vector<DataElement> dataE;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        dataE = new Vector<DataElement>();
        // Creating JSON Parser instance
        JSONParser jParser = new JSONParser();
 
        // getting JSON string from URL
        JSONObject json = jParser.getJSONFromUrl(url);
 
        try {
            // Getting Array of Contacts
            contacts = json.getJSONArray(TAG_CONTACTS);
 
            // looping through All Contacts
            for(int i = 0; i < contacts.length(); i++){
                JSONObject c = contacts.getJSONObject(i);
 
                // Storing each json item in variable
                String id = c.getString(TAG_ID);
                String name = c.getString(TAG_NAME);
                String email = c.getString(TAG_EMAIL);
                String address = c.getString(TAG_ADDRESS);
                String gender = c.getString(TAG_GENDER);
 
                // Phone number is agin JSON Object
                JSONObject phone = c.getJSONObject(TAG_PHONE);
                String mobile = phone.getString(TAG_PHONE_MOBILE);
                String home = phone.getString(TAG_PHONE_HOME);
                String office = phone.getString(TAG_PHONE_OFFICE);
                Log.w("ID", ""+id);
                Log.w("name", ""+name);
                Log.w("email", ""+email);
                Log.w("address", ""+address);
                Log.w("gender", ""+gender);
                Log.w("phone", ""+phone);
                Log.w("moblie", ""+mobile);
                Log.w("home", ""+home);
                Log.w("office", ""+office);
                
                dataE.add(new DataElement(id, name, email, address, mobile));
                
                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();
 
                // adding each child node to HashMap key => value
                map.put(TAG_ID, id);
                map.put(TAG_NAME, name);
                map.put(TAG_EMAIL, email);
                map.put(TAG_PHONE_MOBILE, mobile);
 
                // adding HashList to ArrayList
                contactList.add(map);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
 
         Adapter _adapter = new Adapter();
         ListView lv = (ListView)findViewById(R.id.list_item);
//         lv.setAdapter(_adapter);
//        ListAdapter adapter = new SimpleAdapter(this, contactList,
//                R.layout.activity_main,
//                new String[] { TAG_NAME, TAG_EMAIL, TAG_PHONE_MOBILE }, new int[] {
//                        R.id.name, R.id.email, R.id.mobile });
// 
//        setListAdapter(adapter);
// 
//        // selecting single ListView item
//        ListView lv = getListView();
// 
//        // Launching new screen on Selecting Single ListItem
//        lv.setOnItemClickListener(new OnItemClickListener() {
// 
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                    int position, long id) {
//                // getting values from selected ListItem
//                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();
//                String cost = ((TextView) view.findViewById(R.id.email)).getText().toString();
//                String description = ((TextView) view.findViewById(R.id.mobile)).getText().toString();
// 
//                // Starting new intent
//                Intent in = new Intent(getApplicationContext(), SingleMenuItemActivity.class);
//                in.putExtra(TAG_NAME, name);
//                in.putExtra(TAG_EMAIL, cost);
//                in.putExtra(TAG_PHONE_MOBILE, description);
//                startActivity(in);
//            }
//        });
        
    }
    public class Adapter extends BaseAdapter{

		public int getCount() {
			// TODO Auto-generated method stub
			return dataE.size();
		}

		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			TextView _id, _name, _email, _address, _moblie;
			View view = convertView;
			if(view == null){
				LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = vi.inflate(R.layout.datalist, null);
			}
			_id = (TextView)view.findViewById(R.id.tvid);
			_name = (TextView) view.findViewById(R.id.tvname);
			_address = (TextView)view.findViewById(R.id.tvaddress);
			_moblie = (TextView)view.findViewById(R.id.tvmobile);
			_email = (TextView)view.findViewById(R.id.tvemail);
			
			_id.setText(""+dataE.elementAt(position).getID());
			_name.setText(""+dataE.elementAt(position).getName());
			_address.setText(""+dataE.elementAt(position).getAddress());
			_email.setText(""+dataE.elementAt(position).getEmail());
			_moblie.setText(""+dataE.elementAt(position).getMoblie());
			return view;
		}
    	
    }
}

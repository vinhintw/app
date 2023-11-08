package com.example.a1025_readfromasset_json_11131415;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity_11131415 extends AppCompatActivity {

	RecyclerView recyclerView;
	MyAdapter adapter;
	private ArrayList<String> bookName;
	private ArrayList<String> bookTitle;
	private ArrayList<String> author;
	private ArrayList<String> price;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		recyclerView = (RecyclerView) findViewById(R.id.recylerView);
		LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
		recyclerView.setLayoutManager(layoutManager);
		adapter = new MyAdapter(MainActivity_11131415.this, bookName, bookTitle, author, price );
		recyclerView.setAdapter(adapter);

		try {
			JSONObject obj = new JSONObject(loadJsonData()); //load from asset
			JSONArray booklist = obj.getJSONArray("booklist");


			for (int i=0;i<booklist.length();i++){
				JSONObject bookDetail = booklist.getJSONObject(i);
				bookName.add(bookDetail.getString("name"));
				bookTitle.add(bookDetail.getString("title"));
				author.add(bookDetail.getString("author"));
				price.add(bookDetail.getString("price"));
			}
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}

	}

	String loadJsonData(){
		String jsonData = null;
		try {
			InputStream data = this.getAssets().open("book.json");
			int size = data.available();
			byte[] buffer = new byte[size];
			data.read(buffer);
			data.close();
			jsonData = new String(buffer, "UTF-8");

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return jsonData;
	}



}
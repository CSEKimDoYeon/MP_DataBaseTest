package com.example.kimdoyeon.mp01_09_201402324_lab09;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kimdoyeon.mp01_09_201402324_lab09.MovieDB.MovieDbOpenHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add_btn;
    ListView listView;
    ListViewAdapter adapter;

    String sort = "_id";

    private MovieDbOpenHelper mDbOpenHelper; // 메인 DB를 오픈 할 Helper.
    public ArrayList<MovieOBj> mArray = new ArrayList<MovieOBj>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.listview1);

        mDbOpenHelper = new MovieDbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        showDatabase(sort);

        add_btn = (Button) findViewById(R.id.btn_add);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                int isEdit = 0;

                intent.putExtra("movie_Name", "");
                intent.putExtra("year", "");
                intent.putExtra("dir_Name", "");
                intent.putExtra("score", "");
                intent.putExtra("country", "");

                intent.putExtra("isEdit", isEdit);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onRestart() {
        showDatabase(sort);
        super.onRestart();
    }


    public void showDatabase(String sort) {
        mArray.clear();
        //Toast.makeText(getApplicationContext(), "Show Database", Toast.LENGTH_SHORT).show();
        adapter = new ListViewAdapter(this, R.layout.listview_item, mArray);

        Cursor iCursor = mDbOpenHelper.sortColumn(sort);
        Log.e("showDatabase", "DB Size: " + iCursor.getCount());

        String string = "";
        int count = 0;
        while (iCursor.moveToNext()) {
            //String delete_keyword = iCursor.getString(iCursor.getColumnIndex("DELETE_KEYWORD"));
            int id = iCursor.getInt(0);
            String movie_Name = iCursor.getString(1);
            String year = iCursor.getString(2);
            String dir_Name = iCursor.getString(3);
            String score = iCursor.getString(4);
            String country = iCursor.getString(5);

            MovieOBj obj = new MovieOBj(id,movie_Name,year,dir_Name,score,country);

            string = String.format("_Id:%d,movie_Name:%s, year:%s, dir_Name:%s, " + "score:%s, country:%s",id, movie_Name, year, dir_Name, score,country);

            mArray.add(obj);
            adapter.addItem(id,movie_Name,year,dir_Name,score,country);
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {

                MovieOBj mObj = (MovieOBj) parent.getItemAtPosition(position);

                int _id = mObj.getId();
                String movie_Name = mObj.getMovie_Name();
                String year = mObj.getYear();
                String dir_Name = mObj.getDir_Name();
                String score = mObj.getScore();
                String country = mObj.getCountry();

                int isEdit = 1;

                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                intent.putExtra("_Id", _id);
                intent.putExtra("movie_Name", movie_Name);
                intent.putExtra("year", year);
                intent.putExtra("dir_Name", dir_Name);
                intent.putExtra("score", score);
                intent.putExtra("country", country);

                intent.putExtra("isEdit", isEdit);
                startActivity(intent);
            }
        });

    }

}

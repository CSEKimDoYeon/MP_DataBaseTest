package com.example.kimdoyeon.mp01_09_201402324_lab09;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kimdoyeon.mp01_09_201402324_lab09.MovieDB.MovieDbOpenHelper;

public class EditActivity extends AppCompatActivity{
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    EditText et5;

    Button btn_save;
    Button btn_update;
    Button btn_delete;

    Intent intent;
    int _id;
    String movie_Name;
    String year;
    String dir_Name;
    String score;
    String country;
    int isEdit;

    private MovieDbOpenHelper mDbOpenHelper; // 메인 DB를 오픈 할 Helper.

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        intent = getIntent();
        if(intent != null) {
             movie_Name = intent.getExtras().getString("movie_Name");
             year = intent.getExtras().getString("year");
             dir_Name = intent.getExtras().getString("dir_Name");
             score = intent.getExtras().getString("score");
             country = intent.getExtras().getString("country");
             isEdit = intent.getExtras().getInt("isEdit");
        }

        et1 = (EditText) findViewById(R.id.et_movie_Name);
        et2 = (EditText) findViewById(R.id.et_year);
        et3 = (EditText) findViewById(R.id.et_dir_Name);
        et4 = (EditText) findViewById(R.id.et_score);
        et5 = (EditText) findViewById(R.id.et_country);

        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbOpenHelper = new MovieDbOpenHelper(getApplicationContext());
                mDbOpenHelper.open();
                mDbOpenHelper.create();
                mDbOpenHelper.insertColumn(et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),
                        et4.getText().toString(),et5.getText().toString());
                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbOpenHelper = new MovieDbOpenHelper(getApplicationContext());
                mDbOpenHelper.open();
                mDbOpenHelper.create();
                mDbOpenHelper.updateColumn(_id ,et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),
                        et4.getText().toString(),et5.getText().toString());
                Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDbOpenHelper = new MovieDbOpenHelper(getApplicationContext());
                mDbOpenHelper.open();
                mDbOpenHelper.create();
                mDbOpenHelper.deleteColumnForName(intent.getExtras().getString("movie_Name"));

                Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        if(isEdit == 1){
            btn_save.setVisibility(View.GONE);

            _id = intent.getExtras().getInt("_Id");
            et1.setText(movie_Name);
            et2.setText(year);
            et3.setText(dir_Name);
            et4.setText(score);
            et5.setText(country);
        }
    }
}

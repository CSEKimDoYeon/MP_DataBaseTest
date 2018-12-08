package com.example.kimdoyeon.mp01_09_201402324_lab09.MovieDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MovieDbOpenHelper {
    private static final String DATABASE_NAME = "InnerDatabase_Movie(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mCtx;

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){

            db.execSQL(MovieDataBases.CreateDB._CREATE0);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+MovieDataBases.CreateDB._TABLE);
            onCreate(db);
        }
    }

    public MovieDbOpenHelper(Context context){
        this.mCtx = context;
    }

    public MovieDbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }

    // Insert DB
    public long insertColumn(String movie_Name, String year, String dir_Name , String score, String country){
        ContentValues values = new ContentValues();
        values.put(MovieDataBases.CreateDB.MOVIE_NAME, movie_Name);
        values.put(MovieDataBases.CreateDB.YEAR, year);
        values.put(MovieDataBases.CreateDB.DIR_NAME, dir_Name);
        values.put(MovieDataBases.CreateDB.SCORE, score);
        values.put(MovieDataBases.CreateDB.COUNTRY, country);
        return mDB.insert(MovieDataBases.CreateDB._TABLE, null, values);
    }

    // Update DB
    public boolean updateColumn(int id, String movie_Name, String year, String dir_Name , String score, String country){
        ContentValues values = new ContentValues();
        values.put(MovieDataBases.CreateDB._ID, id);
        values.put(MovieDataBases.CreateDB.MOVIE_NAME, movie_Name);
        values.put(MovieDataBases.CreateDB.YEAR, year);
        values.put(MovieDataBases.CreateDB.DIR_NAME, dir_Name);
        values.put(MovieDataBases.CreateDB.SCORE, score);
        values.put(MovieDataBases.CreateDB.COUNTRY, country);
         return mDB.update(MovieDataBases.CreateDB._TABLE, values, "_id=" + id, null) > 0;

        /*mDB.execSQL("UPDATE MovieDataBases SET MOVIE_NAME =" + "\"" +movie_Name + "\"" +", YEAR ="+ "\"" +year + "\""+ ",DIR_NAME =" +
                "\""+ dir_Name +"\""+ ",SCORE ="+ "\""+score +"\""+ ",COUNTRY ="+"\""+country +"\""+" WHERE _id="+id+";");*/


        //return mDB.update(MovieDataBases.CreateDB._TABLE, values, null, null) > 0;
    }

    // Delete All
    public void deleteAllColumns() {

        mDB.delete(MovieDataBases.CreateDB._TABLE, null, null);
    }

    // Delete DB
    public boolean deleteColumn(long id){
        return mDB.delete(MovieDataBases.CreateDB._TABLE, "_id="+id, null) > 0;
    }

    public boolean deleteColumnForName(String name){ // 리스트에서 누른 아이템과 일치하는 데이터를 삭제한다.
        mDB.execSQL("DELETE FROM Movies WHERE MOVIE_NAME=" +  "\""+name+  "\"");
        return true;
        //return mDB.delete(DeleteKeywordDataBases.CreateDB._TABLE, "DELETE_KEYWORD="+keyword, null) > 0;
    }

    // Select DB
    public Cursor selectColumns(){
        return mDB.query(MovieDataBases.CreateDB._TABLE, null, null, null, null, null, null);
    }

    // sort by column
    public Cursor sortColumn(String sort){
        Cursor c = mDB.rawQuery( "SELECT * FROM Movies ORDER BY " + sort + ";", null);
        return c;
    }
}

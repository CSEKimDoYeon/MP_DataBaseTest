package com.example.kimdoyeon.mp01_09_201402324_lab09.MovieDB;

import android.provider.BaseColumns;

public class MovieDataBases {
    public static final class CreateDB implements BaseColumns {
        public static final String MOVIE_NAME = "movie_name";
        public static final String YEAR = "year";
        public static final String DIR_NAME = "dir_name";
        public static final String SCORE = "score";
        public static final String COUNTRY = "country";

        public static final String _TABLE = "Movies";

        public static final String _CREATE0 = "create table if not exists "+ _TABLE +"("
                + _ID+" integer primary key autoincrement, "
                + MOVIE_NAME+" text not null , "
                + YEAR +" text not null , "
                + DIR_NAME +" text not null , "
                + SCORE +" text not null , "
                + COUNTRY +" text not null );";
    }
}

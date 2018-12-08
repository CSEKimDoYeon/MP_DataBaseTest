package com.example.kimdoyeon.mp01_09_201402324_lab09;

public class MovieOBj {

    public int id;
    public String movie_Name;
    public String year;
    public String dir_Name;
    public String score;
    public String country;

    public MovieOBj(){

    }
    public MovieOBj(int id, String movie_name, String year, String dir_name, String score, String country) {
        this.id=id;
        this.movie_Name = movie_name;
        this.year = year;
        this.dir_Name = dir_name;
        this.score = score;
        this.country = country;
    }

    public String getMovie_Name() {
        return movie_Name;
    }

    public void setMovie_Name(String movie_Name) {
        this.movie_Name = movie_Name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDir_Name() {
        return dir_Name;
    }

    public void setDir_Name(String dir_Name) {
        this.dir_Name = dir_Name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

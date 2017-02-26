package com.justinzyh.film.mvp.bean;

/**
 * Created by justinzyh on 2016/11/14.
 * email:tojustinzyh@163.com
 * QQ:1476156127
 */

public class CategoryFilmViewBean {

    private int AllFilm;//数据

    public int getAllFilm() {
        return AllFilm;
    }

    public void setAllFilm(int allFilm) {
        AllFilm = allFilm;
    }

    private String filmCountry;//地区
    private String filmCategory;//类型

    public String getFilmCountry() {
        return filmCountry;
    }

    public void setFilmCountry(String filmCountry) {
        this.filmCountry = filmCountry;
    }

    public String getFilmCategory() {
        return filmCategory;
    }

    public void setFilmCategory(String filmCategory) {
        this.filmCategory = filmCategory;
    }

}

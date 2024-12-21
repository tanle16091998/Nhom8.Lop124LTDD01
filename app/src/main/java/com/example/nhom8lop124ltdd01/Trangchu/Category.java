package com.example.nhom8lop124ltdd01.Trangchu;

import java.util.List;



public class Category {
    private String nameCategory;
    private List<FILM> films;

    public Category(String nameCategory, List<FILM> films) {
        this.nameCategory = nameCategory;
        this.films = films;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<FILM> getFilms() {
        return films;
    }

    public void setFilms(List<FILM> films) {
        this.films = films;
    }
}

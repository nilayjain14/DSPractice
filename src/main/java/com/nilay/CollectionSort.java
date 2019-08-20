package com.nilay;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionSort {

    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        Movie m1 = new Movie("DDLG",new BigDecimal(4.12));
        Movie m2 = new Movie("GOT",new BigDecimal(4.79));
        Movie m3 = new Movie("haha",new BigDecimal(3.12));

        movies.add(m1);
        movies.add(m2);
        movies.add(m3);

        //sort will in asc order
        Collections.sort(movies,(p1,p2) -> p1.rating.compareTo(p2.rating));

        for(Movie r : movies){
            System.out.println(r.name);
            System.out.println(r.rating);
        }
    }
}

class Movie{
    public String name;
    public BigDecimal rating;

    Movie(String name, BigDecimal rating){
        this.name = name;
        this.rating = rating;

    }

}

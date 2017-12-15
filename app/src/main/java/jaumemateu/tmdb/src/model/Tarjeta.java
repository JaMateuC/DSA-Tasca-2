package jaumemateu.tmdb.src.model;

/**
 * Created by usuario on 15/12/2017.
 */

public class Tarjeta {

    private String title;
    private String image;

    public Tarjeta(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

}

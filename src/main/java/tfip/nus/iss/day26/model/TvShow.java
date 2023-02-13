package tfip.nus.iss.day26.model;

import org.bson.Document;

public class TvShow {

    private int id;

    private String name;

    private float rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static TvShow createDocument(Document doc) {
        TvShow show = new TvShow();
        show.setId(doc.getInteger("id"));
        show.setName(doc.getString("name"));
        Document rating = (Document) doc.get("rating");
        try {
            if (rating.getDouble("average") == null) {
                show.setRating(Float.NaN);
            } else {
                show.setRating(rating.getDouble("average").floatValue());
            }
        } catch (Exception e) {
            float f = rating.getInteger("average") + 0f;
            show.setRating(f);
        }
        return show;
    }

}

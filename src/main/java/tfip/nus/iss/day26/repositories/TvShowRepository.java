package tfip.nus.iss.day26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TvShowRepository {

    public static final String FIELD_TYPE = "type";
    public static final String COLLECTION_TVSHOWS = "tv";

    @Autowired
    private MongoTemplate template;

    public List<Document> findTvShowsByType(String type) {
        return findTvShowsByType(type, 20, 0);
    }

    public List<Document> findTvShowsByType(String type, int limit, int skip) {
        Criteria criteria = Criteria.where(FIELD_TYPE).regex(type, "i");

        Query query = Query.query(criteria)
                .with(Sort.by(Direction.DESC, "rating.average"))
                .limit(limit)
                .skip(skip);

        query.fields()
                .exclude("_id")
                .include("id", "name", "rating.average");

        return template.find(query, Document.class, COLLECTION_TVSHOWS);

    }

    public List<String> listType() {
        return template.findDistinct(new Query(), "type", COLLECTION_TVSHOWS, String.class);
    }

}

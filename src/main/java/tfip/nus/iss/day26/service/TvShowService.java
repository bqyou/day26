package tfip.nus.iss.day26.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfip.nus.iss.day26.model.TvShow;
import tfip.nus.iss.day26.repositories.TvShowRepository;

@Service
public class TvShowService {

    @Autowired
    private TvShowRepository tvShowRepo;

    public List<TvShow> findAllTvShowByType(String type) {
        List<TvShow> list = tvShowRepo.findTvShowsByType(type)
                .stream()
                .map(v -> TvShow.createDocument(v))
                .toList();
        return list;
    }

    public List<String> listType() {
        return tvShowRepo.listType()
                .stream()
                .sorted()
                .collect(Collectors.toList());
    }
}

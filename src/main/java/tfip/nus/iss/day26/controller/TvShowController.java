package tfip.nus.iss.day26.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tfip.nus.iss.day26.model.TvShow;
import tfip.nus.iss.day26.service.TvShowService;

@Controller
@RequestMapping(path = "/")
public class TvShowController {

    @Autowired
    private TvShowService tvShowSvc;

    @GetMapping
    public String listTypes(Model model) {
        List<String> types = tvShowSvc.listType();
        model.addAttribute("types", types);
        return "index";
    }

    @GetMapping(path = "/{type}")
    public String listShows(@PathVariable String type, Model model) {
        model.addAttribute("type", type);
        List<TvShow> shows = tvShowSvc.findAllTvShowByType(type);
        model.addAttribute("shows", shows);
        return "shows";
    }

}

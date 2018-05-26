package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String searchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        model.addAttribute("columns", ListController.columnChoices);
        int jobCounter = 0;

        if (searchType.equals("all")) {
            jobCounter = JobData.findByValue(searchTerm).size();
            String jobCounterString = Integer.toString(jobCounter) + " Result(s)";
            model.addAttribute("jobs", JobData.findByValue(searchTerm));
            model.addAttribute("jobCounter", jobCounterString);
        }
        else {
            jobCounter = JobData.findByColumnAndValue(searchType, searchTerm).size();
            String jobCounterString = Integer.toString(jobCounter) + " Result(s)";
            model.addAttribute("jobs", JobData.findByColumnAndValue(searchType, searchTerm));
            model.addAttribute("jobCounter", jobCounterString);
        }

        return "search";
    }

}

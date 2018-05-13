package com.components.controller;


import com.components.database.models.Word;
import com.components.jacksonfilters.View;
import com.components.service.WordsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class WordController {


    @Autowired
    private WordsService wordsService;

    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/getWords/{groupId}", method = RequestMethod.POST)
    public ResponseEntity<?> getWords(@PathVariable long groupId) {
        List<Word> words = wordsService.getRandomWords(groupId);
        return ResponseEntity.ok(words);
    }


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public void addWord(@RequestBody Word word) {
        wordsService.save(word);
    }


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.PUT)
    public void editWord(@RequestBody Word word) {
        wordsService.update(word);
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.DELETE)
    public void deleteWord(@RequestBody Word word) {
        wordsService.delete(word);
    }

}

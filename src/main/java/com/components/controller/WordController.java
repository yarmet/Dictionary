package com.components.controller;


import com.components.database.models.Word;
import com.components.database.models.WordGroup;
import com.components.jacksonfilters.View;
import com.components.service.WordsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WordController {


    @Autowired
    private WordsService wordsService;


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    public ResponseEntity<?> getWords(WordGroup wordGroup) {
        return ResponseEntity.ok(wordsService.getRandomWords(wordGroup));
    }


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public ResponseEntity<?> addWord(@RequestBody Word word) {
        return ResponseEntity.ok(wordsService.save(word));
    }


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.PUT)
    public ResponseEntity<?> editWord(@RequestBody Word word) {
        return ResponseEntity.ok(wordsService.update(word));
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteWord(@RequestBody Word word) {
        wordsService.delete(word);
        return ResponseEntity.ok().build();
    }

}

package com.components.controller;


import com.components.database.models.Word;
import com.components.jacksonfilters.View;
import com.components.service.WordsService;
import com.components.utils.Utils;
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


    @RequestMapping(value = "userIsLogged", method = RequestMethod.POST)
    public ResponseEntity<?> roles() {
        return ResponseEntity.ok(Utils.userIsLogged());
    }


    @JsonView(View.WordView.class)
    @RequestMapping(value = "/getWords", method = RequestMethod.GET)
    public ResponseEntity<?> getWords() {
        return ResponseEntity.ok(wordsService.getRandomWords());
    }


    @JsonView(View.WordView.class)
    @RequestMapping(value = "getLastWords", method = RequestMethod.GET)
    public ResponseEntity<?> getLastWords() {
        return ResponseEntity.ok(wordsService.getLastRandomWords());
    }


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "addWord", method = RequestMethod.POST)
    public ResponseEntity<?> addWord(@RequestBody Word word) {
        return ResponseEntity.ok(wordsService.save(word));
    }


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "editWord", method = RequestMethod.POST)
    public ResponseEntity<?> editWord(@RequestBody Word word) {
        return ResponseEntity.ok(wordsService.update(word));
    }


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "deleteWord", method = RequestMethod.POST)
    public ResponseEntity<?> deleteWord(@RequestBody Word word) {
        wordsService.delete(word);
        return ResponseEntity.ok().build();
    }

}

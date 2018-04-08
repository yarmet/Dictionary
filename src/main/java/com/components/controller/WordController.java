package com.components.controller;


import com.components.database.models.Word;
import com.components.database.models.WordGroup;
import com.components.jacksonfilters.View;
import com.components.service.WordsService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
public class WordController {


    @Autowired
    private WordsService wordsService;


    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/getWords/{groupId}", method = RequestMethod.POST)
    public ResponseEntity<?> getWords(@PathVariable int groupId) {
        return ResponseEntity.ok(wordsService.getRandomWords(groupId));
    }


    @JsonView(View.WordView.class)
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public ResponseEntity<?> addWord(@RequestBody Word word ) {
        wordsService.save(word);
        return ResponseEntity.ok().build();
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

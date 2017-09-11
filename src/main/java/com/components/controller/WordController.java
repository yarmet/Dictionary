package com.components.controller;


import com.components.models.Word;
import com.components.service.WordsService;
import com.components.utils.Utils;
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

    @RequestMapping(value = "/getWords", method = RequestMethod.GET)
    public ResponseEntity<?> getWords() {
        return ResponseEntity.ok(wordsService.getRandomWords());
    }

    @RequestMapping(value = "getLastWords", method = RequestMethod.GET)
    public ResponseEntity<?> getLastWords() {
        return ResponseEntity.ok(wordsService.getLastRandomWords());
    }

    @RequestMapping(value = "getUserRoles", method = RequestMethod.POST)
    public ResponseEntity<?> roles() {
        return ResponseEntity.ok(Utils.getUserRoles());
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "addWord", method = RequestMethod.POST)
    public ResponseEntity<?> addWord(@RequestBody Word word) {
        wordsService.save(word);
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "editWord", method = RequestMethod.POST)
    public ResponseEntity<?> editWord(@RequestBody Word word) {
        wordsService.update(word);
        return ResponseEntity.ok().build();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "deleteWord", method = RequestMethod.POST)
    public ResponseEntity<?> deleteWord(@RequestBody Word word) {
        wordsService.delete(word);
        return ResponseEntity.ok().build();
    }


}

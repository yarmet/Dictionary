package com.components.controller;


import com.components.database.models.WordGroup;
import com.components.service.WordGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WordGroupsController {

    @Autowired
    private WordGroupService wordGroupService;


    @RequestMapping(value = "groups", method = RequestMethod.GET)
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok(wordGroupService.getAllByCurrentUser());
    }

    @RequestMapping(value = "groups", method = RequestMethod.POST)
    public ResponseEntity<?> addGroup(WordGroup wordGroup) {
        wordGroupService.save(wordGroup);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "groups", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGroup(WordGroup wordGroup) {
        wordGroupService.delete(wordGroup);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "groups", method = RequestMethod.PUT)
    public ResponseEntity<?> updateGroup(WordGroup wordGroup) {
        wordGroupService.update(wordGroup);
        return ResponseEntity.ok().build();
    }


}

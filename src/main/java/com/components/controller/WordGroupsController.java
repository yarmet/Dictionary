package com.components.controller;

import com.components.database.models.WordGroup;
import com.components.jacksonfilters.View;
import com.components.service.WordGroupService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordGroupsController {

    @Autowired
    private WordGroupService wordGroupService;

    @JsonView(View.WordGroupView.class)
    @RequestMapping(value = "groups", method = RequestMethod.GET)
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok(wordGroupService.getGroupsByCurrentUser());
    }

    @RequestMapping(value = "groups", method = RequestMethod.POST)
    public ResponseEntity<?> addGroup(@RequestBody WordGroup wordGroup) {
        return ResponseEntity.ok(wordGroupService.save(wordGroup));
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

package com.components.controller;

import com.components.requestModels.RegistryData;
import com.components.requestModels.ResponseData;
import com.components.services.ObjectService;
import com.components.services.WordsService;
import com.components.tables.Word;
import com.components.util.RegistryManager;
import com.components.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by ruslan on 29.11.16.
 */

@RestController
@RequestMapping("/")
public class BaseRestController {

    @Autowired
    private ObjectService objectService;

    @Autowired
    private WordsService wordsService;

    @Autowired
    private RegistryManager registryManager;

    @RequestMapping(value = "getWords", method = RequestMethod.GET)
    public List<Word> getWords() {
        return wordsService.getRandomWords(10);
    }

    @RequestMapping(value = "getLastWords", method = RequestMethod.GET)
    public List<Word> getLastWords() {
        return wordsService.getLastRandomWords(10);
    }

    @RequestMapping(value = "getUserRoles", method = RequestMethod.POST)
    public Collection<GrantedAuthority> roles() {
        return Util.getCurrentUserRoles();
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "addWord", method = RequestMethod.POST)
    public void addWord(@RequestBody Word word) throws SQLException {
        word.setDate(new Date());
        objectService.saveObject(word);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "editWord", method = RequestMethod.POST)
    public void editWord(@RequestBody Word word) throws SQLException {
        wordsService.updateWord(word);
    }

    @Secured({"ROLE_ADMIN"})
    @RequestMapping(value = "deleteWord", method = RequestMethod.POST)
    public void deleteWord(@RequestBody Word word) throws SQLException {
        objectService.deleteObject(word);
    }


    @RequestMapping(value = "registry", method = RequestMethod.POST)
    public ResponseData registry(@RequestBody RegistryData registryData) {
        return registryManager.registry(registryData);
    }
}

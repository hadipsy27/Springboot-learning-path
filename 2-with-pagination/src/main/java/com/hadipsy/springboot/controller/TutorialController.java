package com.hadipsy.springboot.controller;

import com.hadipsy.springboot.model.Tutorial;
import com.hadipsy.springboot.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title){
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();
            if(title == null){
                tutorialRepository.findAll().forEach(tutorials::add);
            } else {
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
            }

            if (tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorial/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id){
        Optional<Tutorial> tutorial = tutorialRepository.findById(id);
        if(tutorial.isPresent()){
            return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorial")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        try {
            Tutorial _tutorial = tutorialRepository
                    .save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/tutorial/{id}")
    public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial){
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if(tutorialData.isPresent()){
            Tutorial tutor = tutorialData.get();
            tutor.setTitle(tutorial.getTitle());
            tutor.setDescription(tutorial.getDescription());
            tutor.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(tutor), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tutorial/{id}")
    public ResponseEntity<Tutorial> deleteTutorial(@PathVariable("id") long id){
        try {
            Optional<Tutorial> tutorial = tutorialRepository.findById(id);
            tutorialRepository.deleteById(id);
            return new ResponseEntity<>(tutorial.get(), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorial(){
        try {
            tutorialRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Tutorial>> findByPublished(){
        try {
            List<Tutorial> tutorials = tutorialRepository.findByPublished(true);
            if (tutorials.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

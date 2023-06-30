//package com.telerikacademy.web.controllers;
//
//import com.telerikacademy.web.exceptions.DuplicateEntityException;
//import com.telerikacademy.web.exceptions.EntityNotFoundException;
//import com.telerikacademy.web.models.Beer;
//import com.telerikacademy.web.models.Style;
//import com.telerikacademy.web.services.contracts.IStyleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/styles")
//public class StyleController {
//    private final IStyleService service;
//
//    @Autowired
//    public StyleController(IStyleService service) {
//        this.service = service;
//    }
//
//    @GetMapping
//    public List<Style> getAll() {
//        return service.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public Style getById(@PathVariable int id) {
//        try {
//            return service.getById(id);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }
//
//    @PostMapping
//    public Style create(@Valid @RequestBody Style style) {
//        try {
//            service.create(style);
//        } catch (DuplicateEntityException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
//        }
//        return style;
//    }
//
//    @PutMapping
//    public Style update(@Valid @RequestBody Style style) {
//        try {
//            service.update(style);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        } catch (DuplicateEntityException e) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
//        }
//
//        return style;
//    }
//
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable int id) {
//        try {
//            service.delete(id);
//        } catch (EntityNotFoundException e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//        }
//    }
//}

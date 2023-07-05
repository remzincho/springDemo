package com.telerikacademy.web.controllers;

import com.telerikacademy.web.exceptions.DuplicateEntityException;
import com.telerikacademy.web.exceptions.EntityNotFoundException;
import com.telerikacademy.web.exceptions.UnauthorizedOperationException;
import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.DTOs.BeerDTO;
import com.telerikacademy.web.models.User;
import com.telerikacademy.web.services.contracts.IBeerService;
import com.telerikacademy.web.services.mappers.BeerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/beers")
public class BeerController {
    private IBeerService service;
    private final BeerMapper beerMapper;
    private final AuthenticationHelper authenticationHelper;

    @Autowired
    public BeerController(IBeerService service, BeerMapper beerMapper, AuthenticationHelper authenticationHelper) {
        this.service = service;
        this.beerMapper = beerMapper;
        this.authenticationHelper = authenticationHelper;
    }

    @GetMapping
    public List<Beer> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Beer getById(@PathVariable int id) {
        try {
            return service.getById(id);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @GetMapping("/search")
    public Beer getByName(@RequestParam String name) {
        try {
            return service.getByName(name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping
    public Beer create(@RequestHeader HttpHeaders headers, @Valid @RequestBody BeerDTO beerDTO) {
        try {
            Beer beer = beerMapper.fromDto(beerDTO);
            service.create(beer, headers);
            return beer;
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Beer update(@RequestHeader HttpHeaders headers, @PathVariable int id, @Valid @RequestBody BeerDTO beerDTO) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            Beer beer = beerMapper.fromDto(beerDTO, id);
            service.update(beer, user);
            return beer;
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (DuplicateEntityException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader HttpHeaders headers, @PathVariable int id) {
        try {
            User user = authenticationHelper.tryGetUser(headers);
            service.delete(id, user);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (UnauthorizedOperationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}

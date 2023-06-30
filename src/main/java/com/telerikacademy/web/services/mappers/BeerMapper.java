package com.telerikacademy.web.services.mappers;

import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.DTOs.BeerDTO;
import com.telerikacademy.web.repositories.contracts.IBeerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {
    private final IBeerRepository beerRepository;

    @Autowired
    public BeerMapper(IBeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }
    public Beer fromDto(BeerDTO beerDTO) {
        Beer beer = new Beer();
        dtoToObject(beerDTO, beer);
        return beer;
    }

    public Beer fromDto(BeerDTO beerDTO, int id) {
        Beer beer = beerRepository.getById(id);
        dtoToObject(beerDTO, beer);
        return beer;
    }

    private void dtoToObject(BeerDTO beerDTO, Beer beer) {
        beer.setName(beerDTO.getName());
        beer.setAbv(beerDTO.getAbv());
    }
}

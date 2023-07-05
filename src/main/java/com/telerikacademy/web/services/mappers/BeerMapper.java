package com.telerikacademy.web.services.mappers;

import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.DTOs.BeerDTO;
import com.telerikacademy.web.models.Style;
import com.telerikacademy.web.models.User;
import com.telerikacademy.web.repositories.contracts.IBeerRepository;
import com.telerikacademy.web.repositories.contracts.IStyleRepository;
import com.telerikacademy.web.repositories.contracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {
    private final IBeerRepository beerRepository;
    private final IStyleRepository styleRepository;
    private final IUserRepository userRepository;

    @Autowired
    public BeerMapper(IBeerRepository beerRepository, IStyleRepository styleRepository, IUserRepository userRepository) {
        this.beerRepository = beerRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
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
        Style style = styleRepository.getById(beerDTO.getStyleId());
        //User user = userRepository.getById(beerDTO.getOwnerId());
        beer.setName(beerDTO.getName());
        beer.setAbv(beerDTO.getAbv());
        beer.setStyle(style);
        //beer.setOwner(user);
    }
}

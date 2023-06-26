package com.telerikacademy.web.services.mappers;

import com.telerikacademy.web.models.Beer;
import com.telerikacademy.web.models.BeerDTO;
import com.telerikacademy.web.models.Style;
import com.telerikacademy.web.services.contracts.IBeerService;
import com.telerikacademy.web.services.contracts.IStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeerMapper {
    private final IStyleService styleService;
    private final IBeerService beerService;

    @Autowired
    public BeerMapper(IStyleService styleService, IBeerService beerService) {
        this.styleService = styleService;
        this.beerService = beerService;
    }

    public Beer dtoToObject(BeerDTO beerDTO){
        Beer beer = new Beer();
        Style style = styleService.getById(beerDTO.getStyleId());
        int id = beerService.getNextBeerId();
        beer.setId(id);
        beer.setName(beerDTO.getName());
        beer.setAbv(beerDTO.getAbv());
        beer.setStyle(style);
        return beer;
    }
}

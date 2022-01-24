package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.dtos.BuyEventDto;
import nl.iboers.garden.plantmanager.dtos.converters.DtoConverter;
import nl.iboers.garden.plantmanager.entities.BuyEvent;
import nl.iboers.garden.plantmanager.repositories.BuyEventRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "buyevent")
public class BuyEventController extends AbstractDtoCrudController<BuyEvent, BuyEventDto, BuyEventRepository> {

    protected BuyEventController(BuyEventRepository entityRepository, DtoConverter<BuyEvent, BuyEventDto> dtoConverter) {
        super(entityRepository, dtoConverter);
    }
}

package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.dtos.PlantDiedEventDto;
import nl.iboers.garden.plantmanager.dtos.converters.DtoConverter;
import nl.iboers.garden.plantmanager.dtos.converters.PlantDiedEventConverter;
import nl.iboers.garden.plantmanager.entities.PlantDiedEvent;
import nl.iboers.garden.plantmanager.repositories.PlantDiedEventRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "plantdiedevent")
public class PlantDiedEventController extends AbstractDtoCrudController<PlantDiedEvent, PlantDiedEventDto, PlantDiedEventRepository> {

    protected PlantDiedEventController(PlantDiedEventRepository entityRepository, PlantDiedEventConverter dtoConverter) {
        super(entityRepository, dtoConverter);
    }

}

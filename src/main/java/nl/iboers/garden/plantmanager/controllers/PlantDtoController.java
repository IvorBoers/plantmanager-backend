package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.dtos.PlantDto;
import nl.iboers.garden.plantmanager.dtos.converters.DtoConverter;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.repositories.PlantRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "plants")
public class PlantDtoController extends AbstractDtoCrudController<Plant, PlantDto, PlantRepository> {

    protected PlantDtoController(PlantRepository entityRepository, DtoConverter<Plant, PlantDto> dtoConverter) {
        super(entityRepository, dtoConverter);
    }


//    @Override
//    public ResponseEntity<PostResponse> create(PlantDto item) {
//        bindEvents(item);
//        return super.create(item);
//    }
//
//    @Override
//    ResponseEntity<PostResponse> getUpdateResponse(Plant dbItem, PlantDto updatedItem) {
//        bindEvents(updatedItem);
//        return super.getUpdateResponse(dbItem, updatedItem);
//    }
//
//    private void bindEvents(PlantDto updatedItem) {
//        updatedItem.getProducePickEvents().forEach(evt -> evt.setPlant(updatedItem));
//        updatedItem.getRelocationEvents().forEach(evt -> evt.setPlant(updatedItem));
//    }

}

package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.PlantLocation;
import nl.iboers.garden.plantmanager.repositories.PlantLocationRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "plantlocations")
public class PlantLocationController extends AbstractCrudController<PlantLocation, PlantLocationRepository> {

    protected PlantLocationController(PlantLocationRepository entityRepository) {
        super(entityRepository);
    }


}

package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesTypeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Ivor
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "plantspeciestype")
public class PlantSpeciesTypeController extends AbstractCrudController<PlantSpeciesType, PlantSpeciesTypeRepository> {

    public PlantSpeciesTypeController(PlantSpeciesTypeRepository entityRepository) {
        super(entityRepository);
    }

    @GetMapping()
    @Override
    public List<PlantSpeciesType> getAll() {
        return super.getAll(Sort.by("name"));
    }



}

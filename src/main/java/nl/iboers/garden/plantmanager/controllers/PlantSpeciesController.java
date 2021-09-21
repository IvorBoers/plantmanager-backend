package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * @author Ivor
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "plantspecies")
public class PlantSpeciesController extends AbstractCrudController<PlantSpecies, PlantSpeciesRepository> {

    public PlantSpeciesController(PlantSpeciesRepository entityRepository) {
        super(entityRepository);
    }

    @GetMapping()
    @Override
    public List<PlantSpecies> getAll() {
        return super.getAll(Sort.by("name"));
    }

    @Override
    protected void setEntityImage(PlantSpecies plantSpecies, String fieldName, byte[] bytes) {
        if ("image".equals(fieldName)) {
            plantSpecies.setImage(bytes);
        }
    }

    @GetMapping("/filtered")
    public List<PlantSpecies> getAllByParentId(@RequestParam("parentId") Long parentId) {
        if (parentId != null && !entityRepository.existsById(parentId)) {
            return Collections.emptyList();
        }
        return entityRepository.getPlantSpeciesByParentIdOrderByName(parentId);
    }

}

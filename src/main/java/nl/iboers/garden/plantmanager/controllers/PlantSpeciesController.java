package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.dtos.PlantSpeciesDto;
import nl.iboers.garden.plantmanager.dtos.converters.PlantSpeciesConverter;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ivor
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "plantspecies")
public class PlantSpeciesController extends AbstractDtoCrudController<PlantSpecies, PlantSpeciesDto, PlantSpeciesRepository> {

    public PlantSpeciesController(PlantSpeciesRepository entityRepository, PlantSpeciesConverter plantSpeciesConverter) {
        super(entityRepository, plantSpeciesConverter);
    }

    @GetMapping()
    @Override
    public List<PlantSpeciesDto> getAll() {
        return super.getAll(Sort.by("name"));
    }

    @GetMapping("/filtered")
    public List<PlantSpeciesDto> getAllByParentId(@RequestParam("parentId") Long parentId) {
        if (parentId != null && !getEntityRepository().existsById(parentId)) {
            return Collections.emptyList();
        }
        return getEntityRepository().getPlantSpeciesByParentIdOrderByName(parentId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

}

package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.ProducePickEvent;
import nl.iboers.garden.plantmanager.repositories.PlantRepository;
import nl.iboers.garden.plantmanager.repositories.ProducePickEventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "producepickevent")
public class ProduceController extends AbstractCrudController<ProducePickEvent, ProducePickEventRepository> {

    private final PlantRepository plantRepository;

    protected ProduceController(ProducePickEventRepository entityRepository, PlantRepository plantRepository) {
        super(entityRepository);
        this.plantRepository = plantRepository;
    }

    @Override
    protected void setEntityImage(ProducePickEvent entity, String fieldName, byte[] bytes) {
        if ("image".equals(fieldName)) {
            entity.setImage(bytes);
        }
    }

    @GetMapping("/for-plant/{plantId}")
    public List<ProducePickEvent> getAllForPlant(@PathVariable Long plantId) {
        return plantRepository.findById(plantId)
                .map(entityRepository::findAllByPlantOrderByDateDesc)
                .orElse(new ArrayList<>());
    }
}

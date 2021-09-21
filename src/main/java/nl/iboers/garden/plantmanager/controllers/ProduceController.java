package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.ProducePickEvent;
import nl.iboers.garden.plantmanager.repositories.ProducePickEventRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "producepickevent")
public class ProduceController extends AbstractCrudController<ProducePickEvent, ProducePickEventRepository> {

    protected ProduceController(ProducePickEventRepository entityRepository) {
        super(entityRepository);
    }

    @Override
    protected void setEntityImage(ProducePickEvent entity, String fieldName, byte[] bytes) {
        if ("image".equals(fieldName)) {
            entity.setImage(bytes);
        }
    }
}

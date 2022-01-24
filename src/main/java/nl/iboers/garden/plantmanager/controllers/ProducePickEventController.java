package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.dtos.ProducePickEventDto;
import nl.iboers.garden.plantmanager.dtos.converters.ProducePickEventConverter;
import nl.iboers.garden.plantmanager.entities.ProducePickEvent;
import nl.iboers.garden.plantmanager.repositories.ProducePickEventRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivor Boers
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "producepickevent")
public class ProducePickEventController extends AbstractDtoCrudController<ProducePickEvent, ProducePickEventDto, ProducePickEventRepository> {


    protected ProducePickEventController(ProducePickEventRepository entityRepository, ProducePickEventConverter producePickEventConverter) {
        super(entityRepository, producePickEventConverter);
    }

}

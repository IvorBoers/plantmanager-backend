package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.PlantDiedEvent;
import nl.iboers.garden.plantmanager.repositories.PlantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

/**
 * @author Ivor
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "plants")
public class PlantController extends AbstractCrudController<Plant, PlantRepository> {
    private static final Logger LOG = LoggerFactory.getLogger(PlantController.class);

    public PlantController(PlantRepository entityRepository) {
        super(entityRepository);
    }

}

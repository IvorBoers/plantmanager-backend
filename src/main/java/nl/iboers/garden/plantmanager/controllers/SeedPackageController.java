package nl.iboers.garden.plantmanager.controllers;

import nl.iboers.garden.plantmanager.PlantManagerConfiguration;
import nl.iboers.garden.plantmanager.entities.SeedPackage;
import nl.iboers.garden.plantmanager.repositories.SeedPackageRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ivor
 */
@RestController
@RequestMapping(PlantManagerConfiguration.BASE_URL + "seedpackages")
public class SeedPackageController extends AbstractCrudController<SeedPackage, SeedPackageRepository> {

    public SeedPackageController(SeedPackageRepository entityRepository) {
        super(entityRepository);
    }

}

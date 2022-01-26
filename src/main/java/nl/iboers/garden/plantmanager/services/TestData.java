package nl.iboers.garden.plantmanager.services;

import nl.iboers.garden.plantmanager.entities.PlantLocation;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;
import nl.iboers.garden.plantmanager.repositories.PlantLocationRepository;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesRepository;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesTypeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Profile("test")
@Component
public class TestData {
    private static final Logger LOG = LoggerFactory.getLogger(TestData.class);
    private final PlantSpeciesTypeRepository plantSpeciesTypeRepository;
    private final PlantSpeciesRepository plantSpeciesRepository;
    private final PlantLocationRepository plantLocationRepository;

    public TestData(PlantSpeciesTypeRepository plantSpeciesTypeRepository, PlantSpeciesRepository plantSpeciesRepository, PlantLocationRepository plantLocationRepository) {
        this.plantSpeciesTypeRepository = plantSpeciesTypeRepository;
        this.plantSpeciesRepository = plantSpeciesRepository;
        this.plantLocationRepository = plantLocationRepository;
    }

    @EventListener
    public void onEvent(ApplicationReadyEvent event) {
        if (plantSpeciesTypeRepository.count() == 0) {
            LOG.info("Importing testcontent");
            sampleLocations();
            sampleVeggie();
            sampleFruit();
        }
    }

    private void sampleLocations() {
        PlantLocation pot1 = new PlantLocation();
        pot1.setName("Pot 1");
        pot1.setColor("00ff40");
        PlantLocation pot2 = new PlantLocation();
        pot2.setName("Plot A");
        pot2.setColor("001aff");
        plantLocationRepository.save(pot1);
        plantLocationRepository.save(pot2);
    }

    private void sampleVeggie() {
        PlantSpeciesType veggies = new PlantSpeciesType();
        veggies.setName("Vegetable");
        veggies = plantSpeciesTypeRepository.save(veggies);


        PlantSpecies leeks = new PlantSpecies();
        leeks.setType(veggies);
        leeks.setMaximumHeight(60);
        leeks.setDescription("Leeks are very hardy vegetables, which in most regions will safely sit through frost and snow to be lifted as needed. You can prolong the harvest period by selecting a mix of varieties. Early season leeks are less hardy but will be ready for autumn, while mid and late season leeks will give you smooth stems for winter and spring.");
        leeks.setName("Leeks");
        leeks = plantSpeciesRepository.save(leeks);

        PlantSpecies summerLeeks = new PlantSpecies();
        summerLeeks.setType(veggies);
        summerLeeks.setMaximumHeight(50);
        summerLeeks.setParentId(leeks.getId());
        summerLeeks.setName("Early season leeks");
        plantSpeciesRepository.save(summerLeeks);

        PlantSpecies winterLeeks = new PlantSpecies();
        winterLeeks.setType(veggies);
        winterLeeks.setMaximumHeight(60);
        winterLeeks.setParentId(leeks.getId());
        winterLeeks.setName("Late season leeks");
        plantSpeciesRepository.save(winterLeeks);
    }

    private void sampleFruit() {
        PlantSpeciesType fruits = new PlantSpeciesType();
        fruits.setName("Fruit");
        fruits = plantSpeciesTypeRepository.save(fruits);

        PlantSpecies apple = new PlantSpecies();
        apple.setType(fruits);
        apple.setMaximumHeight(400);
        apple.setName("Apple");
        apple = plantSpeciesRepository.save(apple);

        PlantSpecies jonagold = new PlantSpecies();
        jonagold.setType(fruits);
        jonagold.setMaximumHeight(400);
        jonagold.setName("Jonagold");
        jonagold.setParentId(apple.getId());
        plantSpeciesRepository.save(jonagold);
    }
}

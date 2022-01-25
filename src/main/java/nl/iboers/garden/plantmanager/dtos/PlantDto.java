package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import lombok.ToString;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.entities.RelocationEvent;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.stream.Collectors;

/**
 * @author Ivor Boers
 */
@Data
@ToString
public class PlantDto implements Dto<Plant> {
    private Long id;
    private PlantSpeciesDto species;
    private BuyEventDto buyEvent;
    private PlantDiedEventDto diedEvent;
    private SeedStartEventDto seedStartEvent;
    private List<ProducePickEventDto> producePickEvents = new ArrayList<>();
    private List<RelocationEventDto> relocationEvents = new ArrayList<>();
    private PlantLocationDto currentLocation;


    @Override
    public void from(Plant entity) {
        id = entity.getId();
        species = toPlantSpeciesDto(entity.getPlantSpecies());
        buyEvent = entity.getBuyEventAsOptional().map(BuyEventDto::new).orElse(null);
        diedEvent = entity.getPlantDiedEventAsOptional().map(PlantDiedEventDto::new).orElse(null);
        seedStartEvent = entity.getSeedStartEventAsOptional().map(SeedStartEventDto::new).orElse(null);
        producePickEvents = entity.getProducePickEvents().stream()
                .map(ProducePickEventDto::new)
                .collect(Collectors.toList());
        relocationEvents = entity.getRelocationEvents().stream()
                .map(RelocationEventDto::new)
                .collect(Collectors.toList());
        currentLocation = getCurrentLocation(entity.getRelocationEvents());
    }

    private PlantLocationDto getCurrentLocation(SortedSet<RelocationEvent> relocationEvents) {
        return relocationEvents.stream()
                .filter(location -> location.getDate().isBefore(OffsetDateTime.now()))
                .findFirst()
                .map(RelocationEvent::getLocation)
                .map(PlantLocationDto::new)
                .orElse(null);
    }

    private PlantSpeciesDto toPlantSpeciesDto(PlantSpecies e) {
        if (e == null) {
            return null;
        }
        return new PlantSpeciesDto(e);
    }

    @Override
    public Long getId() {
        return id;
    }
}

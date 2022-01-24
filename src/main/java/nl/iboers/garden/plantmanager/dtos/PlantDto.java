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
        buyEvent = getBuyEventDto(entity);
        diedEvent = getDiedEventDto(entity);
        seedStartEvent = getSeedStartEventDto(entity);
        producePickEvents = entity.getProducePickEvents().stream().map(item -> {
            ProducePickEventDto dto = new ProducePickEventDto();
            dto.from(item);
            return dto;
        }).collect(Collectors.toList());
        relocationEvents = entity.getRelocationEvents().stream().map(item -> {
            RelocationEventDto dto = new RelocationEventDto();
            dto.from(item);
            return dto;
        }).collect(Collectors.toList());
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
        PlantSpeciesDto dto = new PlantSpeciesDto();
        dto.from(e);
        return dto;
    }

    private BuyEventDto getBuyEventDto(Plant entity) {
        if (entity.getBuyEvent() == null) {
            return null;
        }
        BuyEventDto result = new BuyEventDto();
        result.from(entity.getBuyEvent());
        return result;
    }

    private PlantDiedEventDto getDiedEventDto(Plant entity) {
        if (entity.getPlantDiedEvent() == null) {
            return null;
        }
        PlantDiedEventDto result = new PlantDiedEventDto();
        result.from(entity.getPlantDiedEvent());
        return result;
    }

    private SeedStartEventDto getSeedStartEventDto(Plant entity) {
        if (entity.getSeedStartEvent() == null) {
            return null;
        }
        SeedStartEventDto result = new SeedStartEventDto();
        result.from(entity.getSeedStartEvent());
        return result;
    }

    @Override
    public Long getId() {
        return id;
    }
}

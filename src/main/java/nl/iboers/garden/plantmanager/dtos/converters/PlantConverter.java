package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.AbstractEventDto;
import nl.iboers.garden.plantmanager.dtos.PlantDto;
import nl.iboers.garden.plantmanager.dtos.PlantSpeciesDto;
import nl.iboers.garden.plantmanager.dtos.RelocationEventDto;
import nl.iboers.garden.plantmanager.entities.AbstractEvent;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.entities.RelocationEvent;
import nl.iboers.garden.plantmanager.repositories.BuyEventRepository;
import nl.iboers.garden.plantmanager.repositories.PlantDiedEventRepository;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesRepository;
import nl.iboers.garden.plantmanager.repositories.RelocationEventRepository;
import nl.iboers.garden.plantmanager.repositories.SeedStartEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * @author Ivor Boers
 */
@Component
public class PlantConverter implements  DtoConverter<Plant, PlantDto> {
    private static final Logger LOG = LoggerFactory.getLogger(PlantConverter.class);
    private final RelocationEventRepository relocationEventRepository;
    private final RelocationEventConverter relocationEventConverter;

    private final BuyEventConverter buyEventConverter;
    private final BuyEventRepository buyEventRepository;

    private final PlantDiedEventConverter plantDiedEventConverter;
    private final PlantDiedEventRepository plantDiedEventRepository;

    private final SeedStartEventConverter seedStartEventConverter;
    private final SeedStartEventRepository seedStartEventRepository;

    private final PlantSpeciesConverter plantSpeciesConverter;
    private final PlantSpeciesRepository plantSpeciesRepository;

    public PlantConverter(RelocationEventRepository relocationEventRepository, RelocationEventConverter relocationEventConverter,
                          BuyEventConverter buyEventConverter, BuyEventRepository buyEventRepository,
                          PlantDiedEventConverter plantDiedEventConverter, PlantDiedEventRepository plantDiedEventRepository,
                          SeedStartEventConverter seedStartEventConverter, SeedStartEventRepository seedStartEventRepository,
                          PlantSpeciesConverter plantSpeciesConverter, PlantSpeciesRepository plantSpeciesRepository) {
        this.relocationEventRepository = relocationEventRepository;
        this.relocationEventConverter = relocationEventConverter;
        this.buyEventConverter = buyEventConverter;
        this.buyEventRepository = buyEventRepository;
        this.plantDiedEventConverter = plantDiedEventConverter;
        this.plantDiedEventRepository = plantDiedEventRepository;
        this.seedStartEventConverter = seedStartEventConverter;
        this.seedStartEventRepository = seedStartEventRepository;
        this.plantSpeciesConverter = plantSpeciesConverter;
        this.plantSpeciesRepository = plantSpeciesRepository;
    }

    @Override
    public Plant convert(Plant entity, PlantDto dto) {
        LOG.debug("Converting dto {}", dto);
        entity.setRelocationEvents(new TreeSet<>(
                dto.getRelocationEvents().stream()
                        .map(this::getRelocationEntity)
                        .collect(Collectors.toSet())));
        entity.setBuyEvent(getEventEntity(dto.getBuyEvent(), buyEventConverter, buyEventRepository));
        entity.setPlantDiedEvent(getEventEntity(dto.getDiedEvent(), plantDiedEventConverter, plantDiedEventRepository));
        entity.setSeedStartEvent(getEventEntity(dto.getSeedStartEvent(), seedStartEventConverter, seedStartEventRepository));
        entity.setPlantSpecies(getType(dto.getSpecies()));
        return entity;
    }

    private PlantSpecies getType(PlantSpeciesDto species) {
        if (species == null) {
            return null;
        }
        if (species.getId() == null) {
            return plantSpeciesConverter.convert(plantSpeciesConverter.createNewEntity(), species);
        }
        return plantSpeciesRepository.findById(species.getId())
                .map(s -> plantSpeciesConverter.convert(s, species))
                .orElseThrow(() -> new IllegalArgumentException("No species found with id=" + species.getId()));
    }

    private <E extends AbstractEvent, D extends AbstractEventDto<E>> E getEventEntity(D eventDto, AbstractEventDtoConverter<E, D>  converter, JpaRepository<E, Long> repository) {
        if (eventDto == null) {
            return null; // no dto -> no entity
        }
        if (eventDto.getId() == null) {
            return converter.convert(converter.createNewEntity(), eventDto); // new entity
        }
        // entity with id
        return  repository.findById(eventDto.getId())
                .map(e -> converter.convert(e, eventDto))
                .orElseThrow(() -> new IllegalArgumentException("No entity found with id=" + eventDto.getId()));
    }

    private RelocationEvent getRelocationEntity(RelocationEventDto eventDto) {
        if (eventDto.getId() == null) {
            return relocationEventConverter.convert(relocationEventConverter.createNewEntity(), eventDto);
        }
        return relocationEventConverter.convert(
                relocationEventRepository.findById(eventDto.getId()).orElse(new RelocationEvent()),
                eventDto);
    }

    @Override
    public Plant createNewEntity() {
        return new Plant();
    }

    @Override
    public PlantDto createNewDto() {
        return new PlantDto();
    }
}

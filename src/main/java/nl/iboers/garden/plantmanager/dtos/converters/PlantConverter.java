package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.AbstractEventDto;
import nl.iboers.garden.plantmanager.dtos.PlantDto;
import nl.iboers.garden.plantmanager.dtos.RelocationEventDto;
import nl.iboers.garden.plantmanager.entities.AbstractEvent;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.RelocationEvent;
import nl.iboers.garden.plantmanager.repositories.BuyEventRepository;
import nl.iboers.garden.plantmanager.repositories.PlantDiedEventRepository;
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

    public PlantConverter(RelocationEventRepository relocationEventRepository, RelocationEventConverter relocationEventConverter,
                          BuyEventConverter buyEventConverter, BuyEventRepository buyEventRepository,
                          PlantDiedEventConverter plantDiedEventConverter, PlantDiedEventRepository plantDiedEventRepository,
                          SeedStartEventConverter seedStartEventConverter, SeedStartEventRepository seedStartEventRepository) {
        this.relocationEventRepository = relocationEventRepository;
        this.relocationEventConverter = relocationEventConverter;
        this.buyEventConverter = buyEventConverter;
        this.buyEventRepository = buyEventRepository;
        this.plantDiedEventConverter = plantDiedEventConverter;
        this.plantDiedEventRepository = plantDiedEventRepository;
        this.seedStartEventConverter = seedStartEventConverter;
        this.seedStartEventRepository = seedStartEventRepository;
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
        return entity;
    }

    private <E extends AbstractEvent, D extends AbstractEventDto<E>> E getEventEntity(D event, AbstractEventDtoConverter<E, D>  converter, JpaRepository<E, Long> repository) {
        if (event == null) {
            return null; // no dto -> no entity
        }
        if (event.getId() != null) { // entity with id
            return converter.convert(
                    repository.findById(event.getId())
                            .orElse(converter.createNewEntity()), // a new entity created while the id was not found . MWAH
                    event);
        }
        return converter.convert(converter.createNewEntity(), event); // new entity
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

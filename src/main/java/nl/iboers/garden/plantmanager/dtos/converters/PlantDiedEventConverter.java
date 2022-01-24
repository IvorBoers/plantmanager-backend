package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.PlantDiedEventDto;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.PlantDiedEvent;
import nl.iboers.garden.plantmanager.repositories.PlantRepository;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Component
public class PlantDiedEventConverter extends  AbstractEventDtoConverter<PlantDiedEvent, PlantDiedEventDto> {

    private final PlantRepository plantRepository;

    public PlantDiedEventConverter(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public PlantDiedEvent createNewEntity() {
        return new PlantDiedEvent();
    }

    @Override
    public PlantDiedEventDto createNewDto() {
        return new PlantDiedEventDto();
    }

    @Override
    public PlantDiedEvent convert(PlantDiedEvent entity, PlantDiedEventDto dto) {
        PlantDiedEvent event = super.convert(entity, dto);
        event.setPlant(getPlant(dto.getPlantId()));
        return event;
    }

    private Plant getPlant(long plantId) {
        return plantRepository
                .findById(plantId)
                .orElseThrow(() -> new IllegalArgumentException("plantId=" + plantId + " is unknown"));
    }
}

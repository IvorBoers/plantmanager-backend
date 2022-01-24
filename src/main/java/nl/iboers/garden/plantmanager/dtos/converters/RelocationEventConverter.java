package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.RelocationEventDto;
import nl.iboers.garden.plantmanager.entities.Plant;
import nl.iboers.garden.plantmanager.entities.RelocationEvent;
import nl.iboers.garden.plantmanager.repositories.PlantLocationRepository;
import nl.iboers.garden.plantmanager.repositories.PlantRepository;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Component
public class RelocationEventConverter extends AbstractEventDtoConverter<RelocationEvent, RelocationEventDto> {

    private final PlantRepository plantRepository;
    private final PlantLocationRepository plantLocationRepository;

    public RelocationEventConverter(PlantRepository plantRepository, PlantLocationRepository plantLocationRepository) {
        this.plantRepository = plantRepository;
        this.plantLocationRepository = plantLocationRepository;
    }

    @Override
    public RelocationEvent convert(RelocationEvent entity, RelocationEventDto dto) {
        RelocationEvent result = super.convert(entity, dto);
        Plant plant = plantRepository.getById(dto.getPlantId());
        result.setPlant(plant);
        result.setLocation(plantLocationRepository.findById(dto.getLocationId()).orElseThrow(() -> new IllegalArgumentException("PlantLocation id=" + dto.getLocationId() + " was not found")));

        return result;
    }

    @Override
    public RelocationEvent createNewEntity() {
        return new RelocationEvent();
    }

    @Override
    public RelocationEventDto createNewDto() {
        return new RelocationEventDto();
    }
}

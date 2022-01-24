package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.PlantSpeciesDto;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Component
public class PlantSpeciesConverter implements DtoConverter<PlantSpecies, PlantSpeciesDto> {


    @Override
    public PlantSpecies convert(PlantSpecies entity, PlantSpeciesDto dto) {
        entity.setDescription(dto.getDescription());
        entity.setId(dto.getId());
        entity.setImageId(dto.getImageId());
        entity.setName(dto.getName());
        entity.setParentId(dto.getParentId());
        entity.setMaximumHeight(dto.getMaximumHeight());
        entity.setSpacing(dto.getSpacing());
        entity.setSungrade(dto.getSungrade());
        return entity;
    }

    @Override
    public PlantSpecies createNewEntity() {
        return new PlantSpecies();
    }

    @Override
    public PlantSpeciesDto createNewDto() {
        return new PlantSpeciesDto();
    }
}

package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.PlantSpeciesTypeDto;
import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Component
public class PlantSpeciesTypeConverter implements DtoConverter<PlantSpeciesType, PlantSpeciesTypeDto> {


    @Override
    public PlantSpeciesType convert(PlantSpeciesType entity, PlantSpeciesTypeDto dto) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public PlantSpeciesType createNewEntity() {
        return new PlantSpeciesType();
    }

    @Override
    public PlantSpeciesTypeDto createNewDto() {
        return new PlantSpeciesTypeDto();
    }
}

package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.PlantSpeciesDto;
import nl.iboers.garden.plantmanager.dtos.PlantSpeciesTypeDto;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesTypeRepository;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Component
public class PlantSpeciesConverter implements DtoConverter<PlantSpecies, PlantSpeciesDto> {

    private final PlantSpeciesTypeConverter plantSpeciesTypeConverter;
    private final PlantSpeciesTypeRepository plantSpeciesTypeRepository;

    public PlantSpeciesConverter(PlantSpeciesTypeConverter plantSpeciesTypeConverter, PlantSpeciesTypeRepository plantSpeciesTypeRepository) {
        this.plantSpeciesTypeConverter = plantSpeciesTypeConverter;
        this.plantSpeciesTypeRepository = plantSpeciesTypeRepository;
    }

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
        entity.setType(getType(dto.getType()));
        return entity;
    }

    private PlantSpeciesType getType(PlantSpeciesTypeDto type) {
        if (type == null) {
            return null;
        }
        if (type.getId() == null) {
            return plantSpeciesTypeConverter.convert(plantSpeciesTypeConverter.createNewEntity(), type);
        }
        return plantSpeciesTypeRepository.findById(type.getId())
                .map(e -> plantSpeciesTypeConverter.convert(e, type))
                .orElseThrow(() -> new IllegalArgumentException("No type found with id=" + type.getId()));
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

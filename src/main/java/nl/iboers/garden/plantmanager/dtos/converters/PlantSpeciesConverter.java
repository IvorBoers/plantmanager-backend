package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.GrowPeriodDto;
import nl.iboers.garden.plantmanager.dtos.PlantSpeciesDto;
import nl.iboers.garden.plantmanager.dtos.PlantSpeciesTypeDto;
import nl.iboers.garden.plantmanager.dtos.WeekOfMonthDto;
import nl.iboers.garden.plantmanager.entities.GrowPeriod;
import nl.iboers.garden.plantmanager.entities.GrowPhaseEnum;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;
import nl.iboers.garden.plantmanager.entities.WeekOfMonth;
import nl.iboers.garden.plantmanager.repositories.PlantSpeciesTypeRepository;
import org.springframework.stereotype.Component;

import java.util.SortedSet;
import java.util.TreeSet;

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
        entity.setSungrade(dto.getSungrade());
        entity.setType(getType(dto.getType()));
        entity.setGrowPeriods(getGrowPeriods(entity, dto.getGrowPeriods()));
        return entity;
    }

    private SortedSet<GrowPeriod> getGrowPeriods(PlantSpecies entity, SortedSet<GrowPeriodDto> growPeriods) {
        SortedSet<GrowPeriod> result = new TreeSet<>();
        for (GrowPeriodDto growPeriod : growPeriods) {
            GrowPeriod period = new GrowPeriod();
            period.setId(growPeriod.getId());
            period.setDescription(growPeriod.getDescription());
            period.setGrowPhase(GrowPhaseEnum.valueOf(growPeriod.getPhase()));
            period.setStart(getWeekOfMonth(growPeriod.getStart()));
            period.setEnd(getWeekOfMonth(growPeriod.getEnd()));
            period.setPlantSpecies(entity);
            result.add(period);
        }

        return result;
    }

    private WeekOfMonth getWeekOfMonth(WeekOfMonthDto dto) {
        WeekOfMonth entity = new WeekOfMonth();
        entity.setId(dto.getId());
        entity.setMonth(dto.getMonth());
        entity.setWeek(dto.getWeek());
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

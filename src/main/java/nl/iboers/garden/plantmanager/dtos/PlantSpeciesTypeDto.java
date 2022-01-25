package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;

/**
 * @author Ivor Boers
 */
@Data
public class PlantSpeciesTypeDto implements Dto<PlantSpeciesType> {
    private Long id;
    private String name;

    public PlantSpeciesTypeDto() {
    }

    public PlantSpeciesTypeDto(PlantSpeciesType entity) {
        from(entity);
    }

    @Override
    public void from(PlantSpeciesType entity) {
        id = entity.getId();
        name = entity.getName();
    }

    @Override
    public Long getId() {
        return id;
    }
}

package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;
import nl.iboers.garden.plantmanager.entities.PlantSpeciesType;

import java.util.Optional;

/**
 * @author Ivor Boers
 */
@Data
public class PlantSpeciesDto implements Dto<PlantSpecies> {
    private Long id;
    private String name;
    private Long typeId;
    private Long imageId;
    private Integer maximumHeight;
    private Integer spacing;
    private Integer sungrade;
    private String description;
    private Long parentId;

    @Override
    public void from(PlantSpecies entity) {
        id = entity.getId();
        name = entity.getName();
        typeId = Optional.ofNullable(entity.getType()).map(PlantSpeciesType::getId).orElse(null);
        imageId = entity.getImageId();
        maximumHeight = entity.getMaximumHeight();
        spacing = entity.getSpacing();
        sungrade = entity.getSungrade();
        description = entity.getDescription();
        parentId = entity.getParentId();
    }

    @Override
    public Long getId() {
        return id;
    }
}

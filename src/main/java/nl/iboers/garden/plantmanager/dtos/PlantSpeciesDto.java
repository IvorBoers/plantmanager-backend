package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import nl.iboers.garden.plantmanager.entities.PlantSpecies;

/**
 * @author Ivor Boers
 */
@Data
public class PlantSpeciesDto implements Dto<PlantSpecies> {
    private Long id;
    private String name;
    private PlantSpeciesTypeDto type;
    private Long imageId;
    private Integer maximumHeight;
    private Integer spacing;
    private Integer sungrade;
    private String description;
    private Long parentId;

    public PlantSpeciesDto() {
    }

    public PlantSpeciesDto(PlantSpecies entity) {
        from(entity);
    }

    @Override
    public void from(PlantSpecies entity) {
        id = entity.getId();
        name = entity.getName();
        type = entity.getTypeAsOptional().map(PlantSpeciesTypeDto::new).orElse(null);
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

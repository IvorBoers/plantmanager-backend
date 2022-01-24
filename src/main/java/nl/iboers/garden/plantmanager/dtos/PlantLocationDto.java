package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import nl.iboers.garden.plantmanager.entities.PlantLocation;

/**
 * @author Ivor Boers
 */
@Data
public class PlantLocationDto implements Dto<PlantLocation> {
    private Long id;
    private String description;
    private String name;
    private String color;
    private Long imageId;

    public PlantLocationDto() {}

    public PlantLocationDto(PlantLocation entity) {
        from(entity);
    }

    @Override
    public void from(PlantLocation entity) {
        id = entity.getId();
        name = entity.getName();
        description = entity.getDescription();
        color = entity.getColor();
        imageId = entity.getImageId();
    }

    @Override
    public Long getId() {
        return id;
    }
}

package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.iboers.garden.plantmanager.entities.PlantDiedEvent;

/**
 * @author Ivor Boers
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PlantDiedEventDto extends AbstractEventDto<PlantDiedEvent> {
    private Long plantId;
    private Long imageId;

    @Override
    public void from(PlantDiedEvent entity) {
        super.from(entity);
        this.plantId = entity.getPlant().getId();
        this.imageId = entity.getImageId();
    }
}

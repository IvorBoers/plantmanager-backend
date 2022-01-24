package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.iboers.garden.plantmanager.entities.RelocationEvent;

/**
 * @author Ivor Boers
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RelocationEventDto extends AbstractEventDto<RelocationEvent> {
    private long plantId;
    private long locationId;

    @Override
    public void from(RelocationEvent entity) {
        super.from(entity);
        this.plantId = entity.getPlant().getId();
        this.locationId = entity.getLocation().getId();
    }
}

package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.iboers.garden.plantmanager.entities.ProducePickEvent;

/**
 * @author Ivor Boers
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ProducePickEventDto extends AbstractEventDto<ProducePickEvent> {

    private Long plantId;
    private Float weight;
    private Integer count;
    private Long imageId;

    @Override
    public void from(ProducePickEvent entity) {
        super.from(entity);
        this.plantId = entity.getPlant().getId();
        this.weight = entity.getWeight();
        this.count = entity.getCount();
        if (entity.getImage() != null) {
            this.imageId = entity.getImage().getId();
        }
    }
}

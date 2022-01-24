package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.iboers.garden.plantmanager.entities.BuyEvent;

/**
 * @author Ivor Boers
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BuyEventDto extends AbstractEventDto<BuyEvent> {
    private Float price;
    private String store;
    private long imageId;

    @Override
    public void from(BuyEvent entity) {
        super.from(entity);
        this.price = entity.getPrice();
        this.store = entity.getStore();
        this.imageId = entity.getImageId();
    }
}

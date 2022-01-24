package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.BuyEventDto;
import nl.iboers.garden.plantmanager.entities.BuyEvent;
import org.springframework.stereotype.Component;

/**
 * @author Ivor Boers
 */
@Component
public class BuyEventConverter extends  AbstractEventDtoConverter<BuyEvent, BuyEventDto> {
    @Override
    public BuyEvent createNewEntity() {
        return new BuyEvent();
    }

    @Override
    public BuyEventDto createNewDto() {
        return new BuyEventDto();
    }

    @Override
    public BuyEvent convert(BuyEvent entity, BuyEventDto dto) {
        BuyEvent event = super.convert(entity, dto);
        event.setStore(dto.getStore());
        event.setPrice(dto.getPrice());
        event.setImageId(dto.getImageId());
        return event;
    }
}

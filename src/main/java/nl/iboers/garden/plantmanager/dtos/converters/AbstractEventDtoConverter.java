package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.AbstractEventDto;
import nl.iboers.garden.plantmanager.entities.AbstractEvent;

/**
 * @author Ivor Boers
 */
public abstract class AbstractEventDtoConverter<E extends AbstractEvent, D extends AbstractEventDto<E>> implements DtoConverter<E, D> {

    @Override
    public E convert(E entity, D dto) {
        entity.setDate(dto.getDate());
        entity.setDescription(dto.getDescription());
        return entity;
    }

}

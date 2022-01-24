package nl.iboers.garden.plantmanager.dtos.converters;

import nl.iboers.garden.plantmanager.dtos.Dto;
import nl.iboers.garden.plantmanager.entities.Identifiable;

/**
 * Component for converting between Entity and Dto.
 * @param <E> type of entity
 * @param <D> type of dto
 */
public interface DtoConverter<E extends Identifiable, D extends Dto<E>> {

    /**
     * Convert a Dto to an entity
     * @param entity an entity to be updated (required)
     * @param dto the dto
     * @return the updated entity
     */
    E convert(E entity, D dto);

    /**
     * create a new entity
     * @return the new entity
     */
    E createNewEntity();

    /**
     * create a new dto
     * @return the new dto
     */
    D createNewDto();
}

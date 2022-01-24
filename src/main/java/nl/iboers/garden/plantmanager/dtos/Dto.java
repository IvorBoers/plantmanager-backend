package nl.iboers.garden.plantmanager.dtos;

import nl.iboers.garden.plantmanager.entities.Identifiable;

/**
 * Data Transfer Object
 * @param <E> Entity to transfer
 */
public interface Dto<E extends Identifiable> extends Identifiable {

    /**
     * initialize this from the specified entity
     * @param entity entity
     */
    void from(E entity);
}

package nl.iboers.garden.plantmanager.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ivor Boers
 */
@Data
@Entity
@Table(name = "relocation_event")
public class RelocationEvent extends AbstractEvent {

    @ManyToOne
    private PlantLocation location;
}

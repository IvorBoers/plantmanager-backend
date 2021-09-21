package nl.iboers.garden.plantmanager.entities;

/**
 * @author Ivor
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "plant_died_event")
public class PlantDiedEvent extends AbstractEvent {

    @OneToOne
    private Plant plant;

    @Lob()
    private byte[] image;
}

package nl.iboers.garden.plantmanager.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author Ivor
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "seed_start_event")
public class SeedStartEvent extends AbstractEvent {

    @ManyToOne
    private SeedPackage seedPackage;

    @Lob()
    private byte[] image;
}

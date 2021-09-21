package nl.iboers.garden.plantmanager.entities;

/**
 * @author Ivor
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "produce_pick_event")
public class ProducePickEvent extends AbstractEvent {

    @ManyToOne
    private Plant plant;

    @Column
    private Float weight;

    @Column
    private Integer count;

    @Lob()
    private byte[] image;
}

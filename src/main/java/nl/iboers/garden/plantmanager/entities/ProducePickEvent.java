package nl.iboers.garden.plantmanager.entities;

/**
 * @author Ivor
 */

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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

    @Transient()
    @ManyToOne()
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "image_id")
    private Long imageId;
}

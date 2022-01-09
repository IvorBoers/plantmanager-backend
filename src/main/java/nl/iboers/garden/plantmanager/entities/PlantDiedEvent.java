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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "plant_died_event")
public class PlantDiedEvent extends AbstractEvent {

    @OneToOne
    private Plant plant;

    @Transient()
    @ManyToOne()
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "image_id")
    private Long imageId;
}

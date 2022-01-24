package nl.iboers.garden.plantmanager.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "produce_pick_event")
public class ProducePickEvent extends AbstractEvent {

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "plant_id", nullable = false)
    private Plant plant;

    @Column
    private Float weight;

    @Column
    private Integer count;

    @ManyToOne()
    @JoinColumn(name = "image_id")
    private Image image;

}

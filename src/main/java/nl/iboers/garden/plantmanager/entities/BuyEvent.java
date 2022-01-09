package nl.iboers.garden.plantmanager.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Ivor
 */
@Data
@Entity
@Table(name = "buy_event")
public class BuyEvent extends AbstractEvent {

    @Column(name = "price")
    private Float price;

    @Column(name = "store")
    private String store;

    @Transient()
    @ManyToOne()
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "image_id")
    private Long imageId;
}

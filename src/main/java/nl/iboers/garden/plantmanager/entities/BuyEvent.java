package nl.iboers.garden.plantmanager.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
}

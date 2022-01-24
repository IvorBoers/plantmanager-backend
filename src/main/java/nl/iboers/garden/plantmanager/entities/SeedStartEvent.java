package nl.iboers.garden.plantmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "seed_package_id")
    private SeedPackage seedPackage;

    @Lob()
    private byte[] image;
}

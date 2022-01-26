package nl.iboers.garden.plantmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * @author Ivor
 */
@Entity
@Table(name = "seed_start_event")
public class SeedStartEvent extends AbstractEvent {

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "seed_package_id")
    private SeedPackage seedPackage;

    @Lob()
    private byte[] image;

    public SeedPackage getSeedPackage() {
        return seedPackage;
    }

    public void setSeedPackage(SeedPackage seedPackage) {
        this.seedPackage = seedPackage;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

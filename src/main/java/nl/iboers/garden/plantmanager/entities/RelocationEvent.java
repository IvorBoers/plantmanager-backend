package nl.iboers.garden.plantmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Ivor Boers
 */
@Entity
@Table(name = "relocation_event")
public class RelocationEvent extends AbstractEvent {

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @ManyToOne(optional = false)
    private PlantLocation location;

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public PlantLocation getLocation() {
        return location;
    }

    public void setLocation(PlantLocation location) {
        this.location = location;
    }


}

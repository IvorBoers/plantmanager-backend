package nl.iboers.garden.plantmanager.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Table(name = "plant")
public class Plant implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private PlantSpecies plantSpecies;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BuyEvent buyEvent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SeedStartEvent seedStartEvent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PlantDiedEvent plantDiedEvent;

    @SortNatural
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SortedSet<RelocationEvent> relocationEvents = new TreeSet<>();

    @SortNatural
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SortedSet<ProducePickEvent> producePickEvents = new TreeSet<>();


    public Optional<BuyEvent> getBuyEventAsOptional() {
        return Optional.ofNullable(buyEvent);
    }

    public Optional<PlantDiedEvent> getPlantDiedEventAsOptional() {
        return Optional.ofNullable(plantDiedEvent);
    }

    public Optional<SeedStartEvent> getSeedStartEventAsOptional() {
        return Optional.ofNullable(seedStartEvent);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlantSpecies getPlantSpecies() {
        return plantSpecies;
    }

    public void setPlantSpecies(PlantSpecies plantSpecies) {
        this.plantSpecies = plantSpecies;
    }

    public BuyEvent getBuyEvent() {
        return buyEvent;
    }

    public void setBuyEvent(BuyEvent buyEvent) {
        this.buyEvent = buyEvent;
    }

    public SeedStartEvent getSeedStartEvent() {
        return seedStartEvent;
    }

    public void setSeedStartEvent(SeedStartEvent seedStartEvent) {
        this.seedStartEvent = seedStartEvent;
    }

    public PlantDiedEvent getPlantDiedEvent() {
        return plantDiedEvent;
    }

    public void setPlantDiedEvent(PlantDiedEvent plantDiedEvent) {
        this.plantDiedEvent = plantDiedEvent;
    }

    public SortedSet<RelocationEvent> getRelocationEvents() {
        return relocationEvents;
    }

    public void setRelocationEvents(SortedSet<RelocationEvent> relocationEvents) {
        this.relocationEvents = relocationEvents;
    }

    public SortedSet<ProducePickEvent> getProducePickEvents() {
        return producePickEvents;
    }

    public void setProducePickEvents(SortedSet<ProducePickEvent> producePickEvents) {
        this.producePickEvents = producePickEvents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Plant that = (Plant) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    public String toString() {
        return "Plant with id=" + id;
    }
}

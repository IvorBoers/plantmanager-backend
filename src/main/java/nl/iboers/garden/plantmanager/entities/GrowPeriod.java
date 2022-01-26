package nl.iboers.garden.plantmanager.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Ivor Boers
 */
@Entity
@Table(name = "growperiod")
public class GrowPeriod implements Identifiable, Comparable<GrowPeriod> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private PlantSpecies plantSpecies;

    @OneToOne(optional = false, targetEntity = WeekOfMonth.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private WeekOfMonth start;

    @OneToOne(optional = false, targetEntity = WeekOfMonth.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private WeekOfMonth end;

    @Enumerated(EnumType.ORDINAL)
    private GrowPhaseEnum growPhase;

    @Column(name="description", length = 2500)
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeekOfMonth getStart() {
        return start;
    }

    public void setStart(WeekOfMonth start) {
        this.start = start;
    }

    public WeekOfMonth getEnd() {
        return end;
    }

    public void setEnd(WeekOfMonth end) {
        this.end = end;
    }

    public GrowPhaseEnum getGrowPhase() {
        return growPhase;
    }

    public void setGrowPhase(GrowPhaseEnum growPhase) {
        this.growPhase = growPhase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public PlantSpecies getPlantSpecies() {
        return plantSpecies;
    }

    public void setPlantSpecies(PlantSpecies plantSpecies) {
        this.plantSpecies = plantSpecies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GrowPeriod that = (GrowPeriod) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public int compareTo(GrowPeriod o) {
        return start.compareTo(o.start);
    }

    @Override
    public String toString() {
        return "GrowPeriod{" +
                "id=" + id +
                ", plantSpeciesId=" + (plantSpecies == null ? "null" : plantSpecies.getId()) +
                ", start=" + start +
                ", end=" + end +
                ", growPhase=" + growPhase +
                ", description='" + description + '\'' +
                '}';
    }
}

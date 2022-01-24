package nl.iboers.garden.plantmanager.entities;

import lombok.Data;
import lombok.ToString;
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
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Data
@ToString
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
}

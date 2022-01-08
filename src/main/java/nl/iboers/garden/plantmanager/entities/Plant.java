package nl.iboers.garden.plantmanager.entities;

import lombok.Data;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Entity
@Data
@Table(name = "plant")
public class Plant implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne()
    private PlantSpecies plantSpecies;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BuyEvent buyEvent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SeedStartEvent seedStartEvent;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PlantDiedEvent plantDiedEvent;

    @SortNatural
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SortedSet<RelocationEvent> relocationEvents = new TreeSet<>();

}

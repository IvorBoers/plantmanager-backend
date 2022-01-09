package nl.iboers.garden.plantmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author Ivor
 */
@Data
@Entity
@Table(name = "seed_package")
public class SeedPackage implements Identifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "description")
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "expiration_date", nullable = false)
    private OffsetDateTime expirationDate;

    @Column(name = "percentFull")
    private Long percentFull = 100L;

    @ManyToOne()
    private PlantSpecies plantSpecies;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BuyEvent buyEvent;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SeedStartEvent> seedStartEvents;

    @Transient()
    @ManyToOne()
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "image_id")
    private Long imageId;
}

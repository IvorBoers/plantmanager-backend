package nl.iboers.garden.plantmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;



/**
 * Species like Tomato or Cucumber
 */
@Entity
@Data
@Table(name = "plant_species")
public class PlantSpecies implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Lob()
    private byte[] image;

    @Min(value = 0)
    @Column(name = "maximum_height")
    private Integer maximumHeight;

    @Min(value = 0)
    @Column(name = "spacing")
    private Integer spacing;

    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "sungrade")
    private Integer sungrade;

    @Column(name = "description")
    private String description;

    @Transient
    @JsonIgnoreProperties(value = { "parents", "children" }, allowSetters = true)
    private Set<PlantSpecies> children = new HashSet<>();

    @JsonIgnoreProperties(value = { "parents", "children" }, allowSetters = true)
    @Transient
    private PlantSpecies parent;

    @Column(name = "parent_id")
    private Long parentId;


}

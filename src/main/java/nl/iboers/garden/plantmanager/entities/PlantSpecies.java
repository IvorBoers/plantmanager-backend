package nl.iboers.garden.plantmanager.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.SortNatural;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Species like Tomato or Cucumber
 */
@Entity
@Table(name = "plant_species")
public class PlantSpecies implements Identifiable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne()
    private PlantSpeciesType type;

    @Transient()
    @ManyToOne()
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "image_id")
    private Long imageId;

    @Min(value = 0)
    @Column(name = "maximum_height")
    private Integer maximumHeight;

    @Min(value = 0)
    @Max(value = 100)
    @Column(name = "sungrade")
    private Integer sungrade;

    @Column(name = "description", length = 2500)
    private String description;

    @Transient
    @JsonIgnoreProperties(value = { "parent", "children" }, allowSetters = true)
    private Set<PlantSpecies> children = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "parent", "children" }, allowSetters = true)
    private PlantSpecies parent;

    @Column(name = "parent_id")
    private Long parentId;

    @SortNatural
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "plantSpecies")
    private SortedSet<GrowPeriod> growPeriods = new TreeSet<>();

    public Optional<PlantSpeciesType> getTypeAsOptional() {
        return Optional.ofNullable(type);
    }


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantSpeciesType getType() {
        return type;
    }

    public void setType(PlantSpeciesType type) {
        this.type = type;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Integer getMaximumHeight() {
        return maximumHeight;
    }

    public void setMaximumHeight(Integer maximumHeight) {
        this.maximumHeight = maximumHeight;
    }

    public Integer getSungrade() {
        return sungrade;
    }

    public void setSungrade(Integer sungrade) {
        this.sungrade = sungrade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PlantSpecies> getChildren() {
        return children;
    }

    public void setChildren(Set<PlantSpecies> children) {
        this.children = children;
    }

    public PlantSpecies getParent() {
        return parent;
    }

    public void setParent(PlantSpecies parent) {
        this.parent = parent;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public SortedSet<GrowPeriod> getGrowPeriods() {
        return growPeriods;
    }

    public void setGrowPeriods(SortedSet<GrowPeriod> growPeriods) {
        this.growPeriods = growPeriods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PlantSpecies that = (PlantSpecies) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public String toString() {
        return "PlantSpecies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + (type == null ? "null" : type.getId()) +
                ", imageId=" + imageId +
                ", maximumHeight=" + maximumHeight +
                ", sungrade=" + sungrade +
                ", description='" + description + '\'' +
                ", children=" + children.size() +
                ", parentId=" + parentId +
                ", growPeriods=" + growPeriods +
                '}';
    }
}

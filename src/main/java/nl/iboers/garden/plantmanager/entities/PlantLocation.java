package nl.iboers.garden.plantmanager.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * @author Ivor Boers
 */
@Entity
@Data
@Table(name = "plant_location")
public class PlantLocation implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "color", nullable = false)
    private String color = "#ffffff";

    @Transient()
    @ManyToOne()
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "image_id")
    private Long imageId;
}

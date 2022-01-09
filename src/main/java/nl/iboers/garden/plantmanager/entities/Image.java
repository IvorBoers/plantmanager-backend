package nl.iboers.garden.plantmanager.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author Ivor Boers
 */
@Entity
@Data
@Table(name = "image")
public class Image implements Identifiable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob()
    private byte[] bytes;

    public Image() {}
    public Image(byte[] bytes) {
        this.bytes = bytes;
    }
}

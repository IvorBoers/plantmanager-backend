package nl.iboers.garden.plantmanager.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.OffsetDateTime;

/**
 * @author Ivor
 */
@Data
@MappedSuperclass
public class AbstractEvent implements Identifiable, Comparable<AbstractEvent> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "event_date", nullable = false)
    private OffsetDateTime date;

    @Column(name = "description")
    private String description;

    @Override
    public int compareTo(AbstractEvent o) {
        return this.date.compareTo(o.date);
    }

    public String getTypeName() {
        return this.getClass().getSimpleName();
    }
}

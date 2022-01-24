package nl.iboers.garden.plantmanager.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import nl.iboers.garden.plantmanager.entities.AbstractEvent;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * @author Ivor Boers
 */
@Data
public class AbstractEventDto<E extends AbstractEvent> implements Dto<E> {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private OffsetDateTime date = OffsetDateTime.now(ZoneId.systemDefault());
    private String description;
    private String typeName;

    @Override
    public void from(E entity) {
        this.id = entity.getId();
        this.description = entity.getDescription();
        this.date = entity.getDate();
        this.typeName = entity.getTypeName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AbstractEventDto<?> that = (AbstractEventDto<?>) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}

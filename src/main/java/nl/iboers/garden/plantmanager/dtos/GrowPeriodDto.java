package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import nl.iboers.garden.plantmanager.entities.GrowPeriod;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Ivor Boers
 */
@Data
public class GrowPeriodDto implements Dto<GrowPeriod>, Comparable<GrowPeriodDto> {

    private Long id;
    private String phase;
    private WeekOfMonthDto start;
    private WeekOfMonthDto end;
    private String description;

    public GrowPeriodDto() {}

    public GrowPeriodDto(GrowPeriod entity) {
        from(entity);
    }


    @Override
    public void from(GrowPeriod entity) {
        id = entity.getId();
        description = entity.getDescription();
        phase = entity.getGrowPhase().name();
        start = new WeekOfMonthDto(entity.getStart());
        end = new WeekOfMonthDto(entity.getEnd());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(GrowPeriodDto o) {
        return start.compareTo(o.start);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        GrowPeriodDto that = (GrowPeriodDto) o;

        return new EqualsBuilder().append(id, that.id).append(phase, that.phase).append(start, that.start).append(end, that.end).append(description, that.description).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(phase).append(start).append(end).append(description).toHashCode();
    }
}

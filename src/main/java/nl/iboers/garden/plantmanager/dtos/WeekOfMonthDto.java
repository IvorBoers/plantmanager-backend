package nl.iboers.garden.plantmanager.dtos;

import lombok.Data;
import nl.iboers.garden.plantmanager.entities.WeekOfMonth;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Ivor Boers
 */
@Data
public class WeekOfMonthDto implements Dto<WeekOfMonth>, Comparable<WeekOfMonthDto> {
    private Long id;
    private Integer month;
    private Integer week;

    public WeekOfMonthDto() {}

    public WeekOfMonthDto(WeekOfMonth entity) {
        from(entity);
    }

    @Override
    public void from(WeekOfMonth entity) {
        id = entity.getId();
        month = entity.getMonth();
        week = entity.getWeek();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public int compareTo(WeekOfMonthDto o) {
        if (month < o.month) {
            return -1;
        }
        if (month.equals(o.month)) {
            return week - o.week;
        }
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WeekOfMonthDto that = (WeekOfMonthDto) o;

        return new EqualsBuilder().append(id, that.id).append(month, that.month).append(week, that.week).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(month).append(week).toHashCode();
    }
}

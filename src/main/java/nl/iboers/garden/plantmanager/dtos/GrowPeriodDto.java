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
    private static final int WEEKS = 12 * 4;
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


    public boolean[] getCalendarRow() {
        boolean[] monthsWithFourWeeks = new boolean[WEEKS];
        int month = 0;
        int week = 0;
        for (int i = 0; i < WEEKS; i++) {
            monthsWithFourWeeks[i] = monthsWithFourWeeks[i] || isInWeek(month, week);
            week++;
            if (week % 4 == 0) {
                month++;
                week = 0;
            }
        }
        return monthsWithFourWeeks;
    }

    private boolean isInWeek(int month, int week) {
        if (isOutsideMonthRange(month)) {
            return false;
        }
        return !isInStartMonthButBeforeWeek(getStart(), month, week) && !isInEndMonthButAfterWeek(getEnd(), month, week);
    }

    private boolean isInEndMonthButAfterWeek(WeekOfMonthDto end, int month, int week) {
        return month == end.getMonth() && week > end.getWeek();
    }

    private boolean isInStartMonthButBeforeWeek(WeekOfMonthDto start, int month, int week) {
        return month == start.getMonth() && week < start.getWeek();
    }

    private boolean isOutsideMonthRange(int month) {
        return month < getStart().getMonth() || month > getEnd().getMonth();
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

package nl.iboers.garden.plantmanager.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Ivor Boers
 */
@Entity
@Table(name = "weekofmonth")
public class WeekOfMonth implements Comparable<WeekOfMonth>, Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //    0 based (0, 1,2 or 3)
    @Column(nullable = false)
    private Integer week;

    //    0 based (0, 1,2 to (including) 11)
    @Column(nullable = false)
    private Integer month;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        WeekOfMonth that = (WeekOfMonth) o;

        return new EqualsBuilder().append(id, that.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }

    @Override
    public int compareTo(WeekOfMonth o) {
        if (month < o.month) {
            return -1;
        }
        if (month.equals(o.month)) {
            return week - o.week;
        }
        return 1;
    }

    @Override
    public String toString() {
        return "WeekOfMonth{" +
                "id=" + id +
                ", week=" + week +
                ", month=" + month +
                '}';
    }
}

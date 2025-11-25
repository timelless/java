package org.acme.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@jakarta.persistence.Table(name = "Sbo_Table", uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Table extends BaseEntity {

    @NotNull
//    @Column(nullable = false)
    private String name;

    @NotNull
//    @Column(nullable = false)
    private Integer seatCount;

    @NotNull
//    @Column(nullable = false)
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

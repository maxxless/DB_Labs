package com.pavelchak;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "team", schema = "db_jdbcc", catalog = "")
public class TeamEntity {
    private String team;
    private Collection<PersonEntity> peopleByCity;

    public TeamEntity(){}
    public TeamEntity(String c){
        team =c;
    }

    @Id
    @Column(name = "team", nullable = false, length = 25)
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeamEntity that = (TeamEntity) o;

        if (team != null ? !team.equals(that.team) : that.team != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return team != null ? team.hashCode() : 0;
    }

    @OneToMany(mappedBy = "cityByCity")
    public Collection<PersonEntity> getPeopleByCity() {
        return peopleByCity;
    }

    public void setPeopleByCity(Collection<PersonEntity> peopleByCity) {
        this.peopleByCity = peopleByCity;
    }
}

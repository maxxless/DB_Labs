package com.my;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "university", schema = "db_hibernate", catalog = "")
public class UniversityEntity {
    private String university;
    private Collection<StudikEntity> studikByUniversity;

    public UniversityEntity(){}
    public UniversityEntity(String u){
        university=u;
    }

    @Id
    @Column(name = "University", nullable = false, length = 25)
    public String getUniversity() {
        return university ;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UniversityEntity that = (UniversityEntity) o;

        if (university != null ? !university.equals(that.university) : that.university != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return university != null ? university.hashCode() : 0;
    }

    @OneToMany(mappedBy = "universityByUniversity")
    public Collection<StudikEntity> getStudikByUniversity() {
        return studikByUniversity;
    }

    public void setStudikByUniversity(Collection<StudikEntity> studikByUniversity) {
        this.studikByUniversity = studikByUniversity;
    }
}

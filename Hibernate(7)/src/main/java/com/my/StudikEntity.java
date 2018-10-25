package com.my;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "studik", schema = "db_hibernate", catalog = "")
public class StudikEntity {
    private int idStudika;
    private String surname;
    private String name;
    private String email;
    private UniversityEntity universityByUniversity;
    private List<KomisiyaEntity> komisiys;

    public StudikEntity()
    {}
    public StudikEntity(String s,String n,String university,String e){
        surname=s;
        name=n;
        universityByUniversity=new UniversityEntity(university);
        email=e;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDStudika", nullable = false)
    public int getIdStudika() {
        return idStudika;
    }

    public void setIdStudika(int idStudika) {
        this.idStudika = idStudika;
    }

    @Column(name = "Surname", nullable = false, length = 25)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "Name", nullable = false, length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "Email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudikEntity that = (StudikEntity) o;

        if (idStudika != that.idStudika) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idStudika;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "University", referencedColumnName = "University", nullable = false)
    public UniversityEntity getUniversityByUniversity() {
        return universityByUniversity;
    }

    public void setUniversityByUniversity(UniversityEntity universityByUniversity) {
        this.universityByUniversity = universityByUniversity;
    }

    @ManyToMany(mappedBy = "studiks")
    public List<KomisiyaEntity> getKomisiys() {
        return komisiys;
    }

    public void addBookEntity(KomisiyaEntity komisiyaEntity){
        if(!getKomisiys().contains(komisiyaEntity)){
            getKomisiys().add(komisiyaEntity);
        }
        if(!komisiyaEntity.getStudiks().contains(this)){
            komisiyaEntity.getStudiks().add(this);
        }
    }

    public void setKomisiys(List<KomisiyaEntity> komisiys) {
        this.komisiys = komisiys;
    }
}
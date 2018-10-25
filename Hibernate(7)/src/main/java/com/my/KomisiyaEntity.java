package com.my;

import javax.persistence.*;
import javax.print.attribute.standard.MediaSize;
import java.util.List;

@Entity
@Table(name = "komisiya", schema = "db_hibernate", catalog = "")
public class KomisiyaEntity {
    private int idKomisiyi;
    private String Name;
    private String Student;
    private int Teacher;
    private List<StudikEntity> studiks;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDKomisiyi", nullable = false)
    public int getIdKomisiyi() {
        return idKomisiyi;
    }

    public void setIdKomisiyi(int idKomisiyi) {
        this.idKomisiyi = idKomisiyi;
    }

    @Basic
    @Column(name = "Name", nullable = false, length = 45)
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    @Basic
    @Column(name = "Student", nullable = false, length = 45)
    public String getStudent() {
        return Student;
    }

    public void setStudent(String Student) {
        this.Student = Student;
    }

    @Basic
    @Column(name = "Teacher", nullable = false)
    public int getTeacher() { return Teacher; }

    public void setTeacher(int teacher) {
        this.Teacher = Teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KomisiyaEntity that = (KomisiyaEntity) o;

        if (idKomisiyi != that.idKomisiyi) return false;
        if (Teacher != that.Teacher) return false;
        if (Name != null ? !Name.equals(that.Name) : that.Name != null) return false;
        if (Student != null ? !Student.equals(that.Student) : that.Student != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idKomisiyi;
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (Student != null ? Student.hashCode() : 0);
        return result;
    }

    @ManyToMany
    @JoinTable(name = "Vykladach", catalog = "", schema = "db_hibernate", joinColumns = @JoinColumn(name = "IDKomisiyi1", referencedColumnName = "IDKomisiyi", nullable = false), inverseJoinColumns = @JoinColumn(name = "IDStudika", referencedColumnName = "IDStudika", nullable = false))
    public List<StudikEntity> getStudiks() {
        return studiks;
    }

    public void setStudiks(List<StudikEntity> studiks) {
        this.studiks = studiks;
    }
}

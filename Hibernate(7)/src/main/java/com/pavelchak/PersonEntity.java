package com.pavelchak;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person", schema = "db_jdbc")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPerson", nullable = false)
    private int idPerson;
    @Column(name = "Surname", nullable = false, length = 25)
    private String surname;
    @Column(name = "Name", nullable = false, length = 25)
    private String name;


    @Column(name = "Email", nullable = true, length = 45)
    private String email;
    @ManyToOne
    @JoinColumn(name = "City", referencedColumnName = "City", nullable = false)
    private CityEntity cityByCity;
    @ManyToMany(mappedBy = "persons")
    private List<BookEntity> books;

    public PersonEntity()
    {}

    public PersonEntity(String s,String n,String city,String e){
        surname=s;
        name=n;
        cityByCity=new CityEntity(city);
        email=e;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


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

        PersonEntity that = (PersonEntity) o;

        if (idPerson != that.idPerson) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPerson;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }


    public CityEntity getCityByCity() {
        return cityByCity;
    }

    public void setCityByCity(CityEntity cityByCity) {
        this.cityByCity = cityByCity;
    }


    public List<BookEntity> getBooks() {
        return books;
    }

    public void addBookEntity(BookEntity bookEntity){
        if(!getBooks().contains(bookEntity)){
            getBooks().add(bookEntity);
        }
        if(!bookEntity.getPersons().contains(this)){
            bookEntity.getPersons().add(this);
        }
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}

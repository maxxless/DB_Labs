package com.dbspring.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fastfoodmarket")
public class FastFoodMarket {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fastfoodmarket_id", nullable = false)
    private Long fastfoodmarket_id;
    @Column(name = "fastfoodmarket_surname", nullable = false, length = 25)
    private String fastfoodmarket_surname;
    @Column(name = "fastfoodmarket_name", nullable = false, length = 25)
    private String fastfoodmarket_name;
    @Column(name = "email", nullable = true, length = 45)
    private String email;
    @Column(name = "street", nullable = true, length = 30)
    private String street;
    @Column(name = "apartment", nullable = true, length = 10)
    private String apartment;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;
    @ManyToMany
    @JoinTable(name = "market_fastfood",
            joinColumns = @JoinColumn(name = "fastfoodmarket_id", referencedColumnName = "fastfoodmarket_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fastfood_id", referencedColumnName = "fastfood_id", nullable = false))
    private Set<FastFood> fastFoods;

    FastFoodMarket(){}
    FastFoodMarket(String fastfoodmarket_surname, String fastfoodmarket_name, String email, String street, String apartment){
        this.fastfoodmarket_surname=fastfoodmarket_surname;
        this.fastfoodmarket_name=fastfoodmarket_name;
        this.email=email;
        this.street=street;
        this.apartment=apartment;
    }

    public Long getId() {
        return fastfoodmarket_id;
    }
    public void setId(Long idFastFoodMarket) {
        this.fastfoodmarket_id = idFastFoodMarket;
    }

    public String getSurname() {
        return fastfoodmarket_surname;
    }
    public void setSurname(String fastfoodmarket_surname) {
        this.fastfoodmarket_surname = fastfoodmarket_surname;
    }

    public String getName() {
        return fastfoodmarket_name;
    }
    public void setName(String fastfoodmarket_name) {
        this.fastfoodmarket_name = fastfoodmarket_name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getApartment() {
        return apartment;
    }
    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public Set<FastFood> getFastFoodMarkets() {
        return fastFoods;
    }
    public void setFastFoodMarkets(Set<FastFood> fastFoods) {
        this.fastFoods = fastFoods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FastFoodMarket that = (FastFoodMarket) o;
        if (fastfoodmarket_id != null ? !fastfoodmarket_id.equals(that.fastfoodmarket_id) : that.fastfoodmarket_id != null) return false;
        if (fastfoodmarket_surname != null ? !fastfoodmarket_surname.equals(that.fastfoodmarket_surname) : that.fastfoodmarket_surname != null) return false;
        if (fastfoodmarket_name != null ? !fastfoodmarket_name.equals(that.fastfoodmarket_name) : that.fastfoodmarket_name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (apartment != null ? !apartment.equals(that.apartment) : that.apartment != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = fastfoodmarket_id != null ? fastfoodmarket_id.hashCode() : 0;
        result = 31 * result + (fastfoodmarket_surname != null ? fastfoodmarket_surname.hashCode() : 0);
        result = 31 * result + (fastfoodmarket_name != null ? fastfoodmarket_name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }
}

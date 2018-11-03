package com.dbspring.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "fastfood")
public class FastFood {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fastfood_id", nullable = false)
    private Long fastfood_id;
    @Column(name = "fastfood_name", nullable = false, length = 45)
    private String fastfood_name;
    @Column(name = "author", nullable = false, length = 45)
    private String author;
    @Column(name = "seller", nullable = true, length = 50)
    private String seller;
    @Column(name = "year_of_creating", nullable = true)
    private Integer year_of_creating;
    @Column(name = "amount", nullable = false)
    private Integer amount;
    @ManyToMany(mappedBy = "fastFoods")
    private Set<FastFoodMarket> fastFoodMarkets;

    FastFood(){}
    FastFood(String fastfood_name, String author, String seller, Integer year_of_creating){
        this.fastfood_name=fastfood_name;
        this.author=author;
        this.seller=seller;
        this.year_of_creating=year_of_creating;
    }

    public Long getId() {
        return fastfood_id;
    }
    public void setId(Long idFastFood) {
        this.fastfood_id = idFastFood;
    }

    public String getFastFoodName() {
        return fastfood_name;
    }
    public void setFastFoodName(String fastfood_name) {
        this.fastfood_name = fastfood_name;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSeller() {
        return seller;
    }
    public void setSeller(String seller) {
        this.seller = seller;
    }

    public Integer getYearOfCreating() {
        return year_of_creating;
    }
    public void setYearOfCreating(Integer year_of_creating) {
        this.year_of_creating = year_of_creating;
    }

    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Set<FastFoodMarket> getFastFoodMarkets() {
        return fastFoodMarkets;
    }
    public void setPersons(Set<FastFoodMarket> fastFoodMarkets) {
        this.fastFoodMarkets = fastFoodMarkets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FastFood that = (FastFood) o;
        if (fastfood_id != null ? !fastfood_id.equals(that.fastfood_id) : that.fastfood_id != null) return false;
        if (fastfood_name != null ? !fastfood_name.equals(that.fastfood_name) : that.fastfood_name != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (seller != null ? !seller.equals(that.seller) : that.seller != null) return false;
        if (year_of_creating != null ? !year_of_creating.equals(that.year_of_creating) : that.year_of_creating != null) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = fastfood_id != null ? fastfood_id.hashCode() : 0;
        result = 31 * result + (fastfood_name != null ? fastfood_name.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (seller != null ? seller.hashCode() : 0);
        result = 31 * result + (year_of_creating != null ? year_of_creating.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }
}

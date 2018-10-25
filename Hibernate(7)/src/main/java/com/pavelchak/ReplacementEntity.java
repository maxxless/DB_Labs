package com.pavelchak;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "replacement", schema = "db_jdbcc")
public class ReplacementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "IDreplacement", nullable = false)
    private int idreplacement;
    @Basic
    @Column(name = "replacement", nullable = false, length = 45)
    private String replacement;
    @ManyToMany
    @JoinTable(name = "connect", schema = "db_jdbcc",
            joinColumns = @JoinColumn(name = "IDreplacement", referencedColumnName = "IDreplacement", nullable = false), inverseJoinColumns = @JoinColumn(name = "IDplayer", referencedColumnName = "IDplayer", nullable = false))
    private List<PlayerEntity> persons;

    
    public int getIdreplacement() {
        return idreplacement;
    }

    public void setIdreplacement(int idreplacement) {
        this.idreplacement = idreplacement;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReplacementEntity that = (ReplacementEntity) o;

        if (idreplacement != that.idreplacement) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = idreplacement;
        result = 31 * result + (replacement != null ? replacement.hashCode() : 0);

        return result;
    }

    public List<PlayerEntity> getPlayers() {
        return persons;
    }

    public void setPlayer(List<PlayerEntity> persons) {
        this.persons = persons;
    }
}

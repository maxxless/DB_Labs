package com.pavelchak;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player", schema = "db_jdbcc")
public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDplayer", nullable = false)
    private int idplayer;
    @Column(name = "Surname", nullable = false, length = 25)
    private String surname;
    @Column(name = "Name", nullable = false, length = 25)
    private String name;


    @Column(name = "player", nullable = true, length = 45)
    private String player;
    @ManyToOne
    @JoinColumn(name = "team", referencedColumnName = "team", nullable = false)
    private TeamEntity teamByTeam;
    @ManyToMany(mappedBy = "persons")
    private List<ReplacementEntity> replacements;

    public PlayerEntity()
    {}

    public PlayerEntity(String s, String n, String team, String e){
        surname=s;
        name=n;
        teamByTeam=new TeamEntity(team);
        player =e;
    }

    public int getIdplayer() {
        return idplayer;
    }

    public void setIdplayer(int idplayer) {
        this.idplayer = idplayer;
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


    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlayerEntity that = (PlayerEntity) o;

        if (idplayer != that.idplayer) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (player != null ? !player.equals(that.player) : that.player != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idplayer;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }


    public TeamEntity getteamByTeam() {
        return teamByTeam;
    }

    public void setteamByTeam(TeamEntity teamByTeam) {
        this.teamByTeam = teamByTeam;
    }


    public List<ReplacementEntity> getReplacements() {
        return replacements;
    }

    public void addBookEntity(ReplacementEntity replacementEntity){
        if(!getReplacements().contains(replacementEntity)){
            getReplacements().add(replacementEntity);
        }
        if(!replacementEntity.getPlayers().contains(this)){
            replacementEntity.getPlayers().add(this);
        }
    }



    public void setReplacements(List<ReplacementEntity> replacements) {
        this.replacements = replacements;
    }
}

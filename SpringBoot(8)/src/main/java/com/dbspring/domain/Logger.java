package com.dbspring.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logger")
public class Logger {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logger_id", nullable = false)
    private Long logger_id;
    @Column(name = "fastfood", nullable = false, length = 50)
    private String fastfood;
    @Column(name = "fastfoodmarket", nullable = false, length = 90)
    private String fastfoodmarket;
    @Column(name = "Action", nullable = false, length = 10)
    private String action;
    @Column(name = "time_stamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Column(name = "user", nullable = true, length = 50)
    private String user;

    public Long getId() {
        return logger_id;
    }
    public void setId(Long logger_id) {
        this.logger_id = logger_id;
    }

    public String getFastFoodMarket() {
        return fastfoodmarket;
    }
    public void setFastFoodMarket(String fastfoodmarket) {
        this.fastfoodmarket = fastfoodmarket;
    }

    public String getFastFood() {
        return fastfood;
    }
    public void setFastFood(String fastfood) {
        this.fastfood = fastfood;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logger that = (Logger) o;
        if (logger_id != null ? !logger_id.equals(that.logger_id) : that.logger_id != null) return false;
        if (fastfood != null ? !fastfood.equals(that.fastfood) : that.fastfood != null) return false;
        if (fastfoodmarket != null ? !fastfoodmarket.equals(that.fastfoodmarket) : that.fastfoodmarket != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = logger_id != null ? logger_id.hashCode() : 0;
        result = 31 * result + (fastfood != null ? fastfood.hashCode() : 0);
        result = 31 * result + (fastfoodmarket != null ? fastfoodmarket.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}

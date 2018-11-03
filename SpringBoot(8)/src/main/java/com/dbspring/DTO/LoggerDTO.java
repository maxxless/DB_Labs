package com.dbspring.DTO;

import com.dbspring.domain.Logger;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class LoggerDTO extends ResourceSupport {
    Logger logger;
    public LoggerDTO(Logger logger, Link selfLink) {
        this.logger=logger;
        add(selfLink);
    }

    public Long getLoggerId() {
        return logger.getId();
    }

    public String getFastFoodMarket() {
        return logger.getFastFoodMarket();
    }

    public String getFastFood() {
        return logger.getFastFood();
    }

    public String getAction() {
        return logger.getAction();
    }

    public String getUser() {
        return logger.getUser();
    }

    public String getTime() {
        return logger.getTimeStamp().toString();
    }
}

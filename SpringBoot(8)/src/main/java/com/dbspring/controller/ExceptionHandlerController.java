package com.dbspring.controller;

import com.dbspring.DTO.MessageDTO;
import com.dbspring.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchCityException.class)
    ResponseEntity<MessageDTO> handleNoSushCityException(){
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such city not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchFastFoodMarketException.class)
    ResponseEntity<MessageDTO> handleNoSuchFastFoodMarketException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such fastfoodmarket not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchFastFoodException.class)
    ResponseEntity<MessageDTO> handleNoSuchFastFoodException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such fastfood not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistsFastFoodMarketsForCityException.class)
    ResponseEntity<MessageDTO> handleExistsFastFoodMarketsForCityException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are fastfoodmarkets for this city"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ExistsFastFoodMarketForFastFoodException.class)
    ResponseEntity<MessageDTO> handleExistsFastFoodsForFastFoodMarketException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Delete imposible. There are fastfoods for this person"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AlreadyExistsFastFoodInFastFoodMarketException.class)
    ResponseEntity<MessageDTO> handleAlreadyExistsFastFoodInFastFoodMarketException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Add imposible. The fastfoodmarket already contain this fastfood"), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(FastFoodAbsentException.class)
    ResponseEntity<MessageDTO> handleFastFoodAbsentException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Now this fastfood is absent"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FastFoodMarketHasNotFastFoodException.class)
    ResponseEntity<MessageDTO> handleFastFoodMarketHasNotFastFoodException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("The fastfoodmarket hasn't this fastfood"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchLogException.class)
    ResponseEntity<MessageDTO> handleNoSuchLogException() {
        return new ResponseEntity<MessageDTO>(new MessageDTO("Such log not found"), HttpStatus.NOT_FOUND);
    }

}

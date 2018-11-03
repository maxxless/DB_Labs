USE db_spring;

DELIMITER //
CREATE TRIGGER AfterInsertFastFoodFastFoodMarket
AFTER INSERT
ON market_fastfood FOR EACH ROW
BEGIN
	DECLARE fastfoodmarketname VARCHAR(50);
    DECLARE fastfoodname VARCHAR(90);
    SELECT CONCAT(fastfoodmarket_name, ' ', fastfoodmarket_surname) INTO fastfoodname
    FROM fastfoodmarket WHERE fastfoodmarket_id=new.fastfoodmarket_id;
    SELECT CONCAT(fastfood_name, ' / ', Author) INTO fastfoodname
    FROM book WHERE fastfood_id=new.fastfood_id;
	INSERT INTO logger (fastfoodmarket, fastfood, action,
								time_stamp, user)
	VALUES(fastfoodmarketname,  fastfoodname, 'GOT', NOW(), USER() );
END //
DELIMITER ;

DELIMITER //
CREATE TRIGGER AfterDeleteFastFoodMarketFastFood
AFTER DELETE
ON market_fastfood FOR EACH ROW
BEGIN
	DECLARE fastfoodmarketname VARCHAR(50);
    DECLARE fastfoodname VARCHAR(90);
    SELECT CONCAT(fastfoodmarket_name, ' ', fastfoodmarket_surname) INTO fastfoodmarketname
    FROM person WHERE fastfoodmarket_id=old.fastfoodmarket_id;
    SELECT CONCAT(fastfood_name, ' / ', author) INTO fastfoodname
    FROM book WHERE fastfood_id=old.fastfood_id;
	INSERT INTO Logger (fastfoodmarket, fastfood, action,
                      time_stamp, user)
	VALUES(fastfoodmarketname,  fastfoodname, 'GAVEBACK', NOW(), USER() );
END //
DELIMITER ;







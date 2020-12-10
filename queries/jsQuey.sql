create database vguess;
use vguess;
create table javascript (word VARCHAR(255),hint VARCHAR(255));

INSERT INTO javascript (word, hint) VALUES
 ("javascript","best languages"),
  ("console","print in javascript console")
;
select * from javascript;
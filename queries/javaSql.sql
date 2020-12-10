create database vguess;
use vguess;
create table java (word VARCHAR(255),hint VARCHAR(255));

INSERT INTO java (word, hint) VALUES
 ("java","one of the best languages"),
  ("object","instance of object")
;
select * from java;
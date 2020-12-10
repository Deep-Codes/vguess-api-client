const express = require('express');
const Router = express.Router();
const mysqlConnection = require('../connection')

let getJava ="SELECT * FROM `vguess`.java;"
let getJavascript ="SELECT * FROM `vguess`.javascript;"

let fillJava = "INSERT INTO `vguess`.java (word, hint) VALUES (? , ? )"
let fillJavascript = "INSERT INTO `vguess`.javascript (word, hint) VALUES (? , ? )"

// const jsData = require("../db/jsData.json");
//const javaData = require("../db/javaData.json");

Router.get('/:id' , (req,res) => {
  if(req.params.id === 'javascript'){
    mysqlConnection.query(getJavascript ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send(result)
    })
  }else if(req.params.id === 'java'){
    mysqlConnection.query(getJava ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send(result)
    })
  }
});

Router.post('/:id' , (req,res) => {
  const word = req.body.word;
  const hint = req.body.hint

  if(req.params.id === 'javascript'){
    mysqlConnection.query(fillJavascript, [word,hint] ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send('User Added To Database!')
    })
  }else if(req.params.id === 'java'){
    mysqlConnection.query(fillJava, [word,hint] ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send('User Added To Database!')
    })
  }
})


module.exports = Router;

const express = require('express');
const Router = express.Router();
const mysqlConnection = require('../connection')

let getJava ="SELECT * FROM `vguess`.java;"
let getJavascript ="SELECT * FROM `vguess`.javascript;"
let getPy ="SELECT * FROM `vguess`.python;"
let getCss ="SELECT * FROM `vguess`.css;"
let getHtml ="SELECT * FROM `vguess`.html;"

let fillJava = "INSERT INTO `vguess`.java (word, hint) VALUES (? , ? )"
let fillJavascript = "INSERT INTO `vguess`.javascript (word, hint) VALUES (? , ? )"
let fillPy = "INSERT INTO `vguess`.python (word, hint) VALUES (? , ? )"
let fillHtml = "INSERT INTO `vguess`.html (word, hint) VALUES (? , ? )"
let fillCss = "INSERT INTO `vguess`.css (word, hint) VALUES (? , ? )"

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
  else if(req.params.id === 'python'){
    mysqlConnection.query(getPy ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send(result)
    })
  }
  else if(req.params.id === 'css'){
    mysqlConnection.query(getCss ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send(result)
    })
  }
  else if(req.params.id === 'html'){
    mysqlConnection.query(getHtml ,  (err , result) => {
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
  else if(req.params.id === 'css'){
    mysqlConnection.query(fillCss, [word,hint] ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send('User Added To Database!')
    })
  }
  else if(req.params.id === 'html'){
    mysqlConnection.query(fillHtml, [word,hint] ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send('User Added To Database!')
    })
  }
  else if(req.params.id === 'python'){
    mysqlConnection.query(fillPy, [word,hint] ,  (err , result) => {
      if (err) {
        res.status(500).send({ error: "Something failed!" }); 
        return;
      }
      res.send('User Added To Database!')
    })
  }
})


module.exports = Router;

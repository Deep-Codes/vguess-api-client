const express = require('express');
const Router = express.Router();
const mysqlConnection = require('../connection')

let getJava ="SELECT * FROM `vguess`.java;"
let getJavascript ="SELECT * FROM `vguess`.javascript;"

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


module.exports = Router;
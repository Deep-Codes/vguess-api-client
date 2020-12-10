const express = require('express');
const Router = express.Router();

const jsData = require("../db/jsData.json");
const javaData = require("../db/javaData.json");

Router.get('/:id' , (req,res) => {
  if(req.params.id === 'javascript'){
    res.send(jsData['lang']);
  }else if(req.params.id === 'java'){
    res.send(javaData['lang']);
  }
});


module.exports = Router;

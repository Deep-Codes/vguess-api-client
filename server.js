const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const LanguageRoute = require('./routes/LanguageRoute')


const app  = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

app.use(cors());
app.use('/api', LanguageRoute);

app.listen(7000 , () => console.log('Server Running at PORT: 7000'))
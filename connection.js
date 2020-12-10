const mysql = require('mysql');

const mysqlConnection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'password',
  database : 'vguess',
});

mysqlConnection.connect((err) => {

  if(!err)
      console.log('Database is connected!');
  else
      console.log('Database not connected! : '+ JSON.stringify(err, undefined,2));
  });

module.exports = mysqlConnection;
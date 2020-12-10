import Axios from 'axios';
import React, { useState } from 'react';

import './App.css';
import logo from './assets/logo.png';

function App() {
  const [select, setSelect] = useState<String>('java');
  const [data, setData] = useState([]);

  const showData = () => {
    console.log('req send');
    Axios.get(`http://localhost:7000/api/${select}`).then((res) => {
      setData(res['data']);
    });
    console.log(data);
  };
  return (
    <div className='App'>
      <img src={logo} alt='Logo Vguess' />

      <select name='languages' onChange={(e) => setSelect(e.target.value)}>
        <option value='java'>Java</option>
        <option value='javascript'>javascript</option>
        <option value='python'>Python</option>
      </select>

      {data === [] ? (
        <div>Loading Data ... </div>
      ) : (
        <div>
          <h1>{select.toUpperCase()} Questions</h1>
          {data.map((dt: any) => (
            <div className='cell'>
              {dt.word}
              {dt.hint}
            </div>
          ))}
        </div>
      )}

      <button onClick={showData}>Show</button>
    </div>
  );
}

export default App;

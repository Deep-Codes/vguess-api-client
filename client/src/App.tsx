import Axios from 'axios';
import React, { useState } from 'react';

import './App.css';
import logo from './assets/logo.png';

interface resQuestion {
  word: string;
  hint: string;
}

function App() {
  const [select, setSelect] = useState<String>('java');
  const [data, setData] = useState<null | Array<resQuestion>>(null);
  const [word, setWord] = useState<string>('');
  const [hint, setHint] = useState<string>('');

  const showData = () => {
    console.log('req send');
    Axios.get(`http://localhost:7000/api/${select}`).then((res) => {
      setData(res['data']);
    });
    console.log(data);
  };

  const addQuestion = () => {
    if (hint && word !== '') {
      Axios.post(`http://localhost:7000/api/${select}`, {
        word: word,
        hint: hint,
      }).then(() => {});
    }
  };
  return (
    <div className='App'>
      <img src={logo} alt='Logo Vguess' className='logo' />

      <select
        name='languages'
        onChange={(e: React.ChangeEvent<HTMLSelectElement>) =>
          setSelect(e.target.value)
        }
      >
        <option value='java'>Java</option>
        <option value='javascript'>JavaScript</option>
        <option value='python'>Python</option>
        <option value='html'>HTML</option>
        <option value='css'>CSS</option>
      </select>
      <button className='btn' onClick={showData}>
        Show
      </button>

      <input
        className='input'
        type='text'
        value={word}
        onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
          setWord(e.target.value)
        }
        placeholder='Enter Word'
      />
      <input
        className='input'
        type='text'
        value={hint}
        onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
          setHint(e.target.value)
        }
        placeholder='Enter Hint'
      />

      <button className='btn' onClick={addQuestion}>
        Add Question
      </button>

      {data && (
        <>
          <div className='question-wrapper'>
            <h1>{select.toUpperCase()} Questions</h1>
            {data.map((dt: resQuestion) => (
              <div className='cell'>
                <div className='word-cell'>{dt.word}</div>
                <div className='word-hint'>{dt.hint}</div>
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  );
}

export default App;

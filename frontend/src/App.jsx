import { useState } from 'react'
import './App.css'
import GetProductById from './assets/GetProductById';
import AddReview from './assets/AddReview'

function App() {
  const [id, setId] = useState('');
  const [tempId, setTempId] = useState('');
  const [displayForm, setDisplayForm] = useState(false);

  const handleFormSubmit = () => {
    setId(tempId - 1);
    setDisplayForm(true);
  }

  return (
    <>
      <div>
        <form className='form1' onSubmit={(e) => {
          e.preventDefault();
          handleFormSubmit();
        }}>
          <label>
            product id: <input type='text' placeholder='id' value={tempId} onChange={(e) => { setTempId(e.target.value) }} />
          </label>
          <button type='submit'>Submit</button>
        </form>
      </div>

      {displayForm && <GetProductById id={id} />}

      <AddReview />
    </>
  )
}

export default App

import { useState } from 'react'
import './App.css'
import GetProductById from './assets/GetProductById'
import GetProductByName from './assets/GetProductByName'
import AddReview from './assets/AddReview'


function App() {
  const [name, setName] = useState('');
  const [tempName, setTempName] = useState('');
  const [displayForm, setDisplayForm] = useState(false);

  const handleFormSubmit = () => {
    setName(tempName);
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
            product name: <input type='text' placeholder='product name' value={tempName} onChange={(e) => { setTempName(e.target.value) }} />
          </label>
          <button type='submit'>Submit</button>
        </form>
      </div>

      {displayForm && <GetProductByName productName={name} />}

      <AddReview />
    </>
  )
}

export default App

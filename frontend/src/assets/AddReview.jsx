import PostReview from "./PostReview";
import { useState } from "react";

function AddReview() {
    const [productName, setProductName] = useState('');
    const [userName, setUserName] = useState('')
    const [score, setScore] = useState('');
    const [description, setDescription] = useState('');
    const [displayForm, setDisplayForm] = useState(false)
    const [depend, setDepend] = useState(0);

    const [hover, setHover] = useState(0);


    const handleFormSubmit = () => {
        setDisplayForm(true);
        setDepend(depend + 1);
    }

    return (
        <>
            <form onSubmit={(e) => {
                e.preventDefault();
                handleFormSubmit();

            }}>
                <label>
                    product name: <input type='text' placeholder='product name' value={productName} onChange={(e) => { setProductName(e.target.value) }} />
                </label><br />
                <label>
                    reviewer name: <input type='text' placeholder='reviewer name' value={userName} onChange={(e) => { setUserName(e.target.value) }} />
                </label><br />

                <label>
                    score:
                </label><br />
                {/*[...Array(5)].map((_, i) => i + 1).map((number) =>
                    <div key={number}>
                        <label>{number}: <input type="radio" name="score" onChange={() => setScore(number)} /></label><br />
                    </div>
                )*/}
                    <div className="star-rating">
                      {[...Array(5)].map((star, index) => {
                        index += 1;
                        return (
                        <star>
                          <button
                            type="button"
                            key={index}
                            className={index <= (hover || score) ? "on" : "off"}
                            onClick={() => setScore(index)}
                            onMouseEnter={() => setHover(index)}
                            onMouseLeave={() => setHover(0)}
                          >
                            <span className="star">&#9733;</span>
                          </button>
                        </star>
                        );
                      })}
                    </div>
                <br />

                <label>
                    description: <textarea type='text' placeholder='description' value={description} onChange={(e) => { setDescription(e.target.value) }} />
                </label><br />
                <button type='submit'>Submit</button>
            </form>

            {displayForm && <PostReview productName={productName} userName={userName} score={score} description={description} depend={depend}/>}
        </>
    )
}

export default AddReview
import axios from "axios";
import { useState, useEffect } from "react"

const StarRating = (score) => {
    const fullStars = Math.floor(score);
    const starArr = [];

    for (let i = 1; i <= fullStars; i++) {
        starArr.push(1);
    }
    if (score < 5) {
        starArr.push(score-fullStars);
        const emptyStars = 5 - starArr.length;
        for (let i = 1; i <= emptyStars; i++) {
            starArr.push(0)
        }
    }

    return (
        starArr.map((val, i) => {
            return <star key={i}
            style={{background: `linear-gradient(90deg, #FF9529
            ${val * 100}%, #BBBAC0 ${val * 100}%)`}}>★</star>
        })
    );
};

function GetProductByName(props) {
    const [item, setItem] = useState();

    useEffect(() => {
        const fetchData = async () => {

            let productId;
            const getProducts = await axios.get("http://localhost:8080/api/v1/products/findall");
            getProducts.data.forEach((product) =>
                product.name.toUpperCase() == props.productName.toUpperCase() ? setItem(product) : '');
        };

        fetchData();
    }, [props.productName])

    if (item !== undefined) {
        const reviews = item.reviews.map(review => {
            <p>
                {review.description}<br/>
            </p>
        })

        return (
            <>
                <p>
                    {item.name} - €{item.price}<br/>
                    {StarRating(item.score)}<br/><br/>
                    {item.reviews.map(review => {
                        return (
                            <review>
                                {review.user}<br/>
                                {StarRating(review.score)} - {review.description}<br/><br/>
                            </review>
                        );
                    })}
                </p>
            </>
        )
    } else {
        return (
            <>
                <p>Not found!</p>
            </>
        )
    }
}

export default GetProductByName
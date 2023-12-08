import axios from "axios";
import { useState, useEffect } from "react"

function PostReview(props) {
    const [res, setRes] = useState('');
    const [style, setStyle] = useState('');

    useEffect(() => {
        const fetchData = async () => {

            let productId;
            const getProducts = await axios.get("http://localhost:8080/api/v1/products/findall");
            getProducts.data.forEach((product) => product.name.toUpperCase() == props.productName.toUpperCase() ? productId = product.id : '');

            let userId;
            const getUsers = await axios.get("http://localhost:8080/api/v1/users/findall");
            getUsers.data.forEach((user) => user.username.toUpperCase() == props.userName.toUpperCase() ? userId = user.id : '');

            if (productId === undefined) {
                setRes(`product "${props.productName}" bestaat niet`);
                setStyle('red');
            }
            else if (userId === undefined) {
                setRes(`user "${props.userName}" bestaat niet`);
                setStyle('red');
            } else {
                const result = await axios.post(`http://localhost:8080/api/v1/reviews/new/${userId}/${productId}`, {
                    score: props.score,
                    description: props.description
                });

                setRes(`status code: ${result.status}`)
                setStyle('green');
            }
        }
        fetchData();
    }, [props.depend])

    return (
        <p className={style}>{res}</p>
    )
}

export default PostReview
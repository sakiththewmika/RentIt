import React, { useState } from 'react';
import axios from 'axios';

const ProductForm = () => {
    const [name, setName] = useState('');
    const [price, setPrice] = useState('');

    const handleSubmit = (e) => {
        e.preventDefault();
        axios.post('http://localhost:8080/api/products', { name, price })
            .then(() => {
                alert('Product added successfully!');
                setName('');
                setPrice('');
            })
            .catch(error => console.error('Error adding product:', error));
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Add Product</h2>
            <input
                type="text"
                placeholder="Product Name"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
            />
            <input
                type="number"
                placeholder="Price"
                value={price}
                onChange={(e) => setPrice(e.target.value)}
                required
            />
            <button type="submit">Add Product</button>
        </form>
    );
};

export default ProductForm;

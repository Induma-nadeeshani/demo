// src/components/AddBook.js

import React, { useState } from 'react';
import axios from 'axios';

function BookForm() {
  const [book, setBook] = useState({
    isbn: '',
    name: '',
    author: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook({ ...book, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    
    axios.post('http://localhost:8080/api/v1/book', book)
      .then((response) => {
        console.log('Book added successfully');

         // Clear the form
        setBook({
          isbn: '',
          name: '',
          author: '',
        });
      })
      .catch((error) => {
        console.error('Error adding book:', error);
      });
  };

  return (
    <div>
      <h2>Add a New Book</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>ISBN:</label>
          <input type="text" name="isbn" value={book.isbn} onChange={handleChange} />
        </div>
        <div>
          <label>Title:</label>
          <input type="text" name="name" value={book.name} onChange={handleChange} />
        </div>
        <div>
          <label>Author:</label>
          <input type="text" name="author" value={book.author} onChange={handleChange} />
        </div>
        <button type="submit">Add Book</button>
      </form>
    </div>
  );
}

export default BookForm;

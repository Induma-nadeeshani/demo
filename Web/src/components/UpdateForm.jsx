import React, { useState, useEffect } from 'react';
import axios from 'axios';

function UpdateForm({ match, history }) {
  const [book, setBook] = useState({

  });

  console.log('Book Update 222222');

  useEffect(() => {
    const getbooks = async () => {
      try {
        const queryParams = new URLSearchParams(window.location.search);

        const bookid = queryParams.get('bookid');
        const res = await fetch(`http://localhost:8080/api/v1/book/${bookid}`);
        const json = await res.json();

        console.log(json.data)
        setBook(json.data);
      } catch { }
    };
    getbooks();
  }, [setBook]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setBook({ ...book, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    axios.put(`http://localhost:8080/api/v1/book/${match.params.id}`, book)
      .then((response) => {
        console.log('Book updated successfully');
        history.push(`/book/${match.params.id}`); 
      })
      .catch((error) => {
        console.error('Error updating book:', error);
      });
  };

  return (
    <div>
      <h2>Edit Book</h2>
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
        <button type="submit">Update Book</button>
      </form>
    </div>
  );
}

export default UpdateForm ;

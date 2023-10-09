import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function AllBooks() {
  const [books, setBooks] = useState([]);
  const [message, setMessage] = useState('');

  useEffect(() => {

    axios.get('http://localhost:8080/api/v1/book')
      .then((response) => {

        const responseData = response.data;

        if (responseData && responseData.data) {
          setBooks(responseData.data);
          setMessage(responseData.message);
        } else {
          console.error('Invalid response format received from the API');
        }
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  return (
    <div>
      <h2>Book List</h2>
      <p>{message}</p>
      <ul>
        {books.map((book) => (
          <li key={book.id}>
            <strong>Title:</strong> {book.name}, <strong>ISBN:</strong> {book.isbn}, <strong>Author:</strong> {book.author}
            {' '}
            {/* <Link to={"/newDoctor/?user=" + userToken + "&uName=" + userName} */}
            <Link to={`/edit/?bookid=${book.id}`}>Edit</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default AllBooks;

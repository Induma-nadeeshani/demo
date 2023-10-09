
import React, { useEffect, useState } from 'react';
import axios from 'axios';

function BookDetails({ match }) {
  const [book, setBook] = useState({});
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get(`http://localhost:8080/api/v1/book/${match.params.id}`)
      .then((response) => {
        setBook(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error(error);
        setLoading(false);
      });
  }, [match.params.id]);

  if (loading) {
    return <p>Loading...</p>;
  }

  return (
    <div>
      <h2>Book Details</h2>
      <p><strong>Title:</strong> {book.title}</p>
      <p><strong>Author:</strong> {book.author}</p>
      <p><strong>ISBN:</strong> {book.isbn}</p>
    </div>
  );
}

export default BookDetails;

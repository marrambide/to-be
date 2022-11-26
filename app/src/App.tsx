import React, { useEffect, useState } from 'react';
import './App.css';
import {Book} from './utils/mongodb-types'

const App = () => {
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(false);

    useEffect(() => {
        setLoading(true)

        fetch('/api/books')
        .then(response => response.json())
        .then(data => {
            setBooks(data);
            setLoading(false);
        })
    }, [])

    if (loading) {
        return <p>Loading...</p>
    }

    return (
       <div>
       {books.map((book:Book) =>
        <div key={book._id}>{book.name}</div>
       )}
       </div>
      );
}

export default App

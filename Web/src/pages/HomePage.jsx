import React from "react";
import { Link } from 'react-router-dom';

const HomePage = () => {
    return(
        <div className="home-page">
              <Link to="/list">
                <button className="btn btn-primary btn-lg mr-5 mt-8">
                View All Books
                </button>
              </Link>
              {/* <Link to="/add">
                <button className="btnPrimaryLarge ml-3 mt-8">
                Add Book
                </button>
              </Link> */}
        </div>
    );
}

export default HomePage;

import './App.css'
import { BrowserRouter as Switch, Route, Routes } from 'react-router-dom';
import AddBook from './pages/AddBook'
import BookList from './pages/BookList.jsx';
import HomePage from './pages/HomePage.jsx';
import EditBook from './pages/EditBook.jsx';

function App() {

  return (
        <div className='app'>

        <Switch>
          <Routes>
          <Route exact path="/" element={<HomePage/>}/>
          <Route path="/list" element={<BookList />} ></Route>
          <Route path="/add" element={<AddBook />} ></Route>
          <Route path="/edit/" element={<EditBook />} > </Route>

          </Routes>
        </Switch>
        </div>
  )
}

export default App;

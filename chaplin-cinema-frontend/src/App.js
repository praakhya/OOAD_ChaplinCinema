import logo from './logo.png';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Layout from './Layout/Layout';
import Home from './Home/Home';
import Login from './Login/Login';
import NoPage from './NoPage/NoPage';
import Signup from './Signup/Signup';
import MoviePage from './Movie/MoviePage';
import { createContext, useState } from "react";
import MoviePreview from './Movie/MoviePreview';
import Profile from './Profile/Profile';

function App() {

  
  return (
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Layout />}>
              <Route index element={<Home />} />
              <Route path="login" element={<Login />} />
              <Route path='signup' element={<Signup />} />
              <Route path='movies' element={<MoviePage />} />
              <Route path='movie/:id' element={<MoviePreview />} />
              <Route path='profile' element={<Profile />} />
              <Route path="*" element={<NoPage />} />
            </Route>
          </Routes>
        </BrowserRouter>
  );
}

export default App;

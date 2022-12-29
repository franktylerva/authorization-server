import React from 'react';
import './App.css';
import Home from './components/Home';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ClientList from "./components/ClientList";
import UserList from "./components/UserList";
import ClientPage from "./components/ClientPage";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<Home/>}/>
                <Route path="/users" element={<UserList/>}/>
                <Route path="/clients" element={<ClientList/>}/>
                <Route path="/client" element={<ClientPage/>}/>
            </Routes>
        </Router>
    )
}

export default App;
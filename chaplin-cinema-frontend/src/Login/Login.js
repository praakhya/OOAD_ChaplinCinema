import axios from "axios"
import { baseUrl } from "../paths";

import { Card, Button, InputGroup, Form, NavLink } from "react-bootstrap"
import { useContext, useState } from "react";
import { useNavigate, useOutletContext } from "react-router-dom";





export default function Login() {
    
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const context = useOutletContext()
    const navigate = useNavigate()

    function loginRequest(username,password) {
        
        axios.post(baseUrl + '/auth',{
            username:username,
            password:password
        },{headers:{ 'Content-Type': 'application/json' }})
        .then((response) => {
            console.log(response.data)
            localStorage.setItem("user", JSON.stringify(response.data))
            context.setUser(response.data)
            navigate("/movies")
        })
        .catch((err) => {
            console.log("err:",err);
            })
             
    }

    return (
        <div className="d-flex justify-content-center w-100 h-100 m-2">
            <Card data-bs-theme="dark" className="w-50">
            <Card.Header>Login</Card.Header>
            <Card.Body>
                <Card.Title>Login to Chaplin Cinema</Card.Title>
                <InputGroup className="mb-3">
                    <InputGroup.Text id="basic-addon1">Username</InputGroup.Text>
                    <Form.Control
                        placeholder="Enter your Username"
                        aria-label="Username"
                        aria-describedby="basic-addon1"
                        value={username}
                        onInput={e => setUsername(e.target.value)}
                    />
                </InputGroup>
                <InputGroup className="mb-3">
                    <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                    <Form.Control
                        placeholder="Enter your Password"
                        aria-label="Username"
                        aria-describedby="basic-addon1"
                        type="password"
                        value={password}
                        onInput={e => setPassword(e.target.value)}
                    />
                </InputGroup>
<Card.Footer className="d-flex flex-row justify-content-between">
<Button variant="primary" onClick={()=>{
    loginRequest(username,password)
    setUsername("")
    setPassword("")
    }}>Login</Button>
                <NavLink href="/signup">Signup</NavLink>
</Card.Footer>
                
            </Card.Body>
        </Card>
        </div>
        
    )
}
import axios from "axios";
import { createRef, useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import { useOutletContext } from "react-router-dom";
import { baseUrl } from "../paths";
function CustomerEdit() {
    const context = useOutletContext();
    const userRef = {
        password: createRef(),
        firstname: createRef(),
        lastname: createRef()
    }
    const [responseAlert, setResponseAlert] = useState({
        message: "",
        status: ""
    })
    function editRequest() {
        axios.put(baseUrl + '/customers', {
            id: context.user.id,
            username: context.user.username,
            password: userRef.password.current.value,
            firstName: userRef.firstname.current.value,
            lastName: userRef.lastname.current.value
        },
        {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + context.user.authToken.authToken
            }
        })
            .then((response) => {
                console.log("response:",response.data.content)
                context.setUser(response.data.content)
                localStorage.setItem("user",JSON.stringify(response.data.content))
                setResponseAlert({
                    message: "User was modified successfully",
                    status: 200
                })
            })
            .catch((err) => {
                console.log("err:", err);
                setResponseAlert({
                    message: "User could not be modified",
                    status: err.message
                })
            })
    }
    return (
        <>
        <Form>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" disabled value={context.user.username}/>
                <Form.Text className="text-muted">
                    Username cannot be changed
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" ref={userRef.password}/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicFirstName">
                <Form.Label>First Name</Form.Label>
                <Form.Control type="text" placeholder="First Name" ref={userRef.firstname}/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicLastName">
                <Form.Label>Last Name</Form.Label>
                <Form.Control type="text" placeholder="Last Name" ref={userRef.lastname}/>
            </Form.Group>
            <Button variant="primary" type="submit">
                Submit
            </Button>
        </Form>
        {responseAlert.message!="" ? <Alert>{responseAlert.message}</Alert>:<></>}
        </>
    )
}
export default CustomerEdit;
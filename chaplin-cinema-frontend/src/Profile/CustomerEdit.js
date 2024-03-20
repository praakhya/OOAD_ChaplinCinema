import axios from "axios";
import { createRef, useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import { useOutletContext } from "react-router-dom";
import { baseUrl } from "../paths";
function CustomerEdit() {
    const context = useOutletContext();
    const [password,setPassword] = useState(context.user.password)
    const [firstName, setFirstName] = useState(context.user.firstName)
    const [lastName, setLastName] = useState(context.user.lastName)
    const [responseAlert, setResponseAlert] = useState({
        message: "",
        status: ""
    })
    function editRequest(event) {
        event.preventDefault()
        axios.put(baseUrl + '/customers', {
            id: context.user.id,
            username: context.user.username,
            password: password,
            firstName: firstName,
            lastName: lastName
        },
        {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + context.user.authToken.authToken
            }
        })
            .then((response) => {
                console.log("response:",response.data)
                if (response.data == undefined) {
                    return
                }
                var modifiedUser = context.user
                modifiedUser.firstName = response.data.firstName
                modifiedUser.lastName = response.data.lastName
                modifiedUser.password = response.data.password
                context.setUser(modifiedUser)
                localStorage.setItem("user",JSON.stringify(modifiedUser))
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
        <Form onSubmit={editRequest}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>Username</Form.Label>
                <Form.Control type="text" disabled value={context.user.username}/>
                <Form.Text className="text-muted">
                    Username cannot be changed
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Password</Form.Label>
                <Form.Control type="password" placeholder="Password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicFirstName">
                <Form.Label>First Name</Form.Label>
                <Form.Control type="text" placeholder="First Name" value={firstName} onChange={(e)=>setFirstName(e.target.value)}/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicLastName">
                <Form.Label>Last Name</Form.Label>
                <Form.Control type="text" placeholder="Last Name" value={lastName} onChange={(e)=>setLastName(e.target.value)}/>
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
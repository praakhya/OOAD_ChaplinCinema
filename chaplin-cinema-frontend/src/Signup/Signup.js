import { Card, Button, InputGroup, Form, NavLink, Tabs, Tab, Alert } from "react-bootstrap"
import { useState, createRef } from "react";
import axios from "axios";
import { baseUrl } from "../paths";

const customerRef = { username: createRef(), password: createRef(), firstname: createRef(), lastname: createRef() }
const adminRef = { username: createRef(), password: createRef() }
const theaterAdminRef = { username: createRef(), password: createRef(), theatername: createRef(), address: createRef() }

export default function Signup() {
    const [responseStatus, setResponseStatus] = useState({ message: "", code: "" })

    const [key, setKey] = useState('customer');
    function adminSignup() {
        console.log(adminRef)
        axios.post(baseUrl + '/admins', {
            username: adminRef.username.current.value,
            password: adminRef.password.current.value
        }, { headers: { 'Content-Type': 'application/json' } })
            .then((response) => {
                console.log("On signup: ", response)
                setResponseStatus({
                    message: "Signup was successful. Login with your credentials",
                    code: response.status.code
                })
            })
            .catch((err) => {
                console.log("err:", err);
            })
    }
    function customerSignup() {
        console.log(customerRef)
        axios.post(baseUrl + '/customers', {
            username: customerRef.username.current.value,
            password: customerRef.password.current.value,
            firstName: customerRef.firstname.current.value,
            lastName: customerRef.lastname.current.value,
        }, { headers: { 'Content-Type': 'application/json' } })
            .then((response) => {
                console.log("On signup: ", response)
                setResponseStatus({
                    message: "Signup was successful. Login with your credentials",
                    code: response.status.code
                })
            })
            .catch((err) => {
                console.log("err:", err);
            })
    }
    return (
        <div className="d-flex justify-content-center w-100 h-100 m-2">
            <Card data-bs-theme="light" className="w-50">
                <Card.Header>Signup</Card.Header>
                <Card.Body>
                    <Card.Title>Signup with Chaplin Cinema</Card.Title>
                    <Card.Subtitle>Choose type of user</Card.Subtitle>
                    <Tabs
                        id="controlled-tab-example"
                        activeKey={key}
                        onSelect={(k) => setKey(k)}
                        className="mb-3"
                    >
                        <Tab eventKey="customer" title="Customer">
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Username</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Username"
                                    ref={customerRef.username}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Password"
                                    type="password"
                                    ref={customerRef.password}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">First Name</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your First Name"
                                    ref={customerRef.firstname}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Last Name</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Last Name"
                                    ref={customerRef.lastname}
                                />
                            </InputGroup>
                            <Card.Footer className="d-flex flex-row justify-content-between">
                                <Button variant="secondary" onClick={customerSignup}>Signup</Button>
                                <NavLink href="/login">Login</NavLink>
                            </Card.Footer>
                        </Tab>
                        <Tab eventKey="admin" title="Admin">
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Username</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Username"
                                    ref={adminRef.username}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Password"
                                    type="password"
                                    ref={adminRef.password}
                                />
                            </InputGroup>
                            <Card.Footer className="d-flex flex-row justify-content-between">
                                <Button variant="secondary" onClick={adminSignup}>Signup</Button>
                                <NavLink href="/login">Login</NavLink>
                            </Card.Footer>
                        </Tab>
                        <Tab eventKey="theater" title="Theater">
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Username</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Username"
                                    ref={theaterAdminRef.username}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Password"
                                    type="password"
                                    ref={theaterAdminRef.password}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Theater Name</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Theater Name"
                                    ref={theaterAdminRef.theatername}
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Address</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Address"
                                    type="address"
                                    ref={theaterAdminRef.address}
                                />
                            </InputGroup>
                            <Card.Footer className="d-flex flex-row justify-content-between">
                                <Button variant="secondary">Signup</Button>
                                <NavLink href="/login">Login</NavLink>
                            </Card.Footer>
                        </Tab>
                    </Tabs>

                    {
                        responseStatus.status == 200 ? <Alert variant="success">{responseStatus.message}</Alert>
                            : responseStatus.status != "" ? <Alert variant="failure">{responseStatus.message}</Alert>
                                : <></>
                    }

                </Card.Body>
            </Card>
        </div>

    )
}
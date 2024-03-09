import { Card, Button, InputGroup, Form, NavLink, Tabs, Tab } from "react-bootstrap"
import { useState } from "react";

export default function Signup() {
    const [key, setKey] = useState('customer');
    return (
        <div className="d-flex justify-content-center w-100 h-100 m-2">
            <Card data-bs-theme="dark" className="w-50">
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
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Password"
                                    type="password"
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">First Name</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your First Name"
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Last Name</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Last Name"
                                />
                            </InputGroup>
                            <Card.Footer className="d-flex flex-row justify-content-between">
                                <Button variant="secondary">Signup</Button>
                                <NavLink href="/login">Login</NavLink>
                            </Card.Footer>
                        </Tab>
                        <Tab eventKey="admin" title="Admin">
                        <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Username</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Username"
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Password"
                                    type="password"
                                />
                            </InputGroup>
                            <Card.Footer className="d-flex flex-row justify-content-between">
                                <Button variant="secondary">Signup</Button>
                                <NavLink href="/login">Login</NavLink>
                            </Card.Footer>
                        </Tab>
                        <Tab eventKey="theater" title="Theater">
                        <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Username</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Username"
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Password"
                                    type="password"
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Theater Name</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Theater Name"
                                />
                            </InputGroup>
                            <InputGroup className="mb-3">
                                <InputGroup.Text id="basic-addon1">Address</InputGroup.Text>
                                <Form.Control
                                    placeholder="Enter your Address"
                                    type="address"
                                />
                            </InputGroup>
                            <Card.Footer className="d-flex flex-row justify-content-between">
                                <Button variant="secondary">Signup</Button>
                                <NavLink href="/login">Login</NavLink>
                            </Card.Footer>
                        </Tab>
                    </Tabs>



                </Card.Body>
            </Card>
        </div>

    )
}
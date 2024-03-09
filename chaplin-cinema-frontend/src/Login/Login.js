import { Card, Button, InputGroup, Form, NavLink } from "react-bootstrap"
export default function Login() {
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
                    />
                </InputGroup>
                <InputGroup className="mb-3">
                    <InputGroup.Text id="basic-addon1">Password</InputGroup.Text>
                    <Form.Control
                        placeholder="Enter your Password"
                        aria-label="Username"
                        aria-describedby="basic-addon1"
                        type="password"
                    />
                </InputGroup>
<Card.Footer className="d-flex flex-row justify-content-between">
<Button variant="primary">Login</Button>
                <NavLink href="/signup">Signup</NavLink>
</Card.Footer>
                
            </Card.Body>
        </Card>
        </div>
        
    )
}
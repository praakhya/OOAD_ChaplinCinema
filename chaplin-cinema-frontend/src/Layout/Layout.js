import { Link, Outlet } from "react-router-dom"
import { Navbar, Container, Nav, NavDropdown } from "react-bootstrap"
export default function Layout() {
    return (
        <>
            <Navbar bg="dark" data-bs-theme="dark">
                <Container>
                    <Navbar.Brand href="/">Chaplin Cinema</Navbar.Brand>
                    <Nav className="justify-content-end">
                        <Nav.Link href="/">Home</Nav.Link>
                        <Nav.Link href="/login">Login</Nav.Link>
                    </Nav>
                </Container>
            </Navbar>
            

            <Outlet />
        </>
    )
}
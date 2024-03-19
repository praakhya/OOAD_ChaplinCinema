import { Link, Outlet } from "react-router-dom"
import { Navbar, Container, Nav, NavDropdown, Image } from "react-bootstrap"
import { useState } from "react"
import { useNavigate } from "react-router-dom"

export default function Layout() {

    var storedUser = JSON.parse(localStorage.getItem("user"));
    console.log("App.js:", storedUser)
    const [user, setUser] = useState(storedUser);
    const [movies, setMovies] = useState([]);
  
    const navigate = useNavigate()
    function isLoggedIn() {
        if (user == null) {
            return false
        }
        return true
    }
    function logout() {
        localStorage.removeItem("user")
        setUser(null)
    }
    
    return (
        <>
            <Navbar bg="light" data-bs-theme="light">
                <Container>
                    <Navbar.Brand>Chaplin Cinema</Navbar.Brand>
                    <Nav className="justify-content-end">
                        <Nav.Link href="/">Home</Nav.Link>
                        {
                            isLoggedIn() ? <Nav.Link onClick={logout}>Logout</Nav.Link> : <Nav.Link href="/login">Login</Nav.Link>
                        }
                        {
                            isLoggedIn() && user.profilePictureURL != null ? <Image src={user.profilePictureURL} roundedCircle /> : <></>
                        }

                    </Nav>

                </Container>
            </Navbar>


            <Outlet context={{user, setUser, movies, setMovies}}/>
        </>
    )
}
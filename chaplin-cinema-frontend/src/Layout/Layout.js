import { Link, Outlet } from "react-router-dom"
import { Navbar, Container, Nav, NavDropdown, Image } from "react-bootstrap"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { baseUrl } from "../paths";
import { Dropdown } from "react-bootstrap";
export default function Layout() {

    localStorage.setItem("genres",JSON.stringify([]))
    var storedUser = JSON.parse(localStorage.getItem("user"));
    var storedGenres = JSON.parse(localStorage.getItem("genres"))
    console.log("App.js:", storedUser)
    const [user, setUser] = useState(storedUser);
    const [movies, setMovies] = useState([]);
    const [genres, setGenres] = useState(storedGenres);

    const navigate = useNavigate()
    function isLoggedIn() {
        if (user == null) {
            return false
        }
        return true
    }
    function logout() {
        localStorage.removeItem("user")
        localStorage.removeItem("genres")
        setUser(null)
        setGenres([])
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
                            isLoggedIn() ?
                                <Dropdown className="d-inline mx-2" drop="start">
                                    <Dropdown.Toggle id="dropdown-autoclose-true" variant="light">
                                        {user.profilePictureURL != null ?
                                        <Image src={user.profilePictureURL} roundedCircle />
                                        :
                                        <Image roundedCircle data-bs-theme="dark" />}                                    
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Header>Welcome {user.username}</Dropdown.Header>
                                        <Dropdown.Item onClick={()=>navigate("profile")}>Profile</Dropdown.Item>
                                    </Dropdown.Menu>
                                </Dropdown>

                                : <></>
                        }

                    </Nav>

                </Container>
            </Navbar>


            <Outlet context={{ user, setUser, movies, setMovies, genres, setGenres }} />
        </>
    )
}
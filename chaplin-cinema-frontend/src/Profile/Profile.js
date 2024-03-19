import { Card, Button, Badge, Alert, ButtonToolbar } from "react-bootstrap"
import { user } from "../testConstants"
import { useNavigate, useOutletContext } from "react-router-dom"
import Modal from 'react-bootstrap/Modal';
import { useState } from "react";
import { Form } from "react-bootstrap";
import CustomerEdit from "./CustomerEdit";
import AdminEdit from "./AdminEdit";
import TheaterEdit from "./TheaterEdit";
export default function Profile() {
    const context = useOutletContext()
    const navigate = useNavigate()
    const [show, setShow] = useState(false);
    const closeEditDialog = () => setShow(false);
    const showEditDialog = () => setShow(true);
    return (
        <>
            <ButtonToolbar>
                <Button className="m-2" variant="secondary" onClick={() => navigate("/movies")}>Back</Button>
            </ButtonToolbar>
            <div className="p-5 d-flex flex-row gap-3">
                <Card style={{ width: '18rem' }} data-bs-theme="light">
                    <Card.Img variant="top" />
                    <Card.Body>
                        <Card.Title>{user.username}</Card.Title>
                        <Card.Subtitle><Badge bg="secondary">
                            {user.grantedAuthorities[0]}
                        </Badge></Card.Subtitle>
                        <Card.Text>
                            Some quick example text to build on the card title and make up the
                            bulk of the card's content.
                        </Card.Text>
                        <Button variant="light" onClick={showEditDialog}>Edit</Button>
                    </Card.Body>
                </Card>
                <div className="w-100">
                    <div>
                        <h1>Booked Movies</h1>
                        {user.Bookings.length != 0 ?
                            <div>
                                {user.Bookings.map(m => {
                                    return <p>{m.title}</p>
                                })}
                            </div>

                            :
                            <Alert variant="secondary">No booking made yet!</Alert>
                        }


                    </div>
                    <div>
                        <h1>Watched Movies</h1>
                        <div>
                            {user.Bookings.length != 0 ?
                                <div>
                                    {user.MoviesWatched.map(m => {
                                        return <p>{m.title}</p>
                                    })}
                                </div>

                                :
                                <Alert variant="secondary">No movies watched yet!</Alert>
                            }
                        </div>
                    </div>

                </div>
            </div>

            <Modal show={show} onHide={closeEditDialog}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit Profile</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {
                    context.user.grantedAuthorities[0]=="CUSTOMER" 
                    ? <CustomerEdit/>
                    : context.user.grantedAuthorities[0]=="ADMIN"
                    ? <AdminEdit/>
                    : context.user.grantedAuthorities[0]=="THEATER_ADMIN"
                    ? <TheaterEdit/>
                    : <></>}
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={closeEditDialog}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    )
}
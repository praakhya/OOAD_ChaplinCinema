import { Card, Button, Badge, Alert, ButtonToolbar } from "react-bootstrap"
import { useNavigate, useOutletContext } from "react-router-dom"
import Modal from 'react-bootstrap/Modal';
import { useState } from "react";
import { Form } from "react-bootstrap";
import CustomerEdit from "./CustomerEdit";
import AdminEdit from "./AdminEdit";
import TheaterEdit from "./TheaterEdit";
import CustomerBody from "./CustomerBody";
import AdminBody from "./AdminBody";
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
                        <Card.Title>@{context.user.username}</Card.Title>
                        <hr/>
                        <Card.Subtitle>
                            <Card.Text>{context.user.firstName} {context.user.lastName}</Card.Text>
                            <Badge bg="secondary">
                            {context.user.grantedAuthorities[0]}
                        </Badge></Card.Subtitle>
                        <Card.Text>
                            <b>Username:</b> {context.user.username}
                            <br/>
                            <b>ID:</b> {context.user.id}
                        </Card.Text>
                        <Button variant="light" onClick={showEditDialog}>Edit</Button>
                    </Card.Body>
                </Card>
                {context.user.grantedAuthorities[0]=="CUSTOMER" ? <CustomerBody bookings={context.user.bookings} moviesWatched={context.user.moviesWatched}/> : <></>}
                {context.user.grantedAuthorities[0]=="ADMIN" ? <AdminBody/> : <></>}
            </div>

            <Modal show={show} onHide={closeEditDialog}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit Profile</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {
                        context.user.grantedAuthorities[0] == "CUSTOMER"
                            ? <CustomerEdit />
                            : context.user.grantedAuthorities[0] == "ADMIN"
                                ? <AdminEdit />
                                : context.user.grantedAuthorities[0] == "THEATER_ADMIN"
                                    ? <TheaterEdit />
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
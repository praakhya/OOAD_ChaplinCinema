import { Card, Button, Badge, Alert, ButtonToolbar } from "react-bootstrap"
import { user } from "../testConstants"
import { useNavigate } from "react-router-dom"
export default function Profile() {
    //const user = user
    const navigate = useNavigate()
    return (
        <>
        <ButtonToolbar>
            <Button className="m-2" variant="secondary" onClick={()=>navigate("/movies")}>Back</Button>
        </ButtonToolbar>
        <div className="p-5 d-flex flex-row gap-3">
            <Card style={{ width: '18rem' }} data-bs-theme="dark">
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
                    <Button variant="light">Edit</Button>
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
        </>
    )
}
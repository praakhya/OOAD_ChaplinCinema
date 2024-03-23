import { Button, ButtonToolbar, Card, Alert, InputGroup, Image } from "react-bootstrap"
import { useOutletContext, useParams } from "react-router-dom"
import { languages } from "../testConstants";
import { useNavigate } from "react-router-dom";
import { useContext, useEffect, useState } from "react";
import { url8080 } from "../paths";
import { Form } from "react-bootstrap";
import axios from "axios";
import { baseUrl } from "../paths";
import { Modal } from "react-bootstrap";
export default function MoviePreview() {
    const closeEditDialog = () => setShow(false);
    const showEditDialog = () => setShow(true);
    const [show, setShow] = useState(false);

    const context = useOutletContext()
    const { id } = useParams();
    const [movie, setMovie] = useState({})
    const [posterString, setPosterString] = useState("")
    const [genreString, setGenreString] = useState("")
    const [languageString, setLanguageString] = useState("")
    const [castString, setCastString] = useState("")
    const [dirString, setDirString] = useState("")
    const [writerString, setWriterString] = useState("")
    const [year, setYear] = useState("")
    const [runtime, setRuntime] = useState("")
    const [title, setTitle] = useState("")
    const [overview, setOverview] = useState("")
    const [date, setDate] = useState(null)
    const [responseAlert, setResponseAlert] = useState({
        message: "",
        status: ""
    })
    function movieUpdateRequest(event) {
        event.preventDefault()
        axios.put(baseUrl + '/movies', {
            id: movie.id,
            title: title,
            overview: overview,
            genres: genreString.split(","),
            languages: languageString.split(","),
            cast: castString.split(","),
            directors: dirString.split(","),
            writers: writerString.split(","),
            year: year,
            poster: posterString,
            released: date,
            runtime: runtime
        },
            {
                headers: {
                    'Content-Type': 'application/json',
                    "Authorization": "Bearer " + context.user.authToken.authToken
                }
            })
            .then((response) => {
                console.log("response:", response.data)
                if (response.data == undefined) {
                    return
                }
                setMovie(response.data)
                setResponseAlert({
                    message: "Movie was modified successfully",
                    status: 200
                })
            })
            .catch((err) => {
                console.log("err:", err);
                setResponseAlert({
                    message: "Movie could not be modified",
                    status: err.message
                })
            })
    }
    useEffect(() => {
        getMovieById()
    }, [])
    useEffect(() => {
        if (Object.keys(movie).length > 0) {
            console.log("MOVIE>GENRES ", movie)
            setGenreString(movie.genres.join(","))
            setLanguageString(movie.languages.join(","))
            setCastString(movie.cast.join(","))
            setDirString(movie.directors.join(","))
            setWriterString(movie.writers.join(","))
            setYear(movie.year ? movie.year : "")
            setTitle(movie.title)
            setOverview(movie.overview ? movie.overview : "")
            setPosterString(movie.poster ? movie.poster : "")
            setDate(movie.released ? stringToDateReverse(movie.released) : "")
        }
        console.log("After setting genres:", genreString)
    }, [movie])
    function deleteMovie() {
        axios.delete(baseUrl + "/movies/" + id, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + context.user.authToken.authToken
            }
        })
            .then((response) => {
                console.log("MOVIEPREVIEW delete response:", response.data)
                closeEditDialog()
                navigate("/movies")
            })
            .catch((err) => {
                console.log("err:", err);
            })
    }
    function getMovieById() {
        axios.get(baseUrl + "/movies/" + id, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + context.user.authToken.authToken
            }
        })
            .then((response) => {
                console.log("MOVIEPREVIEW response:", response.data)
                setMovie(response.data)

            })
            .catch((err) => {
                console.log("err:", err);
            })
    }
    const navigate = useNavigate()
    function stringToDate(d) {
        var date;
        if (d != undefined) {
            date = new Date(d)
            return date.getDate() + "-" + date.toLocaleString('default', { month: 'long' }) + "-" + date.getFullYear()
        }
        return ""
    }
    function stringToDateReverse(d) {
        var date;
        if (d != undefined) {
            date = new Date(d)
            return date.getFullYear() + "-" + date.toLocaleString('default', { month: 'long' }) + "-" + date.getDate()
        }
        return ""
    }

    return (
        <div>
            <Card data-bs-theme="light" style={{ borderRadius: 0 }}>
                <ButtonToolbar className="align-items-left flex-row">
                    <Button variant="secondary" className="m-3" onClick={() => { navigate("/movies") }}>Back</Button>
                </ButtonToolbar>
                {movie != null ? <div className='d-flex flex-row p-5'>
                    <Card.Img src={movie.poster ? movie.poster : url8080 + "/images/placeholderPoster.jpg"} style={{ width: "15vw" }} />
                    <Card.Body>
                        <Card.Title className="fs-1">{movie.title}</Card.Title>
                        <Card.Text>
                            <span className="fs-5">Released on: {stringToDate(movie.released)}</span>
                            <br />
                            {/* <div>
                                {
                                    movie.languages.map(lang => <span>{lang}</span>)
                                }
                            </div> */}
                        </Card.Text>
                        <Card.Footer>
                            {movie.overview}
                        </Card.Footer>
                    </Card.Body>
                </div> : <></>}
            </Card>
            {context.user.grantedAuthorities[0] == "CUSTOMER" ? <div className="d-flex flex-column align-items-center m-5">
                <Alert variant="secondary">
                    Booking Selection feature is yet to be added
                </Alert>
            </div> : <></>}
            {
                context.user.grantedAuthorities[0] == "ADMIN" ? <Card className="p-5">
                    <Card.Title className="fs-1">Admin Panel</Card.Title>
                    <Card.Subtitle className="fs-3">Modify Movie Details</Card.Subtitle>
                    <Form onSubmit={movieUpdateRequest}>
                        <Form.Group className="mb-3">
                            <Form.Label>Title</Form.Label>
                            <Form.Control type="text" placeholder="Enter Title" value={title} onChange={(e) => { setTitle(e.target.value) }} />
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Overview</Form.Label>
                            <Form.Control
                                className="mb-3"
                                value={overview}
                                onChange={(e) => { setOverview(e.target.value) }}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Poster URL</Form.Label>
                            <Form.Control
                                key={`inline-checkbox`}
                                className="mb-3"
                                value={posterString}
                                onChange={(e) => { setPosterString(e.target.value) }}>

                            </Form.Control>
                            <Card.Img src={posterString} style={{ width: "15vw" }} alt={posterString} />
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Genres</Form.Label>
                            <Form.Control className="mb-3" type="text" value={genreString} onChange={(e) => setGenreString(e.target.value)}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Runtime</Form.Label>

                            <InputGroup className="mb-3">
                                <Form.Control
                                    placeholder={runtime}
                                />
                                <InputGroup.Text>minutes</InputGroup.Text>
                            </InputGroup>
                        </Form.Group>
                        <Form.Group>
                            <Form.Label>Release Date</Form.Label>

                            <InputGroup className="mb-3">
                                <Form.Control
                                    type="date"
                                    value={date}
                                    onChange={(e) => setDate(e.target.value)}
                                />
                            </InputGroup>
                        </Form.Group>

                        <Form.Group className="mb-3">
                            <Form.Label>Languages</Form.Label>
                            <Form.Control className="mb-3" type="text" value={languageString} onChange={(e) => setLanguageString(e.target.value)}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Cast</Form.Label>
                            <Form.Control className="mb-3" type="text" value={castString} onChange={(e) => setCastString(e.target.value)}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Directors</Form.Label>
                            <Form.Control className="mb-3" type="text" value={dirString} onChange={(e) => setDirString(e.target.value)}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Writers</Form.Label>
                            <Form.Control className="mb-3" type="text" value={writerString} onChange={(e) => setWriterString(e.target.value)}>
                            </Form.Control>
                        </Form.Group>
                        <Form.Group className="mb-3">
                            <Form.Label>Year</Form.Label>
                            <Form.Control className="mb-3" type="text" value={year} onChange={(e) => setYear(e.target.value)}>
                            </Form.Control>
                        </Form.Group>

                        <ButtonToolbar className="w-80 justify-content-between">
                        <Button variant="primary" type="submit">
                            Submit Edits
                        </Button>
                        <Button variant="danger" onClick={showEditDialog}>Delete Movie</Button>
                        </ButtonToolbar>
                    </Form>
                    
                    {responseAlert.message != "" ? <Alert>{responseAlert.message}</Alert> : <></>}
                </Card>
                    : <></>}
            {context.user.grantedAuthorities[0] == "ADMIN" ? <Modal show={show} onHide={closeEditDialog}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit Profile</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    Do you want to delete {movie.title} permanently?
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={deleteMovie} >
                        Yes
                    </Button>
                    <Button variant="secondary" onClick={closeEditDialog}>
                        No
                    </Button>
                </Modal.Footer>
            </Modal>:<></>}

            

        </div>
    )
}
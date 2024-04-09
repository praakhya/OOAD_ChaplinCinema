import axios from "axios";
import { createRef, useState } from "react";
import { Form, Button, Alert } from "react-bootstrap";
import { useOutletContext } from "react-router-dom";
import { baseUrl } from "../paths";
import { Card, InputGroup, ButtonToolbar } from "react-bootstrap";
function MovieAdd() {
    const context = useOutletContext();
    
    const [responseAlert, setResponseAlert] = useState({
        message: "",
        status: ""
    })
    const [movie, setMovie] = useState({})
    const [posterString, setPosterString] = useState("")
    const [genreString, setGenreString] = useState("")
    const [languageString, setLanguageString] = useState("")
    const [castString, setCastString] = useState("")
    const [dirString, setDirString] = useState("")
    const [writerString, setWriterString] = useState("")
    const [year, setYear] = useState("")
    const [title, setTitle] = useState("")
    const [overview, setOverview] = useState("")
    const [date, setDate] = useState(null)
    function movieAddRequest(event) {
        event.preventDefault()
        axios.post(baseUrl + '/movies', {
            title: title,
            overview: overview,
            genres: genreString.split(","),
            languages: languageString.split(","),
            cast: castString.split(","),
            directors: dirString.split(","),
            writers: writerString.split(","),
            year: year,
            poster: posterString,
            released: date
        },
          {
            headers: {
              'Content-Type': 'application/json',
              "Authorization": "Bearer " + context.user.authToken.authToken
            }
          })
          .then((response) => {
            console.log("response:", response.data)
          })
          .catch((err) => {
            console.log("err:", err);
          })
      }
    {
        return context.user.grantedAuthorities[0] == "ADMIN" ? <Card className="p-5">
            <Card.Title className="fs-1">Add Movie Details</Card.Title>
            <Form onSubmit={movieAddRequest}>
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
                            placeholder="Enter runtime"
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
                    Add Movie
                </Button>
                </ButtonToolbar>
            </Form>
            
            {responseAlert.message != "" ? <Alert>{responseAlert.message}</Alert> : <></>}
        </Card>
            : <></>}
}
export default MovieAdd;
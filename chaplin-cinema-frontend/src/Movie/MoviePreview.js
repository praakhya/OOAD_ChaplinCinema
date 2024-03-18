import { Button, ButtonToolbar, Card, ButtonGroup, InputGroup, Form } from "react-bootstrap"
import { useParams } from "react-router-dom"
import { movies, languages } from "../testConstants";
import { MdOutlineSearch } from "react-icons/md";
export default function MoviePreview() {
    const { id } = useParams();
    const movie = Array.from(new Set(movies)).filter((m) => { return m.movie_id == id })[0]
    movie.release_date = Date(movie.release_date)
    return (
        <div>
            <ButtonToolbar className="w-100 justify-content-between flex-row-reverse m-5">
                <ButtonGroup>
                    <Button>aedfwef</Button>
                </ButtonGroup>
                {/* <ButtonGroup className="me-2" aria-label="First group">
                    <Button variant="secondary">
                        <MdGridView />
                    </Button>{' '}
                    <Button variant="secondary">
                        <MdList />
                    </Button>{' '}
                </ButtonGroup> */}
                <InputGroup>
                    <InputGroup.Text id="btnGroupAddon">
                        <MdOutlineSearch />
                    </InputGroup.Text>
                    <Form.Control
                        type="text"
                        placeholder="Input group example"
                        aria-label="Input group example"
                        aria-describedby="btnGroupAddon"
                    />
                </InputGroup>
            </ButtonToolbar>
            <Card className='d-flex flex-row' data-bs-theme="dark">
                <Card.Img src={movie.poster_path} style={{ width: "15vw" }} />
                <Card.Body>
                    <Card.Title>{movie.title}</Card.Title>
                    <Card.Subtitle>Released on: {movie.release_date}</Card.Subtitle>
                    <Card.Subtitle>Language: {languages[movie.original_language]}</Card.Subtitle>
                    <Card.Text>
                        {movie.overview}
                    </Card.Text>
                </Card.Body>
            </Card>
        </div>
    )
}
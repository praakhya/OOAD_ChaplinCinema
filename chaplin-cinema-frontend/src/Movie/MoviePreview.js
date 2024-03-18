import { Button, ButtonToolbar, Card, Alert } from "react-bootstrap"
import { useOutletContext, useParams } from "react-router-dom"
import { languages } from "../testConstants";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";

export default function MoviePreview() {
    const context = useOutletContext()
    const { id } = useParams();
    console.log("CONTEXT:", context)
    var movie = null
    if (context != undefined) { movie = Array.from(new Set(context.movies)).filter((m) => { return m.id == id })[0] }
    const dark = "rgb(25,28,31)"
    const navigate = useNavigate()
    function stringToDate(d) {
        var date;
        if (d!=undefined) {
            date = new Date(d)
            return date.getDate() + "-" + date.getMonth() + "-" + date.getFullYear()
        }
        return ""
    }
    const getLanguage = (code) => {
        const lang = new Intl.DisplayNames(['en'], {type: 'language'});
        return lang.of(code);
    }
    
    console.log("MOVIE PREVIEW:", movie)
    return (
        movie!=null ? <div>
            <Card data-bs-theme="dark" style={{ borderRadius: 0 }}>
                <ButtonToolbar className="align-items-left flex-row">
                    <Button variant="secondary" className="m-3" onClick={() => { navigate("/movies") }}>Back</Button>
                </ButtonToolbar>
                <div className='d-flex flex-row p-5'>
                    <Card.Img src={movie.posterPath} style={{ width: "15vw" }} />
                    <Card.Body>
                        <Card.Title className="fs-1">{movie.title}</Card.Title>
                        <Card.Text>
                            <span className="fs-5">Released on: {stringToDate(movie.releaseDate)}</span>
                            <br />
                                <span>
                                Language: {getLanguage(movie.originalLanguage)}</span>
                        </Card.Text>
                        <Card.Footer>
                        {movie.overview}
                        </Card.Footer>
                    </Card.Body>
                </div>
            </Card>
            <div className="d-flex flex-column align-items-center m-5">
                <Alert variant="secondary">
                    Booking Selection feature is yet to be added
                </Alert>
            </div>
        </div> : <></>
    )
}
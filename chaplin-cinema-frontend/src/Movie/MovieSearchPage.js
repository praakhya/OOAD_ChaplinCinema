import { ButtonToolbar, CloseButton, ListGroup } from "react-bootstrap";
import { useNavigate, useOutletContext } from "react-router-dom";
import { SearchedMovieContext } from "./MoviePage";
import { useContext } from "react";
function MovieSearchPage() {
    const searchedMovieContext = useContext(SearchedMovieContext)
    var movies = searchedMovieContext.searchedMovies
    console.log("IN SEARCH PAGE:", movies)
    var i = 0;
    const context = useOutletContext()
    const navigate = useNavigate()
    return (
        <>
        <ButtonToolbar className="d-flex align-items-start justify-content-start w-75">
            <CloseButton onClick={()=>{
                searchedMovieContext.setSearchedMovies([])
                searchedMovieContext.setSearchPhrase("")
                }}/>
        </ButtonToolbar>
        <ListGroup>
            {
                movies.map(m => {
                    return <ListGroup.Item key={i++} action onClick={()=>navigate("/movie/"+m.id)} as="div" style={{width:"75vw"}}>{m.title}</ListGroup.Item>
                })
            }
        </ListGroup>
        </>
    )
}

export default MovieSearchPage;
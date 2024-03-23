import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { MdGridView } from "react-icons/md";
import { MdList } from "react-icons/md";
import ToggleButton from 'react-bootstrap/ToggleButton';
import { useContext, useState, useEffect, createContext } from 'react';
import { useNavigate, useOutletContext } from 'react-router-dom';
import axios from "axios"
import { baseUrl, url8080 } from "../paths";
import MovieSearchPage from './MovieSearchPage';

export const SearchedMovieContext = createContext()
const dummyImage = "/images/placeholderPoster.jpg"
export default function MoviePage() {
    const context = useOutletContext()
    const [radioValue, setRadioValue] = useState('list');
    var key = 0
    const navigate = useNavigate();
    const radios = [
        { name: <MdGridView />, value: 'grid' },
        { name: <MdList />, value: 'list' },
    ];
    var [searchPhrase, setSearchPhrase] = useState("")
    const descCharLimit = 200
    useEffect(() => {
        if (context.user == null) {
            navigate("/login")
        }
        getMovies();
    }, []);
    
    function getMovies() {
        console.log("token:", context.user.authToken.authToken)
        axios.get(baseUrl + '/movies', {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + context.user.authToken.authToken
            }
        })
            .then((response) => {
                console.log("MOVIEPAGE response:",response.data.content)
                context.setMovies(response.data.content)
                console.log("MOVIEPAGE movies:",context)
            })
            .catch((err) => {
                console.log("err:", err);
            })

    }
    function getGenres() {
        console.log("token:", context.user.authToken.authToken)
        axios.get(baseUrl + '/genres', {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + context.user.authToken.authToken
            }
        })
            .then((response) => {
                console.log("genre response:",response.data)
                const genres = []
                response.data.forEach((value) => {
                    genres.push(value);
                  });
                  console.log("genres in moviePage:",genres)
                  context.setGenres(genres)
                  localStorage.setItem("genres",JSON.stringify(genres))
            
                console.log("context in moviePage:",context)
            })
            .catch((err) => {
                console.log("err:", err);
            })

    }
    

    return (
            <div className="w-100 d-flex flex-column align-items-center">
                <ButtonToolbar className="w-75 justify-content-between flex-row-reverse m-5">
                    <ButtonGroup>
                        {radios.map((radio, idx) => (
                            <ToggleButton
                                key={idx}
                                id={`radio-${idx}`}
                                type="radio"
                                name="radio"
                                variant='light'
                                value={radio.value}
                                checked={radioValue === radio.value}
                                onChange={(e) => setRadioValue(e.currentTarget.value)}
                            >
                                {radio.name}
                            </ToggleButton>
                        ))}
                    </ButtonGroup>
                    
                </ButtonToolbar>
                <SearchedMovieContext.Provider value={{searchPhrase, setSearchPhrase}}>
                    <MovieSearchPage/>
                </SearchedMovieContext.Provider>
                <div className={radioValue == 'list' ? 'w-75 d-flex flex-column align-items-center gap-3 m-5' : 'w-75 d-flex flex-row align-items-center gap-3 m-5 flex-wrap justify-content-center'}>
                    {
                        context.movies!=[] ? context.movies.map((movie) => {
                            return radioValue == 'list'
                                ?
                                <Card className='d-flex flex-row w-100' data-bs-theme="light" key={key++}>
                                    <Card.Img variant="top" src={movie.poster ? movie.poster : url8080+"/images/placeholderPoster.jpg"} style={{ width: "15vw" }} />
                                    <Card.Body>
                                        <Card.Title>{movie.title}</Card.Title>
                                        <Card.Text>
                                            {movie.overview ? movie.overview.substring(0, descCharLimit) + "..." : <></>}
                                        </Card.Text>
                                        <Button variant="secondary" onClick={() => navigate(`/movie/${movie.id}`)}>Book</Button>
                                    </Card.Body>
                                </Card>
                                :
                                <Card className='d-flex flex-column' style={{ width: "200px" }} data-bs-theme="light" key={key++}>
                                    <Card.Img variant="top" src={movie.poster} style={{ width: "200px" }} />
                                    <Card.Body>
                                        <Card.Title>{movie.title}</Card.Title>
                                        <Button variant="secondary" onClick={() => navigate(`/movie/${movie.id}`)}>Book</Button>
                                    </Card.Body>
                                </Card>
                        }
                        ):<></>}
                </div>                
            </div>
    )

}

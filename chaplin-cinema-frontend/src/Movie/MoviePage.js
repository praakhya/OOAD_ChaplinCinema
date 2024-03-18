import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { movies } from '../testConstants';
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ButtonToolbar from 'react-bootstrap/ButtonToolbar';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { MdGridView } from "react-icons/md";
import { MdList } from "react-icons/md";
import { MdOutlineSearch } from "react-icons/md";
import ToggleButton from 'react-bootstrap/ToggleButton';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
export default function MoviePage() {
    const [checked, setChecked] = useState(false);
    const [radioValue, setRadioValue] = useState('list');
    var key=0
    const navigate = useNavigate();
    const radios = [
        { name: <MdGridView />, value: 'grid' },
        { name: <MdList />, value: 'list' },
    ];
    return (<>

        <div className="w-100 d-flex flex-column align-items-center">
            <ButtonToolbar className="w-75 justify-content-between flex-row-reverse m-5">
                <ButtonGroup>
                    {radios.map((radio, idx) => (
                        <ToggleButton
                            key={idx}
                            id={`radio-${idx}`}
                            type="radio"
                            name="radio"
                            variant='dark'
                            value={radio.value}
                            checked={radioValue === radio.value}
                            onChange={(e) => setRadioValue(e.currentTarget.value)}
                        >
                            {radio.name}
                        </ToggleButton>
                    ))}
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
            <div className={radioValue == 'list' ? 'w-75 d-flex flex-column align-items-center gap-3 m-5' : 'w-75 d-flex flex-row align-items-center gap-3 m-5 flex-wrap justify-content-center'}>
                
                {
                movies.map((movie) =>
                    {
                        return radioValue == 'list'
                        ?
                        <Card className='d-flex flex-row' data-bs-theme="dark" key={key++}>
                            <Card.Img variant="top" src={movie.poster_path} style={{ width: "15vw" }} />
                            <Card.Body>
                                <Card.Title>{movie.title}</Card.Title>
                                <Card.Text>
                                    {movie.overview}
                                </Card.Text>
                                <Button variant="secondary" onClick={()=>navigate(`/movie/${movie.movie_id}`)}>Book</Button>
                            </Card.Body>
                        </Card>
                        :
                        <Card className='d-flex flex-column' data-bs-theme="dark" key={key++}>
                            <Card.Img variant="top" src={movie.poster_path} style={{ height: "30vh" }} />
                            <Card.Body>
                                <Card.Title>{movie.title}</Card.Title>
                                <Button variant="secondary">Book</Button>
                            </Card.Body>
                        </Card>
                    }
                )}
            </div>
        </div>
    </>
    )

}
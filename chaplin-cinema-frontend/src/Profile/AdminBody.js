import { Tabs, Tab, Card, Form, Button } from "react-bootstrap";
import { useOutletContext } from "react-router-dom";
import { languages } from "../language";
import { createRef } from "react";
import axios from "axios";
import { baseUrl } from "../paths";
function AdminBody() {
  const context = useOutletContext()
  const genreRef = createRef()
  const languageRef = createRef()
  const titleRef = createRef()
  const originalTitleRef = createRef()
  console.log("CONTEXT IN ADMIN:", context)
  const genres = context.genres
  console.log("Genres:", genres)
  function movieAddRequest(event) {
    event.preventDefault()
    console.log("Genre ref:",genreRef.current.value,genres.find((element) => element.genreName === genreRef.current.value))
    axios.post(baseUrl + '/movies', {
        originalTitle: originalTitleRef.current.value,
        title: titleRef.current.value,
        originalLanguage: languageRef.current.value,
        genreIds: [genres.find((element) => element.genreName === genreRef.current.value).id]
    },
    {
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + context.user.authToken.authToken
        }
    })
        .then((response) => {
            console.log("response:",response.data)
        })
        .catch((err) => {
            console.log("err:", err);
        })
}
  return (
    <div className="w-100">
      <Tabs
        defaultActiveKey="addMovie"
        id="uncontrolled-tab-example"
        className="mb-3"
      >
        <Tab eventKey="addMovie" title="Add Movie">

          <Form onSubmit={movieAddRequest}>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Genre</Form.Label>
              <Form.Select className="text-muted" ref={genreRef}>
                <option>Select Genre</option>
                {genres.map((genre) => {
                  return (
                    <option key={genre.id} value={genre.genreName}>
                      {genre.genreName}
                    </option>
                  );
                })}
              </Form.Select>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Language</Form.Label>
              <Form.Select className="text-muted" ref={languageRef}>
                <option>Select Language</option>
                {languages.map((lang) => {
                  return (
                    <option key={lang.code} value={lang.code}>
                      {lang.name}
                    </option>
                  );
                })}
              </Form.Select>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Original Title</Form.Label>
              <Form.Control type="text" placeholder="Enter Original Title" ref={originalTitleRef}/>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPassword">
              <Form.Label>Title</Form.Label>
              <Form.Control type="text" placeholder="Enter Title" ref={titleRef}/>
            </Form.Group>
            <Button variant="primary" type="submit">
              Submit
            </Button>
          </Form>
        </Tab>
        <Tab eventKey="deleteMovie" title="Delete Movie">
          Tab content for Profile
        </Tab>
        <Tab eventKey="updateMovie" title="Update Movie">
          Tab content for Profile
        </Tab>
      </Tabs>
    </div>

  )
}
export default AdminBody;
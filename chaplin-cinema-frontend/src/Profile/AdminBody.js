import { Tabs, Tab, Card, Form, Button } from "react-bootstrap";
import { useOutletContext } from "react-router-dom";
import { languages } from "../language";
import { createRef } from "react";
import axios from "axios";
import { baseUrl } from "../paths";
import { useState } from "react";
import MovieSearchPage from "../Movie/MovieSearchPage";
import { SearchedMovieContext } from "../Movie/MoviePage";
import MovieAdd from "../Movie/MovieAdd";
function AdminBody() {
  const context = useOutletContext()
  const genreRef = createRef()
  const languageRef = createRef()
  const titleRef = createRef()
  const originalTitleRef = createRef()
  const genres = []

  var [searchPhrase, setSearchPhrase] = useState("")
  var [searchedMovies, setSearchedMovies] = useState([])
  console.log("CONTEXT IN ADMIN:", context)
  console.log("Genres:", genres)
  function movieAddRequest(event) {
    event.preventDefault()
    console.log("Genre ref:", genreRef.current.value, genres.find((element) => element.genreName === genreRef.current.value))
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
        console.log("response:", response.data)
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

          <MovieAdd/>
        </Tab>
        <Tab eventKey="updateMovie" title="Modify Movie">
          <SearchedMovieContext.Provider value={{ searchPhrase, setSearchPhrase }}>
            <MovieSearchPage />
          </SearchedMovieContext.Provider>
        </Tab>
      </Tabs>
    </div>

  )
}
export default AdminBody;
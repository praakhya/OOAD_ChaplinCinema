import { Alert } from "react-bootstrap";
function CustomerBody(props) {
    console.log("props:",props)
    return (
        <div className="w-100">
                    <div>
                        <h1>Booked Movies</h1>
                        {props.bookings.length != 0 ?
                            <div>
                                {props.bookings.map(m => {
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
                            {props.moviesWatched.length != 0 ?
                                <div>
                                    {props.MoviesWatched.map(m => {
                                        return <p>{m.title}</p>
                                    })}
                                </div>

                                :
                                <Alert variant="secondary">No movies watched yet!</Alert>
                            }
                        </div>
                    </div>

                </div>
        
    )
}
export default CustomerBody;
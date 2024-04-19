import { ButtonToolbar, CloseButton, ListGroup } from "react-bootstrap";
import { useNavigate, useOutletContext } from "react-router-dom";
import { useContext, useEffect } from "react";
import { useState } from "react";
import axios from "axios";
import { baseUrl } from "../paths";
import InputGroup from 'react-bootstrap/InputGroup';
import { MdOutlineClose, MdOutlineSearch } from "react-icons/md";
import { Form } from "react-bootstrap";
import { SearchedCustomerContext } from "./Profile";
function CustomerSearchPage() {
    const searchedCustomerContext = useContext(SearchedCustomerContext)
    var [searchedCustomers, setSearchedCustomers] = useState([])

    useEffect(() => {
        getCustomersBySearchPhrase(searchedCustomerContext.searchPhrase)
    }, [searchedCustomerContext.searchPhrase])
    function getCustomersBySearchPhrase(val) {
        if (val == "") { return }
        console.log("token:", context.user.authToken.authToken)
        axios.get(baseUrl + '/movies/search/' + val, {
            headers: {
                'Content-Type': 'application/json',
                "Authorization": "Bearer " + context.user.authToken.authToken
            }
        })
            .then((response) => {
                console.log("response:", response.data.content)
                setSearchedCustomers(response.data.content)
                console.log("FINSHED SEARCH:", searchedCustomers)
            })
            .catch((err) => {
                console.log("err:", err);
            })

    }
    console.log("IN SEARCH PAGE:", searchedCustomers)
    var i = 0;
    const context = useOutletContext()
    const navigate = useNavigate()
    return (
        <>
            <ButtonToolbar className="d-flex align-items-start justify-content-start w-75">

                <InputGroup data-bs-theme="light">
                    <InputGroup.Text id="btnGroupAddon">
                        <MdOutlineSearch />
                    </InputGroup.Text>
                    {searchedCustomers.length > 0 ? <InputGroup.Text onClick={()=>{
                setSearchedCustomers([])
                searchedCustomerContext.setSearchPhrase("")
                }}>
                        <MdOutlineClose />
                    </InputGroup.Text> :<></>}
                    <Form.Control
                        type="text"
                        placeholder="Input group example"
                        aria-label="Input group example"
                        aria-describedby="btnGroupAddon"
                        value={searchedCustomerContext.searchPhrase}
                        onChange={(e) => searchedCustomerContext.setSearchPhrase(e.target.value)}
                    />
                </InputGroup>
            </ButtonToolbar>
            <ListGroup data-bs-theme="light">
                {
                    searchedCustomers.map(m => {
                        return <ListGroup.Item key={i++} action onClick={() => navigate("/customers/" + m.id)} as="div" style={{ width: "75vw" }}>{m.title}</ListGroup.Item>
                    })
                }
            </ListGroup>
        </>
    )
}

export default CustomerSearchPage;
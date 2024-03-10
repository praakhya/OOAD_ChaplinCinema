import axios from "axios"
import { baseUrl } from "../paths";
export default function loginRequest(username,password) {
    axios.post(baseUrl + '/auth',{
        username:username,
        password:password
    },{headers:{ 'Content-Type': 'application/json' }})
    .then((response) => {
        console.log(response.data)
        localStorage.setItem("user", JSON.stringify(response.data))
    })
    .catch((err) => {
        console.log("err:",err);
        })
         
}
import axios from "axios"


export default function loginRequest(username,password) {
    axios.post('/api/v1/auth',{
        "username":username,
        "password":password
    },{headers:{ 'Content-Type': 'application/json' }})
            .then((response) => console.log(response))
            .catch((err) => {
                console.log("err:",err);
             })
         
}
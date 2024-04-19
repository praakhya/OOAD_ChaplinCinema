async function loginRequest(username,password) {
        
    axios.post(baseUrl + '/auth',{
        username:username,
        password:password
    },{headers:{ 'Content-Type': 'application/json' }})
    .then((response) => {
        var user = response.data
        const username = user.username
        const authToken = user.authToken
        const path = user.grantedAuthorities[0]=="CUSTOMER" ? "customers" 
        : user.grantedAuthorities[0]=="ADMIN" ? "admins" 
        : ""

        path!="" ? axios.get(`${baseUrl}/${path}/username/${username}`, {
            headers:{
                'Content-Type': 'application/json',
                "Authorization": "Bearer "+user.authToken.authToken
            }
        })
        .then((response)=>{
            console.log(response)
            user = response.data
            user.authToken = authToken
            console.log("After body:",user, user.authToken)
            localStorage.setItem("user", JSON.stringify(user))
            return user
        })
        .catch((err)=>{
            console.log("err:",err);
        })
        : localStorage.setItem("user", JSON.stringify(user))
        context.setUser(user)
        navigate("/movies")
    })
    .catch((err) => {
        console.log("err:",err);
        })
         
}
async function getMovies() {
    console.log("token:", context.user.authToken.authToken)
    axios.get(baseUrl + '/movies', {
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + context.user.authToken.authToken
        }
    })
        .then((response) => {
            console.log("response:",response.data.content)
            localStorage.setItem("movies",JSON.stringify(response.data.content))
            return response.data.content
        })
        .catch((err) => {
            console.log("err:", err);
        })

}
async function getGenres() {
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
              localStorage.setItem("genres",JSON.stringify(genres))
              return genres
        
            console.log("context in moviePage:",context)
        })
        .catch((err) => {
            console.log("err:", err);
        })

}
async function getMoviesBySearchPhrase(val) {
    if (val=="") {return}
    console.log("token:", context.user.authToken.authToken)
    axios.get(baseUrl + '/movies/search/'+val, {
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + context.user.authToken.authToken
        }
    })
        .then((response) => {
            localStorage.setItem("searchedMovies",JSON.stringify(response.data.content))
            console.log("FINSHED SEARCH:",searchedMovies)
            return searchedMovies
        })
        .catch((err) => {
            console.log("err:", err);
        })

}
async function editAdminRequest(event) {
    event.preventDefault()
    axios.put(baseUrl + '/admins', {
        id: context.user.id,
        username: context.user.username,
        password: password
    },
    {
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + context.user.authToken.authToken
        }
    })
        .then((response) => {
            console.log("response:",response.data)
            if (response.data == undefined) {
                return
            }
            var modifiedUser = context.user
            modifiedUser.password = response.data.password
            localStorage.setItem("user",JSON.stringify(modifiedUser))
            return modifiedUser
        })
        .catch((err) => {
            console.log("err:", err);
            setResponseAlert({
                message: "User could not be modified",
                status: err.message
            })
        })
}
async function editCustomerRequest(event) {
    event.preventDefault()
    axios.put(baseUrl + '/customers', {
        id: context.user.id,
        username: context.user.username,
        password: password,
        firstName: firstName,
        lastName: lastName
    },
    {
        headers: {
            'Content-Type': 'application/json',
            "Authorization": "Bearer " + context.user.authToken.authToken
        }
    })
        .then((response) => {
            console.log("response:",response.data)
            if (response.data == undefined) {
                return
            }
            var modifiedUser = context.user
            modifiedUser.firstName = response.data.firstName
            modifiedUser.lastName = response.data.lastName
            modifiedUser.password = response.data.password
            context.setUser(modifiedUser)
            localStorage.setItem("user",JSON.stringify(modifiedUser))
            return modifiedUser
        })
        .catch((err) => {
            console.log("err:", err);
            setResponseAlert({
                message: "User could not be modified",
                status: err.message
            })
        })
}
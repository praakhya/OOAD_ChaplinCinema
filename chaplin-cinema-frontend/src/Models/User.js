export default class User {
    User(username, password, grantedAuthorities, authToken){
        this.username = username
        this.password = password
        this.grantedAuthorities = grantedAuthorities
        this.authToken = authToken
    }
}
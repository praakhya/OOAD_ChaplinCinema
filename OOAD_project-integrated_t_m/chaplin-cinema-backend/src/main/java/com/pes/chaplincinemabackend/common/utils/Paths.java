package com.pes.chaplincinemabackend.common.utils;

public class Paths {

    public static final String fullPath = "";
    public class Users {
        public static final String Base = "/users";
    }
    public class Profile {
        public static final String Base = "/profile";
    }
    public class Movies {
        public static final String Base = "/movies";
    }
    public class Genres {
        public static final String Base = "/genres";
    }
    public class Customers{
        public static final String Base = "/customers";
        public static final String GetOnePathVariable = "id";
        public static final String GetOne = "{" + GetOnePathVariable + "}";
        public static final String GetCustomersByUsernameVariable = "username";
        public static final String GetCustomersByUsername = "/"+GetCustomersByUsernameVariable+"/" + "{" + GetCustomersByUsernameVariable + "}";

        public static final String GetCustomersByFirstNameVariable = "firstName";
        public static final String GetCustomersByLastNameVariable = "lastName";
        public static final String GetCustomersByLastName =  "/"+ GetCustomersByLastNameVariable +"/" + "{" + GetCustomersByLastNameVariable + "}";
        public static final String GetCustomersByFirstName = "/"+GetCustomersByFirstNameVariable+"/" + "{" + GetCustomersByFirstNameVariable + "}";
        public static final String GetSubstringPathVariable = "customerSubstring";
        public static final String GetSubstringPath = "/search/"+"{" + GetSubstringPathVariable + "}";
    }
    public class Login {

        public static final String Base = "/login";
    }
    public class Signup {

        public static final String Base = "/signup";
    }

    public class Admins {
        public static final String Base = "/admins";
        public static final String GetAdminByUsernameVariable = "username";
        public static final String GetOneByIDPathVariable = "id";

        public static final String GetAdminByUsernamePath ="/" + GetAdminByUsernameVariable +"/{" + GetAdminByUsernameVariable + "}";
        public static final String GetOneByID = "/" + GetOneByIDPathVariable +"/" + "{" + GetOneByIDPathVariable + "}";

    }
    public class Image {
        public static final String Base = "/images";
    }

}
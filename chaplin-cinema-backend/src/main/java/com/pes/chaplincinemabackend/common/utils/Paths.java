package com.pes.chaplincinemabackend.common.utils;

public class Paths {

    public static final String basePath = "/api";
    public static final String pageVariable = "page";
    public static final String pagePath = "{"+pageVariable+"}";
    public static final String sizeVariable = "size";
    public static final String sizePath = "{"+sizeVariable+"}";
    public static final String pageAndSizePath = pagePath + "/" + sizePath;
    public class V1 {
        public static final String Base = basePath + "/v1";
        public static final String fullPath = Base;
        public class Users {
            public static final String Base = "/users";
            public static final String fullPath = Paths.V1.fullPath + Base;
            public static final String GetOnePathVariable = "username";
            public static final String GetOne = "{" + GetOnePathVariable + "}";
        }
        public class Movies {
            public static final String Base = "/movies";
            public static final String fullPath = Paths.V1.fullPath + Base;
            public static final String GetOnePathVariable = "movieId";
            public static final String GetOne = "{" + GetOnePathVariable + "}";
            public static final String GetPhrasePathVariable = "moviePhrase";
            public static final String GetPhrasePath = "/search/"+"{" + GetPhrasePathVariable + "}";
        }
        public class Customers{
            public static final String Base = "/customers";
            public static final String fullPath = Paths.V1.fullPath + Base;
            public static final String GetOnePathVariable = "id";
            public static final String GetOne = "{" + GetOnePathVariable + "}";
            public static final String GetCustomersByUsernameVariable = "username";
            public static final String GetCustomersByUsername = "/"+GetCustomersByUsernameVariable+"/" + "{" + GetCustomersByUsernameVariable + "}";

            public static final String GetCustomersByFirstNameVariable = "firstName";
            public static final String GetCustomersByLastNameVariable = "lastName";
            public static final String GetCustomersByLastName =  "/"+ GetCustomersByLastNameVariable +"/" + "{" + GetCustomersByLastNameVariable + "}";
            public static final String GetCustomersByFirstName = "/"+GetCustomersByFirstNameVariable+"/" + "{" + GetCustomersByFirstNameVariable + "}";
        }
        public class Auth {

            public static final String Base = "/auth";
            public static final String fullPath = Paths.V1.fullPath + Base;
            public static final String Refresh = "refresh";
        }

        public class Admins {
            public static final String Base = "/admins";
            public static final String fullPath = Paths.V1.fullPath + Base;
            public static final String GetOnePathVariable = "username";
            public static final String GetOneByIDPathVariable = "id";

            public static final String GetOne = "{" + GetOnePathVariable + "}";
            public static final String GetOneByID = "/" + GetOneByIDPathVariable +"/" + "{" + GetOneByIDPathVariable + "}";

        }
        public class Image {
            public static final String Base = "/images";
        }
    }
}
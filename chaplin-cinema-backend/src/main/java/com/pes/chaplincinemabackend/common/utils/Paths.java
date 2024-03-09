package com.pes.chaplincinemabackend.common.utils;

public class Paths {

    public static final String basePath = "/api";
    public class V1 {
        public static final String Base = basePath + "/v1";
        public static final String fullPath = Base;
        public class Users {
            public static final String Base = "/users";
            public static final String fullPath = Paths.V1.fullPath + Base;
            public static final String GetOnePathVariable = "username";
            public static final String GetOne = "{" + GetOnePathVariable + "}";
        }
        public class Customers{
            public static final String Base = "/customers";
            public static final String fullPath = Paths.V1.fullPath + Base;
            public static final String GetOnePathVariable = "username";
            public static final String GetOne = "{" + GetOnePathVariable + "}";
            public static final String GetCustomersByFirstNameVariable = "firstName";
            public static final String GetCustomersByLastNameVariable = "lastName";
            public static final String GetCustomersByLastName =  "/lastname/" + "{" + GetCustomersByLastNameVariable + "}";
            public static final String GetCustomersByFirstName = "/firstname/" + "{" + GetCustomersByFirstNameVariable + "}";
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
            public static final String GetOne = "{" + GetOnePathVariable + "}";

        }
    }
}
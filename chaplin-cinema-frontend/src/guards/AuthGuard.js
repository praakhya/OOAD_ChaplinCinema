import React, { useEffect } from 'react';
import { useOutletContext } from 'react-router-dom';
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
const AuthGuard = ({ component }) => {
    const [status, setStatus] = useState(false);
    const navigate = useNavigate();
    const context = useOutletContext();
    const checkToken = async () => {
        try {
            let user = context.user
            console.log("USER IN GUARD:",user)
            if (!user) {
                navigate(`/login`);
            }
            setStatus(true);
            return;
        } catch (error) {
            console.log("IN GUARD:",error)
            navigate(`/login`);
        }
    }
    useEffect(() => {
        console.log("Auth Guard");
        checkToken()
    }, []);

   return status ?  <React.Fragment>{component}</React.Fragment> :  <React.Fragment></React.Fragment>
}

export default AuthGuard;
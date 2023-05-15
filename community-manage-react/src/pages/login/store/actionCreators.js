import axios from 'axios';
import * as constants from './constants';
import jwt_decode from 'jwt-decode'
import setAuthToken from "../../../utils/AuthToken";


const loginSuccess = (decoded,roles)=>({
    type : constants.LOGIN_SUCCESS,
    loginState: true,
    user: decoded,
    roles: roles
})

const loginError = (error)=>({
    type : constants.LOGIN_ERROR,
    loginState: false,
    message: error
})

export const loginRequest = (values) => {
    return (dispatch) => {
        axios.post('/api/user/login',values).then((res) => {
            const token  = res.data.data.token;
            const roles = res.data.data.roles;
            localStorage.setItem('jwtToken',token);
            setAuthToken(token);
            const decoded = jwt_decode(token);
            console.log(decoded);

            const action = loginSuccess(decoded,roles);
            dispatch(action);
        }).catch(error => {
            console.log(error);
            const action = loginError(error);
            dispatch(action);
        }).then(

        );
    }
};
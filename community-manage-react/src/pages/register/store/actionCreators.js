import axios from 'axios';
import * as constants from './constants';

const registerSuccess = ()=> ({
    type :constants.REGISTER_SUCCESS,
    registerState: true
});

const registerError = (error)=> ({
    type :constants.REGISTER_ERROR,
    registerState: false,
    message: error
});

export const registerRequest = (values) => {
    return (dispatch) => {

        axios.post('/api/user/register',values).then((res) => {
            const action = registerSuccess();
            dispatch(action);
        }).catch(error => {
            const action = registerError(error);
            dispatch(action);
        });
    }
};


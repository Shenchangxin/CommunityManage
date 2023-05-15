import { fromJS } from 'immutable';
import * as constants from './constants';

const defaultState = fromJS ({
    user:{},
    username: '河畔一角',
    roles:[],
    loginState: false,
    message:''
});

const loginSuccess =(state,action)=>{
    return state.merge({
        'user': action.user,
        'loginState': action.loginState,
        'username': action.user.sub,
        'roles': action.roles
    });
}
const loginError =(state,action)=>{
    return state.merge({
        'loginState': action.loginState,
        'message': action.message
    });
}

export default (state = defaultState,action) => {
    switch (action.type) {
        case constants.LOGIN_SUCCESS:
            return loginSuccess(state,action)
        case constants.LOGIN_ERROR:
            return loginError(state, action)
        default:
            return state;
    }

}
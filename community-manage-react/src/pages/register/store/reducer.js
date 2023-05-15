import { fromJS } from 'immutable';
import * as constants from './constants';

const defaultState = fromJS ({
    registerState: false,
    message:''
});

const registerSuccess =(state,action)=>{
    return state.merge({
        'registerState': action.registerState
    });
}
const registerError =(state,action)=>{
    return state.merge({
        'registerState': action.registerState,
        'message': action.message
    });
}

export default (state = defaultState,action) => {
    switch (action.type) {
        case constants.REGISTER_SUCCESS:
            return registerSuccess(state,action)
        case constants.REGISTER_ERROR:
            return registerError(state, action)
        default:
            return state;
    }

}
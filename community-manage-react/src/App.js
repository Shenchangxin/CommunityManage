import React,{ Component } from 'react';

class App extends Component {


    render(){
        return (
            //将store提供给<Provider></Provider>包裹的组件
            // <Provider store={store}>
            //     <BrowserRouter>
            //         <div>
            //
            //             <Route path='/admin' exact component={Admin}></Route>
            //
            //         </div>
            //     </BrowserRouter>
            // </Provider>
            <div>
                {this.props.children}
            </div>

        );
    }

}



export default App;

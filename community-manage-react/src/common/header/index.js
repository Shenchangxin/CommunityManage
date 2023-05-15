import React from 'react';
import {Col, Row} from "antd";
import './index.less';
import Util from '../../utils/utils';
import axios from '../../axios';
import {connect} from "react-redux";

 class Header extends React.Component {
     state = {
         sysTime:'',

     }
    componentWillMount() {

        setInterval(()=>{
            let sysTime = Util.formatDate(new Date().getTime());
            this.setState({
                sysTime
            })
        },1000)
        this.getWeatherAPIData();
    }
    getWeatherAPIData(){
        let city = '北京';
        axios.jsonp({
            url:'http://api.map.baidu.com/telematics/v3/weather?location='+encodeURIComponent(city)+'&output=JSON&ak=wUsSS1dGc3l3EmpgE4KLHweCUY3uIeR9'
        }).then((res) => {

            if (res.status === 'success'){
                let data = res.results[0].weather_data[0];
                this.setState({
                    dayPictureUrl: data.dayPictureUrl,
                    weather: data.weather
                })
            }
        })
    }

    render() {
        return (
            <div className="header">
                <Row className="header-top">
                    <Col span="24">
                        <span>欢迎，{this.props.username}</span>
                        <a href="#">退出</a>
                    </Col>
                </Row>
                <Row className="breadcrumb">
                    <Col span="4" className="breadcrumb-title">
                        首页
                    </Col>
                    <Col span="20" className="weather">
                        <span className="date">{this.state.sysTime}</span>
                        <span className="weather-img">
                            <img src={this.state.dayPictureUrl} alt=""/>
                        </span>
                        <span className="weather-detail">
                            {this.state.weather}
                        </span>
                    </Col>
                </Row>
            </div>
        );
    }
}
const mapState = (state) => ({
    username: state.getIn(['login','username']),

});
const mapDispatch = (dispatch) => ({

})
export default connect(mapState,mapDispatch)(Header);
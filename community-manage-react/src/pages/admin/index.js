import React,{Component} from 'react';
import {connect} from 'react-redux';
import { Row, Col} from 'antd';
import Header from "../../common/header";
import Footer from "../../common/footer";
import NavLeft from "../../components/NavLeft";
import './common.less'

class Admin extends Component {
    render() {
        return (
            <Row className="container">
                <Col span="4" className="nav-left">
                    <NavLeft/>
                </Col>
                <Col span="20" className="main">
                    <Header/>
                    <Row className="content">
                        {/*<Home/>*/}
                        {this.props.children}

                    </Row>
                    <Footer/>
                </Col>
            </Row>
        );
    }

}
export default Admin;
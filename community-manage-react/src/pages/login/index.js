import React, {Component} from 'react'
import {Form, Input, Button} from 'antd';
import { Redirect } from 'react-router-dom';
import { UserOutlined, LockOutlined } from '@ant-design/icons';
import './index.less';
import {connect} from "react-redux";
import {actionCreators} from "./store";

class Login extends Component {


    render () {

        const {handleLogin}  = this.props;
        if(this.props.loginState){return <Redirect to='/admin' />}
        return (
            <div className="form">
                <section className="login-content">
                    <h2>用户登陆</h2>
                    <Form
                         className="login-form"
                        ref="loginForm"
                        initialValues={{
                            username:'admin',//默认值
                        }}
                    >
                        <Form.Item
                            name="username"
                            rules={[// 声明式验证: 直接使用别人定义好的验证规则进行验证
                                { required: true, whitespace: true, message: '用户名必须输入' },
                                { min: 4, message: '用户名至少4位' },
                                { max: 12, message: '用户名最多12位' },
                                { pattern: /^[a-zA-Z0-9_]+$/, message: '用户名必须是英文、数字或下划线组成' },
                            ]}
                        >
                            <Input
                                prefix={<UserOutlined className="site-form-item-icon" />}
                                placeholder="用户名"
                            />
                        </Form.Item>
                        <Form.Item
                            name="password"
                            rules={[
                                {
                                    required: true,
                                    message: 'Please input your Password!',
                                },
                            ]}
                        >
                            <Input
                                prefix={<LockOutlined className="site-form-item-icon" />}
                                type="password"
                                placeholder="密码"
                            />
                        </Form.Item>
                        <Form.Item>
                            <Button type="primary" htmlType="submit" className="login-form-button" onClick={()=>handleLogin(this.refs.loginForm.getFieldValue())}>
                                登录
                            </Button>
                        </Form.Item>
                    </Form>
                </section>
            </div>

        )
    }

}

const mapState = (state) => ({
    loginState: state.getIn(['login','loginState'])
});
const mapDispatch = (dispatch) => ({
    handleLogin(values){
        dispatch(actionCreators.loginRequest(values));
    }

});

export default connect(mapState,mapDispatch)(Login);

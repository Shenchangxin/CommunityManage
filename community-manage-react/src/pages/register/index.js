import React, { Component } from 'react';
import {
    Form,
    Input,
    Select,
    Button,
} from 'antd';
import './index.less';
import {connect} from "react-redux";
import { Redirect } from 'react-router-dom';
import * as actionCreators from "./store/actionCreators";

const { Option } = Select;
const formItemLayout = {
    labelCol: {
        xs: { span: 24 },
        sm: { span: 8 },
    },
    wrapperCol: {
        xs: { span: 24 },
        sm: { span: 16 },
    },
};
const tailFormItemLayout = {
    wrapperCol: {
        xs: {
            span: 24,
            offset: 0,
        },
        sm: {
            span: 16,
            offset: 8,
        },
    },
};
const prefixSelector = (
    <Form.Item name="prefix" noStyle>
        <Select style={{ width: 70 }}>
            <Option value="86">+86</Option>
            <Option value="87">+87</Option>
        </Select>
    </Form.Item>
);


class Register extends Component {

    render(){
        const {handleRegister} = this.props;
        if(this.props.registerState){return <Redirect to='/login' />}
        return(
            <div className="registerForm">
                <h2>用户注册</h2>
                <Form
                    {...formItemLayout}
                    name="register"
                    ref="registerForm"
                    scrollToFirstError
                >
                    <Form.Item
                        name="username"
                        label="账号"
                        rules={[
                            {
                                required: true,
                                message: '请输入账号!',
                            },
                        ]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="password"
                        label="密码"
                        rules={[
                            {
                                required: true,
                                message: '请输入密码!',
                            },
                        ]}
                        hasFeedback
                    >
                        <Input.Password />
                    </Form.Item>

                    <Form.Item
                        name="confirm"
                        label="确认密码"
                        dependencies={['password']}
                        hasFeedback
                        rules={[
                            {
                                required: true,
                                message: '请确认你的密码!',
                            },
                            ({ getFieldValue }) => ({
                                validator(_, value) {
                                    if (!value || getFieldValue('password') === value) {
                                        return Promise.resolve();
                                    }
                                    return Promise.reject(new Error('两次输入的密码不一致!'));
                                },
                            }),
                        ]}
                    >
                        <Input.Password />
                    </Form.Item>

                    <Form.Item
                        name="name"
                        label="昵称"
                        tooltip="你想让别人怎么称呼你?"
                        rules={[{ required: true, message: '请输入昵称!', whitespace: true }]}
                    >
                        <Input />
                    </Form.Item>

                    <Form.Item
                        name="phone"
                        label="手机号"
                        rules={[{ required: true, message: '请输入手机号码!' }]}
                    >
                        <Input addonBefore={prefixSelector} style={{ width: '100%' }} />
                    </Form.Item>

                    <Form.Item
                        name="sex"
                        label="性别"
                        hasFeedback
                        rules={[
                            {
                                required: true,
                                message: '请选择您的性别!',
                            },
                        ]}
                    >
                        <Select placeholder="请选择您的性别">
                            <Option value="男">男</Option>
                            <Option value="女">女</Option>
                        </Select>
                    </Form.Item>

                    <Form.Item
                        name="department"
                        label="所属院系"
                        hasFeedback
                        rules={[
                            {
                                required: true,
                                message: '请选择您的所属院系!',
                            },
                        ]}
                    >
                        <Select placeholder="所属院系">
                            <Option value="智能工程学院计算机与科学系">智能工程学院计算机与科学系</Option>
                            <Option value="智能工程学院软件工程系">智能工程学院软件工程系</Option>
                            <Option value="智能工程学院网络工程系">智能工程学院网络工程系</Option>
                            <Option value="智能工程学院物联网工程系">智能工程学院物联网工程系</Option>
                        </Select>
                    </Form.Item>

                    <Form.Item {...tailFormItemLayout}>
                        <Button type="primary" htmlType="submit" onClick={()=>handleRegister(this.refs.registerForm.getFieldValue())}>
                            注册
                        </Button>
                    </Form.Item>
                </Form>
            </div>
        );
    }
}
const mapState = (state) => ({
    registerState: state.getIn(['register','registerState'])
});
const mapDispatch = (dispatch) => ({
    handleRegister(values){
        console.log(values);
        dispatch(actionCreators.registerRequest(values));
    }

});

export default connect(mapState,mapDispatch)(Register);
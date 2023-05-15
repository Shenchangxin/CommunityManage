import React,{Component}from 'react';
import {Button, Card, Col, Form, Input, Modal, Row, Select, Space, Table,message, Tag} from "antd";
import {PlusSquareOutlined} from "@ant-design/icons";
import {Redirect} from "react-router-dom";
import axios from "axios";
import qs from "qs";

const { Option } = Select;
class User extends Component {

    state = {
        data: [],
        visible: false,
        loading:false,
        deleteState:false,
        updateVisible:false,
        updateState:false,
        updateId: 0
    };
    componentWillMount() {
        this.getUsers();
        console.log(this.state.data);
    }

    handleSearch= (values) => {
        axios.get('/api/admin/searchUser',{
            params: values
        }).then((res)=>{
            const result = res.data.data;
            this.setState({
                data: result,
            });
            message.success("操作成功")
        }).catch(error=>{
            message.error("查询失败"+error)
        })
    };
    getUsers(){
        axios.get('/api/admin/getUser').then((res) => {
            const result = res.data.data;
            this.setState({
                data: result,
            })
        }).catch(err => {
            message.error("获取用户列表失败"+err);
        });
    };
    handleShowModal= () => {
        this.setState({
            visible: true,
        })
    };
    handleCancel= () =>{
        this.setState({
            visible: false,
        })
    };
    handleAddUser = (value) =>{
        axios.post('/api/admin/addUser',value,{
            contentType: "application/json charset=utf-8",
        }).then((res =>{
                this.setState({
                    visible: false,
                })
        })).catch(error => {
                message.error("添加用户失败"+error);
        })
    };
    handleUpdate= (id) =>{
        this.setState({
            updateId: id,
            updateVisible: true,
        })
    };
    handleDelete= (id) =>{

        axios.delete('/api/admin/deleteUser',{
            params: {
                'id':id,
            }
        }).then((res) => {
            message.success("删除成功");
        }).catch(error => {
            message.error("删除失败"+error);
        })
    };
    handleCancelUpdate= () =>{
        this.setState({
            updateVisible: false,
        })
    };
    handleUpdateUser=(value,id)=>{
        axios.post('/api/admin/updateUser',qs.stringify({
            user: value,
            id: id,
        })).then((res) => {
            this.setState({
                updateVisible: false
            });
            message.success("操作成功");
        }).catch(error => {
            message.error("操作失败"+error);
        })
    }


    render(){

        const columns = [
            {
                title: '成员名称',
                dataIndex: 'name',
                key: 'id',
                render: text => <a>{text}</a>,
            },
            {
                title: '所属院系',
                dataIndex:'department',
                key: 'id',
                render: (department) => (
                    <Tag color={"green"} key={department}>{department}</Tag>
                ),
            },

            {
                title: '联系电话',
                dataIndex:'phone',
                key: 'id',
                render: text => <a>{text}</a>,
            },

            {
                title: '年级',
                dataIndex:'grade',
                key: 'id',
                render: text => <a>{text}</a>,
            },

            {

                title: '操作',
                key: 'id',
                render: (text,record) => (

                    <Space size="middle">
                        <Button onClick={()=> handleUpdate(record.id-1)}>修改</Button>
                        <Button onClick={()=> handleDelete(record.id)}>删除</Button>
                    </Space>

                ),
            },
        ];

        const {handleSearch,handleShowModal,handleAddUser,handleUpdate,handleCancel,handleDelete,handleCancelUpdate,handleUpdateUser} = this;
        if(this.props.deleteState){return <Redirect to="/admin/user"/>}
        return(
            <div className="userList">

                <Card style={{width: 1227}}>
                    <Form
                        name="advanced_search"
                        className="ant-advanced-search-form"
                    >
                        <Row type="flex"  align="middle" style={{marginBottom:10}}>

                            <Col>
                                <Form layout="inline"  ref="searchForm">
                                    <Form.Item label={"用户名"}  name="fields">
                                        <Input  />
                                    </Form.Item>
                                    <Form.Item style={{float:"right"}}>
                                        <Button  type="primary" onClick={()=>handleSearch(this.refs.searchForm.getFieldValue())} style={{marginRight:"10px"}}>查找</Button>
                                        <Button  type="default"  onClick={()=>handleShowModal()}><PlusSquareOutlined />添加用户</Button>

                                    </Form.Item>
                                </Form>
                            </Col>
                        </Row>
                    </Form>


                    <Table
                        columns={columns}
                        pagination={{ pageSize:6, position: [ 'bottomCenter'] }}
                        dataSource={this.state.data}
                        rowKey={(record) => {
                            return (record.id + Date.now()) //在这里加上一个时间戳就可以了
                        }}
                    />
                </Card>

                <Modal
                    visible={this.state.visible}
                    destroyOnClose={true}
                    title="添加用户"
                    onOk={()=>handleAddUser()}
                    onCancel={()=>handleCancel()}
                    footer={[
                        <Button key="back" onClick={()=>handleCancel()}>
                            取消
                        </Button>,
                        <Button key="submit" type="primary" loading={this.props.loading} onClick={()=>handleAddUser(this.refs.userForm.getFieldValue())}>
                            确定
                        </Button>,

                    ]}
                >
                    <Form
                        name="addUser"
                        ref="userForm"
                        scrollToFirstError
                    >
                        <Form.Item
                            name="username"
                            label="用户名"
                            rules={[
                                {
                                    required: true,
                                    message: '请输入所要添加的用户名!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="password"
                            label="用户密码"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写用户密码!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="name"
                            label="用户姓名"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写用户姓名!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="phone"
                            label="联系电话"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写联系电话!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="sex"
                            label="性别"
                            hasFeedback
                            rules={[
                                {
                                    required: true,
                                    message: '请选择性别!',
                                },
                            ]}
                        >
                            <Select placeholder="请选择性别">
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
                                    message: '请选择所属院系!',
                                },
                            ]}
                        >
                            <Select placeholder="请选择所属院系">
                                <Option value="智能工程学院计算机与科学系">智能工程学院计算机与科学系</Option>
                                <Option value="智能工程学院软件工程系">智能工程学院软件工程系</Option>
                                <Option value="智能工程学院网络工程系">智能工程学院网络工程系</Option>
                                <Option value="智能工程学院物联网工程系">智能工程学院物联网工程系</Option>
                            </Select>
                        </Form.Item>


                        <Form.Item
                            name="grade"
                            label="年级"
                            hasFeedback
                            rules={[
                                {
                                    required: true,
                                    message: '请选择年级!',
                                },
                            ]}
                        >
                            <Select placeholder="请选择年级">
                                <Option value="大一">大一</Option>
                                <Option value="大二">大二</Option>
                                <Option value="大三">大三</Option>
                                <Option value="大四">大四</Option>
                            </Select>
                        </Form.Item>
                    </Form>
                </Modal>

                <Modal
                    visible={this.state.updateVisible}
                    destroyOnClose={true}
                    title="修改用户"
                    onOk={()=>handleUpdateUser()}
                    onCancel={()=>handleCancelUpdate()}
                    footer={[
                        <Button key="back" onClick={()=>handleCancelUpdate()}>
                            取消
                        </Button>,
                        <Button key="submit" type="primary" loading={this.props.loading} onClick={()=>handleUpdateUser(this.refs.userForm.getFieldValue(),this.state.updateId)}>
                            确定
                        </Button>,

                    ]}
                >
                    <Form
                        name="updateUser"
                        ref="userForm"
                        scrollToFirstError
                    >
                        <Form.Item
                            name="username"
                            label="用户名"
                            rules={[
                                {
                                    required: true,
                                    message: '请输入所要添加的用户名!',
                                },
                            ]}
                        >
                            <Input defaultValue="admin"/>
                        </Form.Item>

                        <Form.Item
                            name="password"
                            label="用户密码"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写用户密码!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="name"
                            label="用户姓名"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写用户姓名!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>


                        <Form.Item
                            name="grade"
                            label="年级"
                            hasFeedback
                            rules={[
                                {
                                    required: true,
                                    message: '请选择年级!',
                                },
                            ]}
                        >
                            <Select placeholder="请选择年级" defaultValue="大一">
                                <Option value="大一">大一</Option>
                                <Option value="大二">大二</Option>
                                <Option value="大三">大三</Option>
                                <Option value="大四">大四</Option>
                            </Select>
                        </Form.Item>
                    </Form>
                </Modal>

            </div>
        )
    }
}
export default User;
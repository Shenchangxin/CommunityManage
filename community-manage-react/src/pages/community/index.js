import React, {Component} from 'react';
import {Table, Tag, Space, Form, Row, Col, Input, Button, Modal, Select, Card, message} from 'antd';
import {Redirect} from "react-router-dom";
import {PlusSquareOutlined} from "@ant-design/icons";
import axios from "axios";
import qs from "qs";



const { Option } = Select;

class Community extends Component {

    state = {
        data:[],
        getState: false,
        message: '',
        visible: false,
        loading: false,
        deleteState: false,
        updateVisible: false,
        updateState: false,
        updateId: 0

    };

    handleSearch = (values) => {
        axios.get('/api/admin/searchCommunity',{
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

    getCommunity(){
        axios.get('/api/admin/getCommunity').then((res) => {
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
    handleAddCommunity = (value) =>{
        axios.post('/api/admin/addCommunity',value,{
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

        axios.delete('/api/admin/deleteCommunity',{
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
    handleUpdateCommunity=(values,id)=>{
        console.log(id);
        axios.post('/api/admin/updateCommunity',qs.stringify({
            name: values.name,
            description: values.description,
            type: values.type,
            id: id

        })).then((res) => {
            this.setState({
                updateVisible: false
            });
            message.success("操作成功");
        }).catch(error => {
            message.error("操作失败"+error);
        })
    }


    componentWillMount() {

        this.getCommunity();
        console.log(this.state.data)
    }


    render() {

        const columns = [
            {
                title: '社团名称',
                dataIndex: 'name',
                key: 'id',
                render: text => <a>{text}</a>,
            },
            {
                title: '社团类型',
                dataIndex:'type',
                key: 'id',
                render: (type) => (
                    <Tag color={"green"} key={type}>{type.name}</Tag>
                ),
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


        const {updateId} = this.state;
        const {handleSearch,handleShowModal,handleAddCommunity,handleUpdate,handleCancel,handleDelete,handleCancelUpdate,handleUpdateCommunity} = this;
        if(this.state.deleteState){return <Redirect to="/admin/community"/>}
        return(
            <div className="communityList">

                <Card style={{width: 1240}}>
                <Form

                    name="advanced_search"
                    className="ant-advanced-search-form"
                >
                    <Row type="flex"  align="middle" style={{marginBottom:10}}>

                        <Col>
                            <Form layout="inline"  ref="searchForm">
                                <Form.Item label={"社团名"}  name="fields">
                                    <Input  />
                                </Form.Item>
                                <Form.Item style={{float:"right"}}>
                                    <Button  type="primary" onClick={()=>handleSearch(this.refs.searchForm.getFieldValue())} style={{marginRight:"10px"}}>查找</Button>
                                    <Button  type="default"  onClick={()=>handleShowModal()}><PlusSquareOutlined />添加社团</Button>

                                </Form.Item>
                            </Form>
                        </Col>
                    </Row>
                </Form>


                <Table
                    columns={columns}
                    pagination={{ pageSize:6, position: [ 'bottomCenter'] }}
                    dataSource={this.state.data}
                />
                </Card>

                <Modal
                    visible={this.state.visible}
                    destroyOnClose={true}
                    title="添加社团"
                    onOk={()=>handleAddCommunity()}
                    onCancel={()=>handleCancel()}
                    footer={[
                        <Button key="back" onClick={()=>handleCancel()}>
                            取消
                        </Button>,
                        <Button key="submit" type="primary" loading={this.state.loading} onClick={()=>handleAddCommunity(this.refs.communityForm.getFieldValue())}>
                            确定
                        </Button>,

                    ]}
                >
                    <Form
                        name="addCommunity"
                        ref="communityForm"
                        scrollToFirstError
                    >
                        <Form.Item
                            name="name"
                            label="社团名称"
                            rules={[
                                {
                                    required: true,
                                    message: '请输入所要添加的社团的名字!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="description"
                            label="社团简介"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写社团简介!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>


                        <Form.Item
                            name="type"
                            label="社团类型"
                            hasFeedback
                            rules={[
                                {
                                    required: true,
                                    message: '请选择社团类型!',
                                },
                            ]}
                        >
                            <Select placeholder="请选择社团类型">
                                <Option value="文体类">文体类</Option>
                                <Option value="艺术类">艺术类</Option>
                            </Select>
                        </Form.Item>
                    </Form>
                </Modal>

                <Modal
                    visible={this.state.updateVisible}
                    destroyOnClose={true}
                    title="修改社团"
                    onOk={()=>handleUpdateCommunity()}
                    onCancel={()=>handleCancelUpdate()}
                    footer={[
                        <Button key="back" onClick={()=>handleCancelUpdate()}>
                            取消
                        </Button>,
                        <Button key="submit" type="primary" loading={this.state.loading} onClick={()=>handleUpdateCommunity(this.refs.communityForm.getFieldValue(),updateId+1)}>
                            确定
                        </Button>,

                    ]}
                >
                    <Form
                        name="updateCommunity"
                        ref="communityForm"
                        scrollToFirstError
                    >
                        <Form.Item
                            name="name"
                            label="社团名称"
                            rules={[
                                {
                                    required: true,
                                    message: '请输入所要添加的社团的名字!',
                                },
                            ]}
                        >

                            {/*<Input defaultValue={this.props.data[updateId].name}/>*/}
                            <Input defaultValue="大学生心里社团"/>
                        </Form.Item>

                        <Form.Item
                            name="description"
                            label="社团简介"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写社团简介!',
                                },
                            ]}
                        >
                            {/*<Input defaultValue={this.props.data[updateId].description}/>*/}
                            <Input defaultValue="我是大学生心里社团"/>
                        </Form.Item>


                        <Form.Item
                            name="type"
                            label="社团类型"
                            hasFeedback
                            rules={[
                                {
                                    required: true,
                                    message: '请选择社团类型!',
                                },
                            ]}
                        >
                            <Select placeholder="请选择社团类型" defaultValue="文体类">
                                <Option value="文体类">文体类</Option>
                                <Option value="艺术类">艺术类</Option>
                                <Option value="体育类">体育类</Option>
                            </Select>
                        </Form.Item>
                    </Form>
                </Modal>

            </div>
        )
    }

}
export default Community;

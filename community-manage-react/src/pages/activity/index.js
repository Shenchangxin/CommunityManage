import React,{Component}from 'react';
import {Button, Card, Col, Form, Input, Modal, Row, Select, Space, Table,message, Tag} from "antd";
import {PlusSquareOutlined} from "@ant-design/icons";
import {Redirect} from "react-router-dom";
import axios from "axios";
import qs from "qs";

const { Option } = Select;
class Activity extends Component {

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
        this.getActivity();
        console.log(this.state.data);
    }

    handleSearch= (values) => {
        axios.get('/api/activity/searchActivity',{
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
    getActivity(){
        axios.get('/api/activity/getActivity').then((res) => {
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
    handleAddActivity = (value) =>{
        axios.post('/api/activity/addActivity',value,{
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

        axios.delete('/api/activity/deleteActivity',{
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
    handleUpdateActivity=(value,id)=>{
        axios.post('/api/activity/updateActivity',qs.stringify({
            name: value.name,
            content: value.content,
            communityName:value.communityName,
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
                title: '活动名称',
                dataIndex: 'name',
                key: 'id',
                render: text => <a>{text}</a>,
            },
            {
                title: '活动内容',
                dataIndex:'content',
                key: 'id',
                render: text => <a>{text}</a>,
            },
            {
                title: '所属社团',
                dataIndex:'community',
                key: 'id',
                render: (community) => (
                    <Tag color={"green"} key={community}>{community.name}</Tag>
                ),
            },

            {

                title: '操作',
                key: 'id',
                render: (text,record) => (

                    <Space size="middle">
                        <Button onClick={()=> handleUpdate(record.id)}>修改</Button>
                        <Button onClick={()=> handleDelete(record.id)}>删除</Button>
                    </Space>

                ),
            },
        ];

        const {handleSearch,handleShowModal,handleAddActivity,handleUpdate,handleCancel,handleDelete,handleCancelUpdate,handleUpdateActivity} = this;
        if(this.props.deleteState){return <Redirect to="/admin/activity"/>}
        return(
            <div className="activityList">

                <Card style={{width: 1227}}>
                    <Form
                        name="advanced_search"
                        className="ant-advanced-search-form"
                    >
                        <Row type="flex"  align="middle" style={{marginBottom:10}}>

                            <Col>
                                <Form layout="inline"  ref="searchForm">
                                    <Form.Item label={"活动名"}  name="fields">
                                        <Input  />
                                    </Form.Item>
                                    <Form.Item style={{float:"right"}}>
                                        <Button  type="primary" onClick={()=>handleSearch(this.refs.searchForm.getFieldValue())} style={{marginRight:"10px"}}>查找</Button>
                                        <Button  type="default"  onClick={()=>handleShowModal()}><PlusSquareOutlined />添加活动</Button>

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
                    title="添加活动"
                    onOk={()=>handleAddActivity()}
                    onCancel={()=>handleCancel()}
                    footer={[
                        <Button key="back" onClick={()=>handleCancel()}>
                            取消
                        </Button>,
                        <Button key="submit" type="primary" loading={this.props.loading} onClick={()=>handleAddActivity(this.refs.activityForm.getFieldValue())}>
                            确定
                        </Button>,

                    ]}
                >
                    <Form
                        name="addActivity"
                        ref="activityForm"
                        scrollToFirstError
                    >
                        <Form.Item
                            name="name"
                            label="活动名"
                            rules={[
                                {
                                    required: true,
                                    message: '请输入所要添加的活动名!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="content"
                            label="活动内容"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写活动内容!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="communityName"
                            label="所属社团"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写所属社团!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                    </Form>
                </Modal>

                <Modal
                    visible={this.state.updateVisible}
                    destroyOnClose={true}
                    title="修改活动"
                    onOk={()=>handleUpdateActivity()}
                    onCancel={()=>handleCancelUpdate()}
                    footer={[
                        <Button key="back" onClick={()=>handleCancelUpdate()}>
                            取消
                        </Button>,
                        <Button key="submit" type="primary" loading={this.props.loading} onClick={()=>handleUpdateActivity(this.refs.activityForm.getFieldValue(),this.state.updateId)}>
                            确定
                        </Button>,

                    ]}
                >
                    <Form
                        name="updateActivity"
                        ref="activityForm"
                        scrollToFirstError
                    >
                        <Form.Item
                            name="name"
                            label="活动名"
                            rules={[
                                {
                                    required: true,
                                    message: '请输入所要添加的活动名!',
                                },
                            ]}
                        >
                            <Input defaultValue="admin"/>
                        </Form.Item>

                        <Form.Item
                            name="content"
                            label="活动内容"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写活动内容!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                        <Form.Item
                            name="communityName"
                            label="所属社团"
                            rules={[
                                {
                                    required: true,
                                    message: '请填写社团名!',
                                },
                            ]}
                        >
                            <Input />
                        </Form.Item>

                    </Form>
                </Modal>

            </div>
        )
    }
}
export default Activity;
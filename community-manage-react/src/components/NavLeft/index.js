import React from 'react';
import {Menu } from 'antd';
import './index.less';
import {NavLink} from "react-router-dom";


export default class NavLeft extends React.Component {
    render() {
        return (
            <div>
                <div className="logo">
                    <img src="./../../../public/statics/logo.png" alt=""/>
                    <h1>大学生社团管理系统</h1>
                </div>
                <Menu
                    theme="dark"
                    defaultSelectedKeys={['1']}
                    defaultOpenKeys={['sub1']}
                    mode="inline"
                >
                    <Menu.Item key="communityManage"><NavLink to="/admin/community">社团管理</NavLink></Menu.Item>


                    <Menu.Item key="userManage"><NavLink to="/admin/user">成员管理</NavLink></Menu.Item>
                    <Menu.Item key="activityManage"><NavLink to="/admin/activity">活动管理</NavLink></Menu.Item>

                </Menu>
            </div>
        );
    }
};
export default    [
    {
        title: '首页',
        key: 'admin/home'
    },
    {
        title: '社团管理',
        key: 'admin/community',
        children: [
            {
                title: '添加社团',
                key: 'admin/community/addCommunity'
            },
            {
                title: '删除社团',
                key: 'admin/community/deleteCommunity'
            },
            {
                title: '修改社团',
                key: 'admin/community/updateCommunity'
            },
            {
                title: '查找社团',
                key: 'admin/community/findCommunity'
            },
        ]
    },
    {
        title: '用户管理',
        key: 'admin/user',
        children:[
            {
                title: '添加用户',
                key: 'admin/user/addUser'
            },
            {
                title: '删除用户',
                key: 'admin/user/deleteUser'
            },
            {
                title: '修改用户',
                key: 'admin/user/updateUser'
            },
            {
                title: '查找用户',
                key: 'admin/user/findUser'
            },
        ]
    }
];



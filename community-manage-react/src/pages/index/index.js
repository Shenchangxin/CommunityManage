import React ,{Component} from 'react';
import axios from "axios";
import {Card, message} from "antd";
import '../index/index.less';
import {Link} from "react-router-dom";

class Index extends Component {

    state = {
        activity: [],

    }
    componentWillMount() {
        this.getActivity();

    }
    getActivity =(id)=>{

        axios.get("/api/activity/getActivity").then((res)=>{
            const result = res.data.data;
            console.log(result);
            this.setState({
                activity: result
            })
            message.success("操作成功");
        }).catch( err =>{
            message.error("获取活动信息失败"+err);
        })
    }

    render() {
        return(
            <div className="cards">

                {
                    this.state.activity.map((item,index) =>{
                        console.log(item.id);
                        let id = item.id;
                        return <Card size="small" title={item.name} extra={<Link to={`/activity/${id}`}>更多</Link>} style={{ width: 300,height: 200,marginRight:20 }}>
                            <p>{item.content}</p>
                        </Card>
                    })
                }

            </div>
        )
    }
}
export default Index;
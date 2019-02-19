import React, { Component } from 'react';
import { Card, Table } from 'antd';
import Detail from '@/pages/sendMessage/components/Detail';
import { connect } from 'dva';

@connect(({sendMessage}) => ({
  sendMessage
}))
class TableModal extends Component{

  constructor(props){
    super(props);
    this.state = {
      showDetail : false,
      monthSalesTaskDetailList: [],
      title: ''
    }
  const {dispatch} = this.props;
  dispatch({
    type: 'sendMessage/fetchQuerySendInfo',
    payload: {
      type: this.props.type,
      name: this.props.name
    }
  })

  }

  toDetail = (record,text) => {
    //if(id){
      this.setState({showDetail: true, monthSalesTaskDetailList: record.monthSalesTaskDetailList, title: text });

    //}
  }

  goBack = () => {
    this.setState({showDetail: false});
  }


  render(){
/*    const dataSource = [
      { manager: '华南', totalTaskNum: 100, totalSaleNum: 80, totalRatio: '80%' }
    ]*/
    const { sendMessage } = this.props;
    const dataSource = sendMessage.data;
    
    const columns = [{
        title: '任务责任人',
        width: 100,
        dataIndex: 'manager',
        render: (text, record) => {
          return (
            <span><a href="javascript:;" onClick={() => this.toDetail(record,text)}>{text}</a></span>
          )
        }
      },
      { title: '总任务数', width: 80, dataIndex: 'totalTaskNum' },
      { title: '总销售数', width: 80, dataIndex: 'totalSaleNum' },
      { title: '任务完成率', width: 80, dataIndex: 'totalRatio' },
    ];

    let view = '';
    if(!this.state.showDetail){
     view = (
        <Table style={{textAlign: 'center'}}
        columns={columns}
        dataSource={dataSource}
        bordered
        title={this.setTitle}
        pagination={false}
       />
       )
    }else{
       view = (
        <Detail goBack={this.goBack} dataSource={this.state.monthSalesTaskDetailList} title={this.state.title}
       />
        )  
    }

    return (
      <div>
         {view}
      </div>
     
    )
  }
}
export default TableModal
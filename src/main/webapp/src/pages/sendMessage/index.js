import React, { Component } from 'react';
import { Card, Table } from 'antd';
import Detail from './components/Detail';
import { connect } from 'dva';

@connect(({ sendMessage }) => ({
  sendMessage
}))
class SendMessage extends Component {

  constructor(props) {
    super(props);
    this.state = {
      showDetail: false,
      monthSalesTaskDetailList: [],
      title: '',
      addColumns: [
        { title: '总销售数', width: 80, dataIndex: 'totalSaleNum', key: 'totalSaleNum' },
        { title: '任务完成率', width: 100, dataIndex: 'totalRatio', key: 'totalRatio' },
      ]
    }

    /*    //得到传参，根据传参取后台数据，到页面展示
        //type 类型标识
        const type = this.props.match.params.type;
        //对应的名称
        const name = this.props.match.params.name;
        //是不是只含有task任务数
        const time = this.props.match.params.time;*/
    const { type, name, time } = this.props.match.params;

    console.log(type, name);

    const { dispatch } = this.props;
    dispatch({
      type: 'sendMessage/fetchQuerySendInfo',
      payload: {
        type: type,
        name: name,
        month: time
      }
    })
  }

  setTitle = () => {
    /*    let myDate = new Date();
        let time = myDate.getFullYear() + '年' + myDate.getMonth();*/
    const time = this.props.match.params.time;
    return "我的" + time + "销售任务报表";
  }

  toDetail = (record, text) => {
    const time = this.props.match.params.time;
    //if(id){
    this.setState({ showDetail: true, monthSalesTaskDetailList: record.monthSalesTaskDetailList, title: text + "的" + time });

    //}
  }

  goBack = () => {
    this.setState({ showDetail: false });
  }

  render() {
    /*const dataSource = [
      { manager: '华南', totalTaskNum: 100, totalSaleNum: 80, totalRatio: '80%' }
    ]*/
    const { sendMessage } = this.props;
    const dataSource = sendMessage.data;
    let monthSalesTaskDetailList = [];
    if(dataSource.length !== 0){
      monthSalesTaskDetailList = dataSource[0].monthSalesTaskDetailList;
    }

    // 得到类型 sp
    const { type } = this.props.match.params;

    let columns = [{
        title: '任务责任人',
        width: 100,
        dataIndex: 'manager',
        key: 'manager',
        render: (text, record) => {
          return (
            <span><a href="javascript:;" onClick={() => this.toDetail(record,text)}>{text}</a></span>
          )
        }
      },
      { title: '总任务数', width: 80, dataIndex: 'totalTaskNum', key: 'totalTaskNum' },
    ];

    const taskType = this.props.match.params.taskType;

    /* taskType === 1  显示完成度   taskType === 0  不显示完成度*/
    if (taskType === '1') {
      columns = columns.concat(this.state.addColumns);
    }

    let view = '';
    //如果是服务商直接显示详情页面
    if (type === 'sp') {
      const { name } = this.props.match.params;
      view = (
        <Detail type={type} dataSource={monthSalesTaskDetailList} title={this.state.title} taskType={taskType}
       />
      )
    } else {
      if (!this.state.showDetail) {
        view = (
          <Table style={{textAlign: 'center'}}
        columns={columns}
        dataSource={dataSource}
        bordered
        title={this.setTitle}
        pagination={false}
       />
        )
      } else {
        view = (
          <Detail type={type} goBack={this.goBack} dataSource={this.state.monthSalesTaskDetailList} title={this.state.title} taskType={taskType}
       />
        )
      }
    }


    return (
      <Card >
        {view}
      </Card>
    )
  }
}

export default SendMessage

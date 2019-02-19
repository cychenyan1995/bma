import React, { Component } from 'react';
import { Card, Table, Button, Icon } from 'antd';

class Task extends Component {

  setTitle () {
    let desc = this.props.title;
    let myDate = new Date();
    let time = myDate.getFullYear()+'年'+myDate.getMonth();
    return desc+time+'任务报表'
  }

  render() {
    const dataSource = [{'taskManager':'冉一君',}]
    const columns = [{
        title: '任务责任人',
        width: 100,
        dataIndex: 'taskManager',
      },
      { title: '广联DVD', width: 80, dataIndex: 'tasknumDvd' },
      { title: '广联GPS', width: 80, dataIndex: 'tasknumGps' },
      { title: '嘀嘀虎智能云镜', width: 80, dataIndex: 'tasknumYunjing' },
      { title: '嘀嘀虎车联网服务激活卡-1年版', width: 80, dataIndex: 'tasknumDidihuService' },
      { title: '无线车充', width: 80, dataIndex: 'tasknumWirelessCharge' },
      { title: '纯流媒体后视镜', width: 80, dataIndex: 'tasknumRearview' },
      { title: '全功能_流媒体后视镜', width: 80, dataIndex: 'tasknumFunctionRearview' }
    ];

    return {
      <Table style={{textAlign: 'center'}} bordered={false}
            columns={columns}
            dataSource={dataSource}
            bordered
            title={this.setTitle}
            pagination={false}
          />
    }
  }
}

export default Task

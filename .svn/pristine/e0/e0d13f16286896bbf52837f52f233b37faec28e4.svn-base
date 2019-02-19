import React, { Component } from 'react';
import { Card, Table, Button, Icon } from 'antd';

class Detail extends Component {

  constructor(props) {
    super(props);
    this.state = {
      addColumns: [
        { title: '销售数', width: 80, dataIndex: 'saleNum' },
        { title: '任务完成率', width: 100, dataIndex: 'ratio' },
      ]
    }
  }

  setTitle = () => {
    const { type } = this.props;
    if (type === 'sp') {
      return '我的销售任务报表'
    }else{
      let desc = this.props.title;
      return desc + '的销售任务报表'
    }
  }

  render() {
    const { dataSource, taskType, type } = this.props;

    let columns = [{
        title: '任务类型',
        width: 100,
        dataIndex: 'taskType',
      },
      { title: '任务数', width: 80, dataIndex: 'taskNum' },
    ];

    /* taskType === 1  显示完成度   taskType === 0  不显示完成度*/
    if (taskType === '1') {
      columns = columns.concat(this.state.addColumns);
    }

    let view = '';
    // 如果是服务商 不需要返回按钮
    if (type !== 'sp') {
      view = (
        <Button onClick={this.props.goBack}>
          <Icon type="left-circle" style={{fontSize:'20px'}}>返回</Icon>
        </Button>
      )
    }

    return (
      <div>
        {view}
        <Table style={{textAlign: 'center'}} bordered={false}
          columns={columns}
          dataSource={dataSource}
          bordered
          title={this.setTitle}
          pagination={false}
        />
      </div>
    )
  }
}

export default Detail

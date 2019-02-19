import React, { Component } from 'react';
import { Card, Col, List, Row, Button, Icon, DatePicker, Divider } from 'antd';
import { connect } from "dva/index";
import TableCustom from "../../../components/Table/Table";
import SelectCustom from './Select'

@connect(({ historyDetail }) => ({
  historyDetail
}))
class Detail extends Component {
  constructor(props) {
    super(props);
    const { dispatch, spCode,area } = props;
    const param = "sum";
    dispatch({
      type: 'historyDetail/fetchDetail',
      payload: {
        spCode: spCode,
        area: area,
        currentPage: 1,
        pageSize: 10
      }
    });
    dispatch({
      type: 'historyDetail/getFilters',
      payload: {
        currentPage: 1,
        pageSize: 10
      }
    });
    dispatch({
      type: 'historyDetail/fetchChart',
      payload: {
        spCode: spCode,
        area: area,
        taskType: param
      }
    });
  }

  goBack = () => {
    const { goBack } = this.props;
    goBack();
  };

  onChange = (page, pageSize) => {
    const { dispatch, historyDetail } = this.props;
    const filters = historyDetail.filters;
    filters.spCode = this.props.spCode;
    filters.area = this.props.area;
    filters.currentPage = page;
    filters.pageSize = pageSize;
    dispatch({
      type: 'historyDetail/fetchDetail',
      payload: filters,
    });
  };

  onShowSizeChange = (current, size) => {
    //console.log(current);
    //console.log(size);
    const { dispatch, historyDetail } = this.props;
    const filters = historyDetail.filters;
    filters.spCode = this.props.spCode;
    filters.area = this.props.area;
    filters.currentPage = current;
    filters.pageSize = size;
    dispatch({
      type: 'historyDetail/fetchDetail',
      payload: filters,
    });
  };

  handleSelectChange = (value) => {
    //console.log(value);
    let param = '';
    switch(value){
      case "总销售任务完成度":param = "sum"; break;
      case "广联DVD任务完成度":param = "dvd";break;
      case "广联GPS任务完成度":param = "gps";break;
      case "嘀嘀虎智能云镜任务完成度":param = "yunjing";break;
      case "嘀嘀虎车联网服务激活卡-1年版任务完成度":param = "didihuService";break;
      case "无线车充任务完成度":param = "wirelessCharge";break;
      case "纯流媒体后视镜任务完成度":param = "rearview";break;
      case "全功能_流媒体后视镜任务完成度数":param = "functionRearview";break;
      default:param = "sum"
    }
    //console.log(param);
    const { dispatch, historyDetail } = this.props;
    const filters = historyDetail.filters;
    filters.spCode = this.props.spCode;
    filters.area = this.props.area;
    dispatch({
      type: 'historyDetail/fetchChart',
      payload: {
        spCode: filters.spCode,
        area: filters.area,
        taskType: param
      }
    })
  }



  render() {
    const { historyDetail } = this.props;
    const { detail, list } = historyDetail;

    const columns = [
      { title: '任务期数', width: 100, key: 'month', dataIndex: 'month', fixed: 'left' },
      { title: '广联DVD销售数', width: 140, dataIndex: 'salesGlDvd', key: 'salesGlDvd' },
      { title: '广联DVD完成率', width: 140, key: 'salesGlDvdRate', dataIndex: 'salesGlDvdRate' },
      { title: '广联GPS销售数', width: 140, dataIndex: 'salesGlGps', key: 'salesGlGps' },
      { title: '广联GPS完成率', width: 140, dataIndex: 'salesGlGpsRate', key: 'salesGlGpsRate' },
      { title: '嘀嘀虎智能云镜销售数', width: 130, dataIndex: 'salesYunjing', key: 'salesYunjing' },
      { title: '嘀嘀虎智能云镜完成率', width: 130, dataIndex: 'salesYunjingRate', key: 'salesYunjingRate' },
      { title: '嘀嘀虎车联网服务激活卡-1年版销售数', width: 170, dataIndex: 'salesDidihuService', key: 'salesDidihuService' },
      { title: '嘀嘀虎车联网服务激活卡-1年版完成率', width: 170, dataIndex: 'salesDidihuServiceRate', key: 'salesDidihuServiceRate' },
      { title: '无线车充销售数', width: 130, dataIndex: 'salesWirelessCharge', key: 'salesWirelessCharge' },
      { title: '无线车充完成率', width: 130, dataIndex: 'salesWirelessChargeRate', key: 'salesWirelessChargeRate' },
      { title: '纯流媒体后视镜销售数', width: 130, dataIndex: 'salesRearview', key: 'salesRearview' },
      { title: '纯流媒体后视镜完成率', width: 130, dataIndex: 'salesRearviewRate', key: 'salesRearviewRate' },
      { title: '全功能_流媒体后视镜销售数', width: 140, dataIndex: 'salesFunctionRearview', key: 'salesFunctionRearview' },
      { title: '全功能_流媒体后视镜完成率', width: 140, dataIndex: 'salesFunctionRearviewRate', key: 'salesFunctionRearviewRate' }
    ];

    let data = '';
    let data1 = '';
    if (detail) {
      data = [
        `商户名称：${detail.spName}`,
        `区域：${detail.area}`,
        `一级责任人：${detail.regionalManagerLeader}`,
        `二级责任人：${detail.regionalManager}`
      ];
      data1 = [
        `业务类型：${detail.bizType}`
      ];
    }

    // 图表数据
    const chartData = {
      lineData: historyDetail.lineData,
      barData1: historyDetail.barData1,
      barData2: historyDetail.barData2,
      xAxisData: historyDetail.xAxisData
    }

    return (
      <div style={{textAlign: 'left'}}>
        <Button onClick={this.goBack}>
          <Icon type="left-circle" style={{fontSize:'20px'}}>返回</Icon>
        </Button>
        <Divider orientation="left">
          <strong>商户销售分析</strong>
        </Divider>
        <Card bordered={false}>
          <Row>
            <Col span={12}>
              <List
                split={false}
                dataSource={data}
                renderItem={item => (<List.Item>{item}</List.Item>)}
              />
            </Col>
            <Col span={12}>
              <List
                split={false}
                dataSource={data1}
                renderItem={item => (<List.Item>{item}</List.Item>)}
              />
            </Col>
          </Row>
          <TableCustom
            columns={columns}
            scroll={{x: 2000}}
            dataSource={historyDetail.list}
            total={historyDetail.total}
            onChange={this.onChange}
            onShowSizeChange={this.onShowSizeChange}
            onVisibleChange={this.onVisibleChange}
            showSearch={historyDetail.showSearch}
            page={historyDetail.currentPage}
          />
          <SelectCustom
            data={chartData}
            onchange={this.handleSelectChange}
          />
        </Card>
      </div>
    );
  }
}

export default Detail;

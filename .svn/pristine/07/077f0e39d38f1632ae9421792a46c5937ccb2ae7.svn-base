import React, { Component } from 'react';
import { Card, Col, List, Row, Button, Icon, DatePicker, Divider } from 'antd';
import { connect } from "dva/index";
import TableCustom from "@/components/Table/Table";
import SelectCustom from '@/pages/salesHistory/components/Select';
import { Link } from 'dva/router';

@connect(({ detailWeb }) => ({
  detailWeb
}))
class Detail extends Component {
  constructor(props) {
    super(props);
    const { dispatch } = props;
    const { spCode,area } = this.props.location.query.record;
    const param = "sum";
    dispatch({
      type: 'detailWeb/fetchDetail',
      payload: {
        spCode: spCode,
        area: area,
        currentPage: 1,
        pageSize: 10
      }
    });
    dispatch({
      type: 'detailWeb/getFilters',
      payload: {
        currentPage: 1,
        pageSize: 10
      }
    });
    dispatch({
      type: 'detailWeb/fetchChart',
      payload: {
        spCode: spCode,
        area: area,
        taskType: param
      }
    });
  }

  /*  goBack = () => {
      const type = this.props.location.query.type;
      console.log(type)
      goBack();
    };*/

  onChange = (page, pageSize) => {
    const { dispatch, detailWeb } = this.props;
    const filters = detailWeb.filters;
    const { spCode,area } = this.props.location.query.record;
    filters.spCode = spCode;
    filters.area = area;
    filters.currentPage = page;
    filters.pageSize = pageSize;
    dispatch({
      type: 'detailWeb/fetchDetail',
      payload: filters,
    });
  };

  onShowSizeChange = (current, size) => {
    //console.log(current);
    //console.log(size);
    const { dispatch, detail } = this.props;
    const filters = detailWeb.filters;
    //filters.spCode = this.props.location.query.record.spCode;
    const { spCode,area } = this.props.location.query.record;
    filters.spCode = spCode;
    filters.area = area;
    filters.currentPage = current;
    filters.pageSize = size;
    dispatch({
      type: 'detailWeb/fetchDetail',
      payload: filters,
    });
  };

  handleSelectChange = (value) => {
    //console.log(value);
    let param = '';
    switch (value) {
      case "总销售任务完成度":
        param = "sum";
        break;
      case "广联DVD任务完成度":
        param = "dvd";
        break;
      case "广联GPS任务完成度":
        param = "gps";
        break;
      case "嘀嘀虎智能云镜任务完成度":
        param = "yunjing";
        break;
      case "嘀嘀虎车联网服务激活卡-1年版任务完成度":
        param = "didihuService";
        break;
      case "无线车充任务完成度":
        param = "wirelessCharge";
        break;
      case "纯流媒体后视镜任务完成度":
        param = "rearview";
        break;
      case "全功能_流媒体后视镜任务完成度数":
        param = "functionRearview";
        break;
      default:
        param = "sum"
    }
    //console.log(param);
    const { dispatch, detailWeb } = this.props;
    const filters = detailWeb.filters;
    //filters.spCode = this.props.location.query.record.spCode;
    const { spCode,area } = this.props.location.query.record;
    filters.spCode = spCode;
    filters.area = area;
    dispatch({
      type: 'detailWeb/fetchChart',
      payload: {
        spCode: filters.spCode,
        area: filters.area,
        taskType: param
      }
    })
  }



  render() {
    const { detailWeb } = this.props;
    const { detail, list } = detailWeb;

    const {type, record} = this.props.location.query;
    console.log(type)

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
    if (record) {
      data = [
        `商户名称：${record.spName === null ? '' : record.spName}`,
        `区域：${record.area === null ? '' : record.area}`,
        `一级责任人：${record.regionalManagerLeader === null ? '' : record.regionalManagerLeader}`,
        `二级责任人：${record.regionalManager === null ? '' : record.regionalManager}` 
      ];
      data1 = [
        `业务类型：${record.bizType === null ? '' : record.bizType}`
      ];
    }

    // 图表数据
    const chartData = {
      lineData: detailWeb.lineData,
      barData1: detailWeb.barData1,
      barData2: detailWeb.barData2,
      xAxisData: detailWeb.xAxisData
    }

    return (
      <div style={{textAlign: 'left'}}>
        <Link to={type === 'task' ? '/base/salesTask' : '/base/salesHistory'}>
          <Button>{/* onClick={this.goBack}  to="/base/salesTask"*/}
            <Icon type="left-circle" style={{fontSize:'20px'}}>返回</Icon>
          </Button>
        </Link>
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
            dataSource={detailWeb.list}
            total={detailWeb.total}
            onChange={this.onChange}
            onShowSizeChange={this.onShowSizeChange}
            onVisibleChange={this.onVisibleChange}
            showSearch={detailWeb.showSearch}
            page={detailWeb.currentPage}
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

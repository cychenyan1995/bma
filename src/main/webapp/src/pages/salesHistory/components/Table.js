import React, { Component } from 'react';
// import Pie from '@/components/Chart/Pie/NormalPie';
import { connect } from 'dva';
import { Row, Col, DatePicker, Button, Card } from 'antd';
import moment from 'moment';
import { Link } from 'dva/router';
import TableCustom from "../../../components/Table/Table";
import FormCustom from "./Form";
import SelectCustom from './Select'


@connect(({ historyTable }) => ({
  historyTable, // namespace action
}))
class HistoryTable extends Component {
  constructor(props) {
    super(props);
    const { dispatch, historyTable } = this.props;
    const filters = [];
    filters.month = moment().format("YYYY-MM");
    filters.currentPage = 1;
    filters.pageSize = 10;
    dispatch({
      type: 'historyTable/fetchList',
      payload: filters
    });
    dispatch({
      type: 'historyTable/getFilters',
      payload: filters
    });

    const param = "sum";
    filters.taskType = "sum";
    dispatch({
      type: 'historyTable/fetchChange',
      payload: {
        taskType: param
      }
    })
  }

  onChange = (page, pageSize) => {
    const { dispatch, historyTable } = this.props;
    const filters = historyTable.filters;
    filters.currentPage = page;
    filters.pageSize = pageSize;
    dispatch({
      type: 'historyTable/fetchList',
      payload: filters,
    });
  };

  onShowSizeChange = (current, size) => {
    const { dispatch, historyTable } = this.props;
    const filters = historyTable.filters;
    filters.currentPage = current;
    filters.pageSize = size;
    dispatch({
      type: 'historyTable/fetchList',
      payload: filters,
    });
  };

  onVisibleChange = (visible) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'historyTable/showSearch',
      payload: visible,
    });
  };

  handleSubmit = (form) => {
    console.log(form);
    const param = form;

    param.pageSize = 10;
    param.currentPage = 1;
    const { dispatch, historyTable } = this.props;
    const filters = historyTable.filters;
    param.month = filters.month;
    param.taskType = filters.taskType;
    param.pageSize = filters.pageSize;
    param.taskType = filters.taskType;
    dispatch({
      type: 'historyTable/fetchList',
      payload: param
    });
    dispatch({
      type: 'historyTable/getFilters',
      payload: param
    });
    dispatch({
      type: 'historyTable/search',
      payload: form,
    });
    dispatch({
      type: 'historyTable/showSearch',
      payload: false,
    });
    dispatch({
      type: 'historyTable/fetchChange',
      payload: param
    })
    // 查询完之后将form表单置空
    // this.formRef.props.form.resetFields();
  };

  handleConfirmSubmit = (rule, value, callback) => {
    const { handleConfirmSubmit } = this.props
    handleConfirmSubmit(rule, value, callback);
  };

  toDetail = (text) => {
    const { toDetail } = this.props;
    toDetail(text);
  };

  onChangeMonth = (date, dateString) => {
    const { dispatch, historyTable } = this.props;
    const filters = historyTable.filters;
    filters.month = dateString;
    dispatch({
      type: 'historyTable/fetchList',
      payload: filters
    });
    dispatch({
      type: 'historyTable/getFilters',
      payload: filters
    });
  };

  // 导出
  exportByMonth = () => {
    const { dispatch, historyTable } = this.props;
    dispatch({
      type: 'historyTable/fetchListByMonth',
      payload: historyTable.filters
    });
  };

    handleSelectChange = (value) => {
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
    const { dispatch, historyTable  } = this.props;
    const filters = historyTable.filters;
    filters.taskType = param;
    dispatch({
      type: 'historyTable/fetchChange',
      payload: filters
    })
  }

  render() {
    const { historyTable, dispatch } = this.props;

    const columns = [
      { title: '商户ID', width: 80, dataIndex: 'spCode', key: 'spCode', fixed: 'left' },
      {
        title: '商户名称',
        width: 250,
        dataIndex: 'spName',
        key: 'spName',
        fixed: 'left',
        render: (text, record) => /* <a onClick={this.toDetail.bind(this,record.spCode)}>{text}</a> */
          <Link to={{pathname:'/base/detail', query:{ record }}}>{text}</Link>
      },
      { title: '区域', width: 110, dataIndex: 'area', key: 'area' },
      { title: '区域经理', width: 150, dataIndex: 'regionalManager', key: 'regionalManager' },
      { title: '业务类型', width: 110, dataIndex: 'bizType', key: 'bizType' },
      { title: '大区经理', width: 110, dataIndex: 'regionalManagerLeader', key: 'regionalManagerLeader' },
      { title: '广联DVD销售数', width: 140, dataIndex: 'salesGlDvd', key: 'salesGlDvd' },
      { title: '广联DVD完成率', width: 140, key: 'salesGlDvdRate', dataIndex: 'salesGlDvdRate' },
      { title: '广联GPS销售数', width: 140, dataIndex: 'salesGlGps', key: 'salesGlGps' },
      { title: '广联GPS完成率', width: 140, dataIndex: 'salesGlGpsRate', key: 'salesGlGpsRate' },
      { title: '嘀嘀虎智能云镜销售数', width: 170, dataIndex: 'salesYunjing', key: 'salesYunjing' },
      { title: '嘀嘀虎智能云镜完成率', width: 170, dataIndex: 'salesYunjingRate', key: 'salesYunjingRate' },
      { title: '嘀嘀虎车联网服务激活卡-1年版销售数', width: 150, dataIndex: 'salesDidihuService', key: 'salesDidihuService' },
      { title: '嘀嘀虎车联网服务激活卡-1年版完成率', width: 150, dataIndex: 'salesDidihuServiceRate', key: 'salesDidihuServiceRate' },
      { title: '无线车充销售数', width: 120, dataIndex: 'salesWirelessCharge', key: 'salesWirelessCharge' },
      { title: '无线车充完成率', width: 120, dataIndex: 'salesWirelessChargeRate', key: 'salesWirelessChargeRate' },
      { title: '纯流媒体后视镜销售数', width: 120, dataIndex: 'salesRearview', key: 'salesRearview' },
      { title: '纯流媒体后视镜完成率', width: 120, dataIndex: 'salesRearviewRate', key: 'salesRearviewRate' },
      { title: '全功能_流媒体后视镜销售数', width: 120, dataIndex: 'salesFunctionRearview', key: 'salesFunctionRearview' },
      { title: '全功能_流媒体后视镜完成率', width: 120, dataIndex: 'salesFunctionRearviewRate', key: 'salesFunctionRearviewRate' },
    ];
    const { MonthPicker } = DatePicker;
    const monthFormat = 'YYYY-MM';

    // 下载地址
    const url = historyTable.MonthlyExport;
    if (url) {
    const evt = document.createEvent("MouseEvents");
    evt.initEvent("click",true,true);
    const address = document.createElement("a");
    address.href = url;
    address.dispatchEvent(evt);
    // address.click();
    dispatch({
      type: 'historyTable/clearUrl',
      payload: ''
    });
    }
    // 获取当前月份
    // let nowDate = new Date();
    // let month = nowDate.getMonth() + 1
    // let time = nowDate.getFullYear() + '-' + month;
    const time = new Date();

    // chart
    // 图表数据
    const chartData = {
      lineData: historyTable.lineData,
      barData1: historyTable.barData1,
      barData2: historyTable.barData2,
      xAxisData: historyTable.xAxisData
    }
    return (
      <Card>
        <Row>
          <Col span={24}>
            <TableCustom
              columns={columns}
              dataSource={historyTable.list}
              total={historyTable.total}
              scroll={{x: 2725,y: 450}}
              onChange={this.onChange}
              onShowSizeChange={this.onShowSizeChange}
              onVisibleChange={this.onVisibleChange}
              showSearch={historyTable.showSearch}
              page={historyTable.currentPage}
              searchForm={
                <FormCustom
                  handleSubmit={this.handleSubmit}
                  handleConfirmSubmit={this.handleConfirmSubmit}
                  wrappedComponentRef={(form) => this.formRef = form}
                />
              }
              searchRight={
                <div>
                  <MonthPicker defaultValue={moment(time, monthFormat)} format={monthFormat} onChange={this.onChangeMonth} />
                  <Button type="primary" icon="download" style={{ marginLeft: 8 }} onClick={this.exportByMonth}>导出数据</Button>
                </div>
              }
            />
          </Col>
        </Row>
        <Row>
          <SelectCustom
            data={chartData}
            onchange={this.handleSelectChange}
          />
        </Row>
      </Card>
    );
  }
}

export default HistoryTable

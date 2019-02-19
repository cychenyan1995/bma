import React, { Component } from 'react';
import { Card, Row, Col, Form, Select } from 'antd';
import LineBarChart from '@/components/LineBar/LineBarChart';

const FormItem = Form.Item;
const Option = Select.Option;

class SelectCustom extends Component {

  handleSelectChange = (value) => {
    const handleSelectChange = this.props.onchange;
    handleSelectChange(value);
  }

  render() {
    const { data } = this.props;

    const chartBasicInfo = {
      title: '销售历史任务完成度图表',
      legendData: ['任务完成率', '任务完成数', '任务计划数'],
      lineName: '任务完成率',
      barName1: '任务完成数',
      barName2: '任务计划数',
      unitLeft: '%',
      unitRight: '台'
    };
    const taskOptions = ['总销售任务完成度','广联DVD任务完成度', '广联GPS任务完成度', '嘀嘀虎智能云镜任务完成度', '嘀嘀虎车联网服务激活卡-1年版任务完成度', '无线车充任务完成度', '纯流媒体后视镜任务完成度', '全功能_流媒体后视镜任务完成度数'];

    const chartData = {
      ...data,
      ...chartBasicInfo,
      taskOptions
    }

    return (
      <Card>
        <Form>
          <Row>
            <Col span={12}>
              <Form.Item label='任务数类型' labelCol={{ span: 5 }} wrapperCol={{ span: 12 }}>
                <Select style={{ width: '100%' }} placeholder="总销售任务完成度" showSearch onChange={this.handleSelectChange} filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0}>
                  {
                    (taskOptions === undefined ? []:taskOptions).map((item) => <Option value={item} key={item}>{item}</Option>)
                  }
                </Select>
              </Form.Item>
            </Col>
          </Row>

          <Row>
            <Col>
              <LineBarChart data={chartData} />
            </Col>
          </Row>
        </Form>

      </Card>

    )
  }
}


export default SelectCustom

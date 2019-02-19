import React, {Component} from 'react';
import {Row, Col, Button, Form, Input, Select} from 'antd';
import { connect } from 'dva/index';

const Option = Select.Option;

@connect(({historyForm}) => ({
  historyForm
}))
class FormCustom extends Component {

  constructor(props) {
    super(props);
    const {dispatch} = props;
    dispatch({
      type: 'historyForm/fetchAreaOptions',
      payload: {},
    });
    dispatch({
      type: 'historyForm/fetchLeaderOptions',
      payload: {},
    });
  }

  handleSubmit = (e) => {
    e.preventDefault();
    const {form,handleSubmit} = this.props;
    form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        handleSubmit(values);
      }
    });
  }

  handleReset = () => {
    const {form} = this.props;
    form.resetFields();
  }

  filter = (inputValue, path) => (
    path.some(option => (option.label).toLowerCase().indexOf(inputValue.toLowerCase()) > -1)
  );


  render() {
    const { form, historyForm, areaOptions, leaderOptions } = this.props;
    const { getFieldDecorator } = form;

    return (
      <Form
        className="ant-advanced-search-form"
        onSubmit={this.handleSubmit}
      >
        <Row gutter={12}>
          <Col span={12}>
            <Form.Item label='区域经理'>
              {getFieldDecorator('regionalManager', {
                rules: []
              })(
                <Input placeholder="请输入区域经理" />
              )}
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item label='大区经理'>
              {getFieldDecorator('regionalManagerLeader', {
              })(
                /* <Select placeholder="请选择" showSearch filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0}>
                  {
                    (historyForm.leaderOptions === undefined ? []:historyForm.leaderOptions).map((item) => <Option value={item.regionalManagerLeader} key={item.regionalManagerLeader}>{item.regionalManagerLeader}</Option>)
                  }
                </Select> */
                <Input placeholder="请输入大区经理" />
              )}
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={12}>
          <Col span={12}>
            <Form.Item label='区域'>
              {getFieldDecorator('area', {
              })(
                <Select placeholder="请选择" showSearch filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0}>
                  {
                    (historyForm.areaOptions === undefined ? []:historyForm.areaOptions).map((item) => <Option value={item.area} key={item.area}>{item.area}</Option>)
                  }
                </Select>
              )}
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item label='业务类型'>
              {getFieldDecorator('bizType', {
              })(
                <Select
                  showSearch
                  placeholder="请选择"
                  optionFilterProp="children"
                  filterOption={(input, option) => option.props.children.toLowerCase().indexOf(input.toLowerCase()) >= 0}
                >
                  <Select.Option value="广联">广联</Select.Option>
                  <Select.Option value="趣融">趣融</Select.Option>
                </Select>
              )}
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={12}>
          <Col span={24}>
            <Form.Item label='商户名称'>
              {getFieldDecorator('spName', {
                rules: []
              })(
                <Input placeholder="请输入商户名称" />
              )}
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <div style={{ textAlign: 'right' }}>
              <Button type="primary" htmlType="submit">搜索</Button>
              <Button style={{ marginLeft: 8 }} onClick={this.handleReset}>
                重置
              </Button>
            </div>
          </Col>
        </Row>
      </Form>
    );
  }
}

export default Form.create()(FormCustom);

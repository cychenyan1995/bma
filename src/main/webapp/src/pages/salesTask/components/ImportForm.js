import React, { Component } from 'react';
import { Row, Col, Button, Form, Upload, Icon, message, Modal, Tag } from 'antd';
import reqwest from 'reqwest';

class ImportForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      fileList: [],
      uploading: false,
    }
  }

  normFile = (e) => {
    // console.log('Upload event:', e);
    if (Array.isArray(e)) {
      return e;
    }
    return e && e.fileList;
  };

  // 上传调后台
  handleUpload = () => {
    const { fileList } = this.state;
    const file = fileList[0];
    const formData = new FormData();
    formData.append('file', fileList[0]);
    this.setState({
      uploading: true
    });
    reqwest({
       //url: 'http://localhost:8088/gldb-admin-bma/salesTask/importSalesTask',
      url: '/gldb-admin-bma/salesTask/importSalesTask',
      method: 'post',
      processData: false,
      data: formData,
      success: (res) => {
        // console.log(res);
        if (res.data[0].isImported === 0) {
          message.success('upload successfully.');
        } else if (res.data[0].isImported === 1) {
          message.error('上传文件不为.xls或.xlsx格式');
        } else if (res.data[0].isImported === 2) {
          message.error('导入Excel数据失败');
        } else if (res.data[0].isImported === 3) {
          message.error('导入的Excel表数据为空');
        } else if (res.data[0].isImported === 4) {
          // message.error('存在任务数为负数或小数或空数据，请修改');
          Modal.warning({
            width:'730px',
            title: '存在导入数据有错误，请修改',
            content: (
              <div>
                {res.data[0].failList.map((item) =>
                  <Tag style={{marginTop:"10px",fontSize:"15px"}} color="volcano">{item.result.spName || '错误信息'} : {item.errorMsg}</Tag>
                )}
              </div>
            ),
            okText: '知道了'
          });
        }
        this.setState({
          fileList: [],
          uploading: false
        });
        const { onOk } = this.props;
        onOk();
        // setFieldsValue  修改formItem對應的值upload
        this.props.form.setFieldsValue({
          upload: [],
        });
      },
      error: () => {
        this.setState({
          fileList: [],
          uploading: false
        });
        this.props.form.setFieldsValue({
          upload: [],
        });
        message.error('upload failed.');
      },
    });

  }

  cancelUpload = () => {
    this.setState({
      fileList: [],
      uploading: false
    });
    this.props.form.setFieldsValue({
      upload: [],
    });
    const { onCancel } = this.props;
    onCancel();
  }

  render() {
    const FormItem = Form.Item;
    const { uploading, fileList } = this.state;

    const formItemLayout = {
      labelCol: { span: 5 },
      wrapperCol: { span: 14 },
    };

    const { form } = this.props;
    const { getFieldDecorator } = form;
    const upload = {
      name: 'file',
      // action   不需要点击按钮就直接调后台
      /* action: 'http://localhost:8088/gldb-admin-bma/salesTask/importSalesTask', */
      // action: '/gldb-admin-bma/salesTask/importSalesTask',
      headers: {
        authorization: 'authorization-text',
      },
      // onChange: (info) => {
      //   if (info.file.status !== 'uploading') {
      //     console.log(info.file, info.fileList);
      //   }
      //   if (info.file.status === 'done') {
      //     message.success(`${info.file.name} 文件上传成功`);
      //     this.setState({fileList:info.fileList})
      //   } else if (info.file.status === 'error') {
      //     message.error(`${info.file.name} 文件上传失败.`);
      //   }
      // },
      onRemove: (file) => {
        this.setState((state) => {
          const index = state.fileList.indexOf(file);
          const newFileList = state.fileList.slice();
          newFileList.splice(index, 1);
          return {
            fileList: newFileList,
          };
        });
      },
      beforeUpload: (file) => {
        this.setState(state => ({
          // fileList: [...state.fileList, file],
          fileList: [file],
        }));
        this.props.form.setFieldsValue({
          upload: [file],
        });

        return false;
      },
      fileList
    };

    return (
      // 文件上传   给form添加method="post" encType="multipart/form-data"
      <Form className="ant-advanced-search-form" method="post" encType="multipart/form-data">
        <Row>
          <Col span={24}>
            <FormItem label="导入" {... formItemLayout}>
              {getFieldDecorator('upload',{
                valuePropName: 'fileList',
                getValueFromEvent: this.normFile,
                rules: [
                  { required: true, message: '请选择上传文件!' },
                ],
              })(
                <Upload {...upload}>
                  <Button style={{marginLeft:'20px'}} disabled={fileList.length === 1}>
                    <Icon type="upload" /> 选择文件
                  </Button>
                </Upload>
              )}
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col offset={5}>
            <Button
              type="primary"
              loading={uploading}
              htmlType="submit"
              style={{marginRight:'20px'}}
              onClick={this.handleUpload}
              disabled={fileList.length === 0}
            > {uploading ? '加载中' : '确认导入' }
            </Button>
            <Button type="primary" onClick={this.cancelUpload}>取消导入</Button>
          </Col>
        </Row>
      </Form>
    )
  }
}

export default Form.create()(ImportForm)

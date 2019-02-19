import PageHeaderWrapper from '@/components/PageHeaderWrapper';
import React, { Component } from 'react';
import { Row, Col, Button, Card, Table, Input, InputNumber, Popconfirm, Form, Modal, Upload, Icon } from 'antd';
import TableCustom from '@/components/Table/Table';
import { connect } from 'dva';
import ImportForm from './components/ImportForm'
import EditableCell from './components/EditableCell';
import { EditableContext, EditableRow, EditableFormRow } from './components/EditableCell';
import { Link } from 'dva/router';
import TableModal from './components/TableModal';

const FormItem = Form.Item;

@connect(({ salesTask }) => ({
  salesTask
}))
class SalesTask extends Component {

  constructor(props) {
    super(props);
    //初始化表格title(含操作列，列表中含有操作方法)
    let baseColunm = [
      { title: '商户ID', width: 150, dataIndex: 'spCode', fixed: 'left' },
      {
        title: '商户名称',
        width: 300,
        dataIndex: 'spName',
        render: (text, record) => {
          return (
            //<span><a 
            //href="javascript:;"
            //onClick={() => this.toSpDetail(record.spCode)}>{text}</a></span>
            //,query:{goBack: this.detailGoBack}
            <Link to={{pathname:'/base/detail', query:{type:'task', record: record}}}>{text}</Link>
          )
        },
        fixed: 'left'
      },
      { title: '区域', width: 150, dataIndex: 'area' },
      {
        title: '大区经理',
        width: 150,
        dataIndex: 'regionalManagerLeader',
        /*        render: (text, record) => {
                  return (
                    //<Link to="/send/sendMessage/leader/{text}">{text}</Link>
                    <span><a 
                   href="javascript:;"
                   onClick={() => this.toSendInfo(record,'leader')}>{text}</a></span>
                  )
                }*/
      },
      {
        title: '区域经理',
        width: 150,
        dataIndex: 'regionalManager',
        /*        render: (text, record) => {
                  return (
                    // <Link to="/send/sendMessage/manager/{text}">{text}</Link>
                    <span><a 
                   href="javascript:;"
                   onClick={() => this.toSendInfo(record,'manager')}>{text}</a></span>
                  )
                }
        */
      },
      { title: '业务类型', width: 150, dataIndex: 'bizType' },
      { title: '任务期数', width: 150, dataIndex: 'month' }, //////
      { title: '广联DVD任务数', width: 150, dataIndex: 'tasknumDvd', editable: true },
      { title: '广联GPS任务数', width: 150, dataIndex: 'tasknumGps', editable: true },
      { title: '嘀嘀虎智能云镜任务数', width: 150, dataIndex: 'tasknumYunjing', editable: true },
      { title: '嘀嘀虎车联网服务激活卡-1年版任务数', width: 150, dataIndex: 'tasknumDidihuService', editable: true },
      { title: '无线车充任务数', width: 150, dataIndex: 'tasknumWirelessCharge', editable: true },
      { title: '纯流媒体后视镜任务数', width: 150, dataIndex: 'tasknumRearview', editable: true },
      { title: '全功能_流媒体后视镜任务数', width: 150, dataIndex: 'tasknumFunctionRearview', editable: true },

    ];

    //
    const operationCol = {
      title: '操作',
      width: 150,
      dataIndex: 'operation',
      render: (text, record) => {
        //根据editable判断状态
        const editable = this.isEditing(record);
        return (
          <div>
              {editable ? (
                <span>
                  <EditableContext.Consumer>
                    {form => (
                      <a
                        href="javascript:;"
                        onClick={() => this.save(form, record.id)}
                        style={{ marginRight: 8 }}
                      >
                        保存
                      </a>
                    )}
                  </EditableContext.Consumer>
                  <Popconfirm
                    title="确定取消?"
                    onConfirm={() => this.cancel(record.id)}
                  >
                    <a>取消</a>
                  </Popconfirm>
                </span>
              ) : (
                <a onClick={() => this.edit(record.id)}>编辑</a>
              )}
            </div>
        );
      }
    };
    //发布任务控制按钮sendMessageBtn
    this.state = {
      columns: baseColunm,
      operationCol: operationCol,
      editingKey: '',
      importVisible: false,
      detailVisible: false,
      TableVisible: false,
      detailSpVisible: false,
      page: 1,
      pageSize: 10,
    };

    const { dispatch, salesTask } = props;
    dispatch({
      type: 'salesTask/fetchTaskList',
      payload: {
        currentPage: this.state.page,
        pageSize: this.state.pageSize
      }
    });
  };

  //推送列表
  toSendInfo = (record, type) => {
    let name = '';
    switch (type) {
      case 'leader':
        name = record.regionalManagerLeader;
        break;

      case 'manager':
        name = record.regionalManager;
        break;

    };
    this.setState({ TableVisible: true, type: type, name: name });
  }

  //导入
  importTask = () => {

    //表格列数问题
    /*    let columns = this.state.columns;
        if (columns.length === 14) {
          columns.push(this.state.operationCol);
          this.setState({ columns: columns });
        }*/
    //弹框
    this.setState({ importVisible: true });

  };

  //发布任务
  sendMessage = () => {
    //取出原数据
    const { salesTask } = this.props;
    if (salesTask.list.length === 0) {
      Modal.warning({
        title: '提示信息',
        content: '本月还没有导入任务数，不能发布',
      });
    } else {
      const confirm = Modal.confirm;
      const that = this;
      confirm({
        title: '是否确定发布任务?',
        content: '发布任务后不能修改任务数',
        onOk() {
          let baseColunm = that.state.columns;
          const { dispatch } = that.props;
          dispatch({
            type: 'salesTask/fetchSendMessage',
            payload: {}
            //后台推送接口
          })
          return new Promise((resolve, reject) => {
            setTimeout(resolve, 1000);
          }).catch(() => console.log('Oops errors!'));
        },
        onCancel() {},
      });
    }

  }
  // 
  onChange = (page, pageSize) => {
    const { dispatch, salesTask } = this.props;
    dispatch({
      type: 'salesTask/fetchTaskList',
      payload: {
        currentPage: page,
        pageSize: pageSize
      }
    });
    //修改page和pageSize
    this.setState({ page: page, pageSize: pageSize });
  };

  onShowSizeChange = (page, pageSize) => {
    const { dispatch, salesTask } = this.props;
    dispatch({
      type: 'salesTask/fetchTaskList',
      payload: {
        currentPage: page,
        pageSize: pageSize
      }
    });
    //修改page和pageSize
    this.setState({ page: page, pageSize: pageSize });
  }

  //编辑
  isEditing = (record) => {
    //editingKey初始化为'' 初始化的状态都不是编辑状态
    return record.id === this.state.editingKey;
  };
  //确认编辑
  edit(id) {
    this.setState({ editingKey: id });
  }
  //保存
  save(form, id) {
    form.validateFields((error, row) => {
      if (error) {
        return;
      }

      //取出原数据
      const { dispatch, salesTask } = this.props;

      //数据全部修改为0时，给出提示
      const tasknumDidihuService = parseInt(row.tasknumDidihuService);
      const tasknumDvd = parseInt(row.tasknumDvd);
      const tasknumFunctionRearview = parseInt(row.tasknumFunctionRearview);
      const tasknumGps = parseInt(row.tasknumGps);
      const tasknumRearview = parseInt(row.tasknumRearview);
      const tasknumWirelessCharge = parseInt(row.tasknumWirelessCharge);
      const tasknumYunjing = parseInt(row.tasknumYunjing);
      if (tasknumDidihuService === 0 &&
        tasknumDvd === 0 &&
        tasknumFunctionRearview === 0 &&
        tasknumGps === 0 &&
        tasknumRearview === 0 &&
        tasknumWirelessCharge === 0 &&
        tasknumYunjing === 0) {
          Modal.warning({
            title: '提示信息',
            content: '不能将所有产品的任务数都设置为0',
          });
      } else {

        const newData = [...salesTask.list];
        const index = newData.findIndex(item => id === item.id);
        //更新要修改的数据
        if (index > -1) {
          const item = newData[index];
          let salesTaskRow = {
            ...item,
            ...row,
          };
          dispatch({
            type: 'salesTask/fetchUpdateTask',
            payload: {
              currentPage: this.state.page,
              pageSize: this.state.pageSize,
              ...salesTaskRow
            }
          });
          //查询
          /* dispatch({
             type: 'salesTask/fetchTaskList',
             payload: {
               currentPage: this.state.page,
               pageSize: this.state.pageSize
             }
           });*/
          this.setState({ editingKey: '' });
          /*newData.splice(index, 1, {
            ...item,
            ...row,
          });
          this.setState({ data: newData, editingKey: '' });*/
        } else {
          newData.push(row);
          this.setState({ data: newData, editingKey: '' });
        }
      }

    });
  }
  //取消
  cancel = () => {
    //恢复state的初始化状态
    this.setState({ editingKey: '' });
  };

  //跳转
  toSpDetail = (spCode) => {

    this.setState({ detailSpVisible: true, spCode: spCode });

  }

  //弹框
  hideModal = () => {
    this.setState({ importVisible: false });
  }
  //table弹框
  hideTableModal = () => {
    this.setState({ TableVisible: false });
  }

  //确认导入
  submitModal = () => {
    //this.formRef拿到了form
    this.formRef.props.form.validateFieldsAndScroll((err, values) => {
      if (!err) {
        // console.log('Received values of form: ', values);
        // 刷新列表数据
        const { dispatch } = this.props;
        dispatch({
          type: 'salesTask/fetchTaskList',
          payload: {
            currentPage: 1,
            pageSize: 10
          }
        })
        this.setState({ importVisible: false });
      }
    })
  }

  render() {

    const { salesTask } = this.props;

    let columns = this.state.columns;
    if (salesTask.publishStatus === 1 && columns.length === 15) { //已发布
      columns = this.state.columns;
      columns.pop();
    } else if (salesTask.publishStatus === 0 && columns.length === 14) {
      columns.push(this.state.operationCol);
    }

    //table的components属性
    const components = {
      body: {
        row: EditableFormRow,
        cell: EditableCell,
      },
    };

    columns = columns.map((col) => {
      if (!col.editable) {
        return col;
      }
      return {
        ...col,
        onCell: record => ({
          record,
          inputType: 'number',
          dataIndex: col.dataIndex,
          title: col.title,
          editing: this.isEditing(record),
          test: record.id
        }),
      };
    });

    return (
      <PageHeaderWrapper>
        <Card bordered={false}>
          <Row>
            <Col>
              <Button type="primary" disabled={salesTask.publishStatus === 1} style={{marginRight:'20px'}} onClick={this.importTask}>导入任务配置</Button>
              <Modal
                title="导入文件"
                visible={this.state.importVisible}
                // onOk={this.submitModal}
                 onCancel={this.hideModal}
                // okText="确认"
                // cancelText="取消"
                footer={null}
              >
              <ImportForm onOk={this.submitModal}
                onCancel={this.hideModal} wrappedComponentRef={(form) => this.formRef = form}></ImportForm>
              </Modal>
              <Button type="primary" onClick={this.sendMessage} disabled={salesTask.publishStatus === 1}>发布任务</Button>
            </Col>
          </Row>
             
        </Card>
          <Modal
            title="我的月报表"
            visible={this.state.TableVisible}
            onCancel={this.hideTableModal}
            footer={null}
            >
            
            <TableModal type={this.state.type} name={this.state.name}></TableModal>
          </Modal>
         <TableCustom
            components={components}
            columns={columns}
            scroll={{ x: salesTask.publishStatus === 1 ? 2250 : 2400,y: 600 }}
            dataSource={ salesTask.list }//{this.state.data ? this.state.data : salesTask.list}
            total={salesTask.total}
            onChange={this.onChange}
            onShowSizeChange={this.onShowSizeChange}
            page={salesTask.currentPage}
            />
          </PageHeaderWrapper>

    )
  }
}

export default SalesTask

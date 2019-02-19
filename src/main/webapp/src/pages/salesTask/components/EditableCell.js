import React, { Component } from 'react';
import { Form, Input, InputNumber} from 'antd';


const EditableContext = React.createContext();

const EditableRow = ({ form, index, ...props }) => (
  <EditableContext.Provider value={form}>
    <tr {...props} />
  </EditableContext.Provider>
);

const EditableFormRow = Form.create()(EditableRow);

const FormItem = Form.Item;

class EditableCell extends Component {

  getInput = () => {
    if (this.props.inputType === 'number') {
      return <InputNumber min={0} formatter={value => `${value}`.replace('.', '')}/>; // onChange={this.handleNumberChange}
    }
    return <Input />;
  };

  // handleNumberChange = (e) => {
  //   console.log(e)
  //   if()
  // }

  render() {
    const {
      editing,
      dataIndex,
      title,
      inputType,
      record,
      index,
      ...restProps
    } = this.props;
    //console.log("editing:"+editing);
    return (
      //Consumer
      <EditableContext.Consumer>
        {(form) => {
          const { getFieldDecorator } = form;
          return (
            <td {...restProps}>
              {editing ? (
                <FormItem style={{ margin: 0 }}>
                  {getFieldDecorator(dataIndex, {
                    rules: [{
                      required: true,
                      message: `请输入 ${title}!`,
                    }],
                    initialValue: record[dataIndex],
                  })(this.getInput())}
                </FormItem>
              ) : restProps.children}
            </td>
          );
        }}
      </EditableContext.Consumer>
    );
  }
}

export default EditableCell
export {
     //EditableCell,
     EditableContext,
     EditableRow,
     EditableFormRow
}

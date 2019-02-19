import React, { Component } from 'react';
import { connect } from 'dva';
import Table from './components/Table';
import Detail from './components/Detail';
import PageHeaderWrapper from '@/components/PageHeaderWrapper';

@connect(({ history }) => ({
  history
}))

class SalesHistory extends Component {

  constructor(props) {
    super(props);
    const {dispatch, history} = this.props;
    const param = "sum";
    dispatch({
      type: 'history/fetchChange',
      payload: {
        taskType: param
      }
    })
  }

  toDetail = (text, e) => {
    const { dispatch } = this.props;
    dispatch({
      type: 'history/showDetail',
      payload: {
        showDetail: true,
        spCode: text
      }
    });
  };

  goBack = () => {
    const { dispatch } = this.props;
    dispatch({
      type: 'history/showDetail',
      payload: {
        showDetail: false,
        spCode: ''
      }
    });
  };

  render() {

    const { history } = this.props;
    let view = '';

    if (!history.showDetail) {
      view = (
        <div>
          <Table toDetail={this.toDetail} />
        </div>
      )
    } else {
      view = (
        <div>
          <Detail spCode={history.spCode} goBack={this.goBack} />
        </div>
      );
    }

    return (
      <PageHeaderWrapper>
        {view}
      </PageHeaderWrapper>
    );
  }
}


export default SalesHistory

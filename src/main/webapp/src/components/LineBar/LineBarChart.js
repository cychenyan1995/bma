import React, { Component } from 'react';
import echarts from 'echarts/lib/echarts';
import 'echarts/lib/chart/line';
import 'echarts/lib/chart/bar';
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';
import 'echarts/lib/component/legend';

class LineBarChart extends Component {

  componentDidMount() {
    this.getChart();
  }
  componentDidUpdate() {
    this.getChart();
  }

  getChart = () => {
    const myChart = echarts.init(document.getElementById("lineBarChart"));
    const { data } = this.props;
    const barName  = data.barName1;
    const option = {
       title: {
        text: data.title,
        x: 'center'
      },
      tooltip: {
        trigger: 'axis',
        formatter: function(params, ticket, callback) {

          var res = params[0].name;

          for (var i = 0, l = params.length; i < l; i++) {
            if (params[i].seriesType === 'line') {
              res += '<br/>' + params[i].seriesName + ' : ' + (params[i].value ? params[i].value : '-') + data.unitLeft;
            } else {
              res += '<br/>' + params[i].seriesName + ' : ' + (params[i].value ? params[i].value : '-') + data.unitRight;
            }
          }
          return res;

        }
      },
      grid: {
        containLabel: true
      },
      legend: {
        data: data.legendData,
        x: 'right'
      },
      xAxis: [{
        type: 'category',
        axisTick: {
          alignWithLabel: true
        },
        data: data.xAxisData
      }],
      dataZoom: [{
        type: 'slider',
        xAxisIndex: 0,
        filterMode: 'empty',
        start: 0,
        end: 100
      }, {
        type: 'slider',
        yAxisIndex: 0,
        filterMode: 'empty',
        start: 0,
        end: 100
      }, {
        type: 'inside',
        xAxisIndex: 0,
        filterMode: 'empty',
        start: 0,
        end: 100
      }, {
        type: 'inside',
        yAxisIndex: 0,
        filterMode: 'empty',
        start: 0,
        end: 100
      }],
      yAxis: [{
        type: 'value',
        name: '',
        min: 0,
        position: 'left',
        axisLabel: {
          formatter: '{value} '+ data.unitLeft
        },
        splitLine:{
           show:false
       },
      }, {
        type: 'value',
        name: '',
        min: 0,
        position: 'right',
        axisLabel: {
          formatter: '{value} '+ data.unitRight
        },
        splitLine:{
           show:false
       },
      }],
      series: [{
        name: data.lineName,
        type: 'line',
        label: {
          normal: {
            show: true,
            position: 'top',
            formatter: '{c} %'
          }
        },
        lineStyle: {
          normal: {
            width: 3,
            shadowColor: 'rgba(0,0,0,0.4)',
            shadowBlur: 10,
            shadowOffsetY: 10
          }
        },
        data: data.lineData
      }, {
        name: data.barName1,
        type: 'bar',
        yAxisIndex: 1,
        label: {
          normal: {
            show: true,
            position: 'top'
          }
        },
        data: data.barData1
      }, {
        name: data.barName2,
        type: 'bar',
        yAxisIndex: 1,
        label: {
          normal: {
            show: true,
            position: 'top'
          }
        },
        data: data.barData2
      }]

    }
    myChart.setOption(option);

  }

  render() {
    return (
      <div id="lineBarChart" style={{width:'100%',height:'500px'}}></div>
    )
  }
}

export default LineBarChart

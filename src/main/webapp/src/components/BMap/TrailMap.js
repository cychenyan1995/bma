import React, {Component} from 'react';

class TrailMapCustom extends Component {

  componentDidMount () {
    // 创建地图实例
    const map = new BMap.Map("trailMap");
    const {data} = this.props;
    const point = new BMap.Point(data[0].lng, data[0].lat);
    map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图级别
    // 启用滚轮放大缩小，默认禁用
    map.enableScrollWheelZoom();

    const chartData = [];
    data.map((value) => (
      chartData.push(new BMap.Point(value.lng, value.lat))
    ));
    for (let i = 0; i < chartData.length-1; i++) {
      const startPoint = chartData[i];
      const endPoint = chartData[i + 1];

      const displayStartIcon = i===0;
      const displayEndIcon = i===chartData.length-2;
      let walking = null;
      if(displayStartIcon && !displayEndIcon){ // 第一个起点只展示起点图标
        walking = new BMap.DrivingRoute(map, {
          renderOptions: { map, autoViewport: true },
          onMarkersSet(routes) {
            map.removeOverlay(routes[1].marker);
          }
        });
      }else if(!displayStartIcon && !displayEndIcon){// 中间的起点终点不展示起点、终点图标
        walking = new BMap.DrivingRoute(map, {
          renderOptions: { map, autoViewport: true },
          onMarkersSet(routes) {
            map.removeOverlay(routes[0].marker);
            map.removeOverlay(routes[1].marker);
          }
        });
      }else{// 最后一个终点只展示终点图标
        walking = new BMap.DrivingRoute(map, {
          renderOptions: { map, autoViewport: true },
          onMarkersSet(routes) {
            map.removeOverlay(routes[0].marker);
          }
        });
      }
      walking.search(startPoint, endPoint);
    }
  }

  render() {
    return (
      <div id="trailMap" style={{width:'100%',height:'500px'}} />
    );
  }
}

export default TrailMapCustom;

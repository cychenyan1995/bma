import React, {Component} from 'react';

class HeatMapCustom extends Component {

  componentDidMount () {
    const map = new BMap.Map("heatMap");// 创建地图实例
    const {data,maxCount} = this.props;
    const point = new BMap.Point(data[0].lng, data[0].lat);
    map.centerAndZoom(point, 16); // 初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom(); // 允许滚轮缩放
    const points =data;
    for (let i = 0; i < points.length; i++) {
      const point1 = new BMap.Point(points[i].lng,points[i].lat);
      const marker = new BMap.Marker(point1);
      map.addOverlay(marker);
      marker.addEventListener("click", (e) => {
        const opts = {
          width : 300,         // 信息窗口宽度
          height: 80,          // 信息窗口高度
          title : '常住地',  // 信息窗口标题
          enableMessage:true   // 设置允许信息窗发送短息
        };
        const p = e.target;
        const point1 = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
        const infoWindow = new BMap.InfoWindow('<div>地址：广东省深圳市南山区科兴科学园</div><div>时间：2018-10-11</div>',opts);  // 创建信息窗口对象
        map.openInfoWindow(infoWindow,point1);              // 开启信息窗口
      })
    }

    const heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":50});
    map.addOverlay(heatmapOverlay);
    heatmapOverlay.setDataSet({data:points,max:maxCount});
  }

  render() {
    return (
      <div id="heatMap" style={{width:'100%',height:'500px'}} />
    );
  }
}

export default HeatMapCustom;

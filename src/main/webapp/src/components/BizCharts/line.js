import React from 'react';
import {Row, Col, Card} from 'antd';
import {Chart, Geom, Axis, Tooltip, Legend} from 'bizcharts';
import DataSet from "@antv/data-set";

const Basic = (props) => {
  const {title,lineData,name,value,label,position,cols} = props;
  return (
    <Card bordered={false}>
      <Row>
        <Col>
          {title}
          <Chart height={400} data={lineData} scale={cols} forceFit>
            <Axis name={name} />
            <Axis name={value} label={label} />
            <Tooltip
              crosshairs={{
                type: "y"
              }}
            />
            <Geom type="line" position={position} size={2} />
            <Geom
              type="point"
              position={position}
              size={4}
              shape="circle"
              style={{
                stroke: "#fff",
                lineWidth: 1
              }}
            />
          </Chart>
        </Col>
      </Row>
    </Card>
  );
};
const Curved = (props) => {
  const {title,lineData,transform,name,label,position,cols} = props;
  const ds = new DataSet();
  const dv = ds.createView().source(lineData);
  dv.transform(transform);
  return (
    <Card bordered={false}>
      <Row>
        <Col>
          {title}
          <Chart data={dv} scale={cols} forceFit>
            <Legend />
            <Axis name={name} />
            <Axis
              name={transform.value}
              label={label}
            />
            <Tooltip
              crosshairs={{
                type: "y"
              }}
            />
            <Geom
              type="line"
              position={position}
              size={2}
              color={transform.key}
              shape="smooth"
            />
            <Geom
              type="point"
              position={position}
              size={4}
              shape="circle"
              color={transform.key}
              style={{
                stroke: "#fff",
                lineWidth: 1
              }}
            />
          </Chart>
        </Col>
      </Row>
    </Card>
  );
};
export default {
  Line:Basic,
  CurvedLine:Curved
}

import { parse } from 'url';
import mockjs from 'mockjs';

let tableListDataSource = [];

for (var i = 0; i < 88; i++) {
  tableListDataSource.push(mockjs.mock({
    id: mockjs.Random.integer(1000000, 1100000),
    sp: mockjs.Random.cword(8),
    area: mockjs.Random.province(),
    regionManageLeader: mockjs.Random.cname(),
    regionManage: mockjs.Random.cname(),
    bizType: mockjs.Random.cword(2),
    month: mockjs.Random.date('yyyy-MM'),
    tasknumDvd: mockjs.Random.integer(10, 100),
    tasknumGps: mockjs.Random.integer(10, 100),
    tasknumYunjing: mockjs.Random.integer(10, 100),
    tasknumDidihuService: mockjs.Random.integer(10, 100),
    tasknumWirelessCharge: mockjs.Random.integer(10, 100),
    tasknumRearview: mockjs.Random.integer(10, 100),
    tasknumFunctionRearview: mockjs.Random.integer(10, 100),
  }))
}

function getSalesTaskList(req, res, u) {

  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  const params = parse(url, true).query;
  console.log(params);

  let dataSource = tableListDataSource;
  let pageSize = 10;
  if (params.pageSize && params.currentPage) {
    pageSize = params.pageSize * 1;
    let currentPage = params.currentPage * 1;
    let start = params.pageSize * (currentPage - 1);
    let end = params.pageSize * currentPage;
    console.log(start);
    console.log(end);

    let filterDataSource = dataSource.slice(start, end);
    dataSource = filterDataSource;

  }

  const result = {
    list: dataSource,
    total: tableListDataSource.length
  }

  return res.json(result);
}

function updateTask(req, res, u, b) {

  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }
  const body = (b && b.body) || req.body;
  console.log('updateTask:' + JSON.stringify(body));
  const { currItem } = body;
  tableListDataSource = tableListDataSource.map((item) => {
    if(item.id === currItem.id){
        Object.assign(item,currItem);
        return item;
    }
    return item;
  })

  const result = {
    list: tableListDataSource,
    total: tableListDataSource.length
  }

  return res.json(result);

}



export default {
  'GET /salesTask/list': getSalesTaskList,
  'POST /salesTask/updateTask': updateTask,
}

import { parse } from 'url';
import mockjs from 'mockjs';

// mock tableListDataSource
let tableListDataSource = [];
const Users = [];

for (let i = 0; i < 86; i++) {
  tableListDataSource.push(mockjs.mock({
      key: mockjs.Random.guid(),
      name: mockjs.Random.cname(),
      addr: mockjs.mock('@county(true)'),
      'age|18-60': 1,
      birth: mockjs.Random.date(),
      sex: mockjs.Random.integer(0, 1)
  }));
}

function getUser(req, res, u) {
  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  const params = parse(url, true).query;

  let dataSource = tableListDataSource;

  if (params.sorter) {
    const s = params.sorter.split('_');
    dataSource = dataSource.sort((prev, next) => {
      if (s[1] === 'descend') {
        return next[s[0]] - prev[s[0]];
      }
      return prev[s[0]] - next[s[0]];
    });
  }

  if (params.status) {
    const status = params.status.split(',');
    let filterDataSource = [];
    status.forEach(s => {
      filterDataSource = filterDataSource.concat(
        dataSource.filter(data => parseInt(data.status, 10) === parseInt(s[0], 10))
      );
    });
    dataSource = filterDataSource;
  }

  if (params.name) {
    dataSource = dataSource.filter(data => data.name.indexOf(params.name) > -1);
  }

  let pageSize = 10;
  if (params.pageSize) {
    pageSize = params.pageSize * 1;
  }

  const result = {
    list: dataSource,
    pagination: {
      total: dataSource.length,
      pageSize,
      current: parseInt(params.currentPage, 10) || 1,
    },
  };

  return res.json(result);
}

function postUser(req, res, u, b) {

  let url = u;
  if (!url || Object.prototype.toString.call(url) !== '[object String]') {
    url = req.url; // eslint-disable-line
  }

  const body = (b && b.body) || req.body;
  const { method, key, keys } = body;
  console.log('demo user:' + JSON.stringify(body));
    switch (method) {
    /* eslint no-case-declarations:0 */
    case 'delete':
      // tableListDataSource = tableListDataSource.filter(item => key.indexOf(item.key) === -1);
      let ids = keys.split(',');

      // 单项删除
      // tableListDataSource = tableListDataSource.filter(u => u.key !== key);

      // 支持批量删除
      tableListDataSource = tableListDataSource.filter(u => !ids.includes(u.key));

      break;
    case 'create':
      const { name, sex, age, addr, birth } = body;
      const i = Math.ceil(Math.random() * 10000);
      tableListDataSource.unshift({
        key: mockjs.Random.guid(),
        name: name,
        addr: addr,
        age: age,
        birth: birth,
        sex: sex,
      });
      break;
    case 'update':
      const { values } = body;
      tableListDataSource = tableListDataSource.map(item => {
        if (item.key === key) {
          Object.assign(item, values);
          return item;
        }
        return item;
      });
      break;
    default:
      break;
  }

  const result = {
    list: tableListDataSource,
    pagination: {
      total: tableListDataSource.length,
    },
  };

  return res.json(result);
}

export default {
  'GET /api/demo/user': getUser,
  'POST /api/demo/user': postUser,
};

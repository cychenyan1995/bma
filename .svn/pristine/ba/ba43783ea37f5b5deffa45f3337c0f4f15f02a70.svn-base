export default [

  // app
  {
    path: '/base',
    component: '../layouts/BasicLayout',
    Routes: ['src/pages/Authorized'],
    authority: ['admin', 'user'],
    routes: [
      { path: '/', redirect: '/base/salesTask' }, //修改启动页面
      {
        path: '/base/salesTask',
        icon: 'table',
        name: 'salesTask',
        component: './salesTask/index',
      },
      {
        path: '/base/salesHistory',
        icon: 'dashboard',
        name: 'salesHistory',
        component: './salesHistory/index',
      },
      {
        path: '/base/detail',
        hideInMenu:true,
        name: 'detail',
        component: './detail/detail',
      }
    ]
  },
  {
    path: '/send',
    component: '../layouts/BlankLayout',
    // Routes: ['src/pages/Authorized'],
    //authority: ['admin', 'user'],
    routes: [
      //{ path: '/', redirect: '/sendMessage'},
      {
        path: '/send/sendMessage/:taskType/:time/:type/:name',
        component: './sendMessage/index',
      }
    ]
  },

];

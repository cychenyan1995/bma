import React, { Fragment } from 'react';
import { Layout, Icon } from 'antd';
import GlobalFooter from '@/components/GlobalFooter';

const { Footer } = Layout;
const FooterView = () => (
  <Footer style={{ padding: 0 }}>
    <GlobalFooter
      links={[
        /* {
          key: '广联赛讯',
          title: '广联赛讯',
          href: 'http://www.glsx.com.cn/',
          blankTarget: true,
        } */
      ]}
      copyright={
        <Fragment>
          Copyright <Icon type="copyright" /> <strong>深圳广联赛讯有限公司</strong>  版权所有
        </Fragment>
      }
    />
  </Footer>
);
export default FooterView;

import React from 'react';
import { Layout } from 'antd';
import styles from './UserLayout.less';

class newPage extends React.PureComponent {
  render() {
    const { children } = this.props;
    return (
      // @TODO <DocumentTitle title={this.getPageTitle()}>
      <div className={styles.container}>     
         <div className={styles.content}>
          <div className={styles.top}>
            <div className={styles.header}>
           {children}
           </div>
           </div>
         </div>
          
         </div>

    );
  }
}

export default newPage;

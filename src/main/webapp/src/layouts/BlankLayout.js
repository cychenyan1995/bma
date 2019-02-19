import React from 'react';
import styles from './BlankLayout.less';

class BlankLayout extends React.PureComponent {
  render() {
    const { children } = this.props;
    return (
      // @TODO <DocumentTitle title={this.getPageTitle()}>
      <div className={styles.container}>     
         <div className={styles.content}>
          <div >
            <div className={styles.header}>
           {children}
           </div>
           </div>
         </div>
         </div>

    );
  }
}


export default BlankLayout

import ProLayout, {
    MenuDataItem,
    BasicLayoutProps as ProLayoutProps,
    Settings,
    DefaultFooter,
} from '@ant-design/pro-layout';

import React from 'react';

const Layout: React.FC = ({ children }) => {
    return (
        <ProLayout
        >
            {children}
        </ProLayout>
    );
};

export default Layout;

import React from 'react';
import { Upload, Button } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

interface UploadProprs {
  uploadProprs?: any;
  fileList?: any;
}

class MyUpload extends React.Component<UploadProprs> {

  render() {
    const { uploadProprs, fileList } = this.props;
    return (
      <Upload {...uploadProprs} fileList={fileList}>
        <Button>
          <UploadOutlined /> 上传
        </Button>
      </Upload>
    );
  }
}

export default MyUpload;
import React, { useState, useEffect, useMemo } from 'react';
import { connect } from 'umi';
import { ConnectState } from '@/models/connect';

import { Card, message } from 'antd';
import { Editor } from 'react-draft-wysiwyg';
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css'
import draftjs from 'draftjs-to-html';
import { EditorState, convertToRaw, ContentState } from 'draft-js';
import htmlToDraft from 'html-to-draftjs';

interface EditorArticleProps {
  data?: any;
  onContentStateChange?: void;
};
const EditorArticle: React.FC<EditorArticleProps> = (props) => {
  const { data, onContentStateChange } = props;
  // 状态
  const [editorState, setEditorState] = useState('');
  // 构建 data
  const buildEditorData = (data: any) => {
    if (data && data !== 'null') {
      const contentBlock = htmlToDraft(data);
      const contentState = ContentState.createFromBlockArray(contentBlock.contentBlocks);
      const editorState = EditorState.createWithContent(contentState);
      return editorState;
    }
    return null;
  }

  useEffect(() => {
    onEditorStateChange(buildEditorData(data));
  }, []);


  // console.log(data)
  const onEditorChange = (editortContent: any) => {
    onContentStateChange(draftjs(editortContent));
  }
  const onEditorStateChange = (editorState: any) => {
    setEditorState(editorState);
  }

  const uploadImageCallBack = (file: any) => {
    return new Promise(
      (resolve, reject) => {
        let formData = new FormData()
        formData.append('file', file)
        fetch(`http://mkweb:8803/api/upload`, {
          method: 'POST',
          headers: {
            //   'store-user-token':sys.token
          },
          body: formData,
        }).then(res => {
          return res.json()
        }).then(res => {
          if (res && res.status) {
            resolve({ data: { link: res.data.url } })
          } else {
            message.error('图片上传失败', 2)
            reject(res)
          }
        }).catch(err => {
          reject(err)
        })
      }
    )
  }

  return (
    <div>
      <Card>
        <Editor
          editorState={editorState}
          onContentStateChange={onEditorChange}
          onEditorStateChange={onEditorStateChange}
          toolbar={{
            image: {
              urlEnabled: true,
              uploadEnabled: true,
              alignmentEnabled: true,   // 是否显示排列按钮 相当于text-align
              uploadCallback: uploadImageCallBack,
              previewImage: true,
              inputAccept: 'image/*',
              alt: { present: false, mandatory: false, previewImage: true }
            },
          }}
        />
      </Card>
    </div>
  );
};

export default connect(({ global, settings }: ConnectState) => ({
  collapsed: global.collapsed,
  settings,
}))(EditorArticle);
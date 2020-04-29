import React, { useState, useEffect } from 'react';
import { PageHeaderWrapper } from '@ant-design/pro-layout';
import { connect, history, useIntl } from 'umi';
import { ConnectState } from '@/models/connect';
import { Form, Select, Upload, Button, Input, message, AutoComplete } from 'antd';
import EditorArticle from '@/components/EditorArticle';
import MyUpload from '@/components/MyUpload';
import { addArticle, queryFileCatoryList, queryItCatoryList, queryArticle } from './service';
import styles from './index.less';

const EditorArticleForm: React.FC<{}> = (props) => {
    // const { match } = props;
    const [form] = Form.useForm();
    const [fileList, setFileList] = useState<any>([]);
    const [content, setContent] = useState('');
    // it技术分类
    const [itCatorys, setItCatorys] = useState([]);
    // 文件分类
    const [fileCatorys, setFileCatorys] = useState([]);
    const { currentUser } = props;

    const { formatMessage } = useIntl();

    useEffect(() => {
        // 获取文件分类
        queryFileCatoryList().then(res => {
            // console.log(res)
            if (res && res.status) {
                let list = res.list.map((item: any) => ({ value: item.id, label: item.name, key: item.name }));
                setFileCatorys(list);
            }
        });
        // 获取技术分类
        queryItCatoryList().then(res => {
            // console.log(res)
            if (res && res.status) {
                let list = res.list.map((item: any) => ({ value: item.id, label: item.name, key: item.name }));
                setItCatorys(list);
            }
        });
        // setFileList([{ uid: 'rc-upload-1586136474460-6', name: '3.png', size: 8812, type: 'image/png' }]);
    }, []);

    useEffect(() => {
        let id = props.match.params.id;
        if (id > 0) {
            queryArticle(id).then(res => {
                if (res && res.status && res.data) {
                    let article = res.data;
                    let fileCatory = article.fileCatory.map((item: any) => item.id);
                    let itCatory = article.itCatory.map((item: any) => item.id);
                    form.setFieldsValue({ fileName: article.fileName });
                    form.setFieldsValue({ fileDesc: article.fileDesc });
                    form.setFieldsValue({ fileCatory: fileCatory });
                    form.setFieldsValue({ itCatory: itCatory });
                    form.setFieldsValue({ id: article.id });
                    setFileList(article.attachments);
                    setContent(article.content);
                }
            })
        }
    }, [props.match.params.id]);

    // 验证文件名称
    const handleValidateFileName = (rule: any, value: any, callback: any) => {
        if (value) {
            if (value.length < 3 || value.length > 50) {
                callback(formatMessage({ id: 'form.validate.file.name.placeholder' }));
            }
        }
        callback();    // callback方法必须要有，否则会报错
    };
    // 检查归类
    const handleValidateCatory = (rule: any, value: any, callback: any) => {
        if (value) {
            if (value.length > 3) {
                callback(formatMessage({ id: 'form.validate.file.it.catory.placeholder' }));
            }
        }
        callback();    // callback方法必须要有，否则会报错
    };
    // 检查文件简介
    const handleValidateFileDesc = (rule: any, value: any, callback: any) => {
        if (value) {
            if (value.length < 3 || value.length > 100) {
                callback(formatMessage({ id: 'form.validate.file.desc.placeholder' }));
            }
        }
        callback();    // callback方法必须要有，否则会报错
    };
    // 验证附件
    const handleValidateAttachments = (rule: any, value: any, callback: any) => {
        if (value) {
            if (value.fileEnclosure && value.fileEnclosure.fileList && value.fileEnclosure.fileList.length > 3) {
                callback(formatMessage({ id: 'form.validate.file.enclose.placeholder' }));
            }
        }
        callback();    // callback方法必须要有，否则会报错
    };
    // 保存
    const save = async () => {
        try {
            if (content) {
                if (content.length < 1) {
                    message.info(formatMessage({ id: 'form.validate.file.content.placeholder' }));
                    return;
                }
            }

            const values = await form.validateFields(['id', 'fileName', 'fileDesc', 'fileCatory', 'itCatory', 'content']);
            const hide = message.loading(formatMessage({ id: 'form.validate.file.add.placeholder' }));
            try {
                let fileInfos = fileList.map((item: any) => item.response.data);
                let result = await addArticle({
                    id: values?.id, fileName: values?.fileName, fileDesc: values?.fileDesc, fileCatory: values?.fileCatory,
                    itCatory: values?.itCatory, content: content, attachments: fileInfos ? fileInfos : []
                    , userId: (currentUser?.userid) ? (currentUser?.userid) : 1
                });
                if (result && result.status) {
                    hide();
                    message.success(formatMessage({ id: 'form.validate.file.add.success.placeholder' }));
                    history.push('/welcome');
                    return true;
                } else {
                    message.success(formatMessage({ id: 'form.validate.file.add.err.placeholder' }));
                    return false;
                }
            } catch (error) {
                hide();
                console.log(error)
                message.error(formatMessage({ id: 'form.validate.file.add.agin.placeholder' }));
                return false;
            }
        } catch (errorInfo) {
            console.log('Failed:', errorInfo);
            return false;
        }
    }

    // editor 取值
    const onContentStateChange = (value: any) => {
        setContent(value);
    }



    const handleChange = (info: any) => {
        let fileList = [...info.fileList];

        // 1. Limit the number of uploaded files
        // Only to show two recent uploaded files, and old ones will be replaced by the new
        fileList = fileList.slice(-2);

        // 2. Read from response and show file link
        fileList = fileList.map(file => {
            if (file.response) {
                // Component will show file.url as link
                file.url = file.response.url;
            }
            return file;
        });

        setFileList(fileList);
    };
    // 处理上传
    const uploadProprs = {
        action: '/api/upload',
        onChange: handleChange,
        multiple: true,
    };
    return (
        <PageHeaderWrapper
            title={formatMessage({ id: 'header.title' })}
            style={{ padding: 50 }}
        >
            <Form layout="inline" form={form}>
                <div className={styles.form_layout}>
                    <div className={styles.form_item}>
                        <span>{formatMessage({ id: 'form.file.name.title' })}</span>
                        <Form.Item
                            name="fileName"
                            rules={[
                                {
                                    required: true,
                                    message: formatMessage({ id: 'form.file.name.title.placeholder' }),
                                },
                                {
                                    validator: handleValidateFileName
                                }
                            ]}
                        >
                            <Input placeholder={formatMessage({ id: 'form.file.name.title.placeholder' })} />
                        </Form.Item>
                    </div>

                    <div className={styles.form_item}>
                        <span>{formatMessage({ id: 'form.file.desc.title' })}</span>
                        <Form.Item
                            name="fileDesc"
                            rules={[
                                {
                                    required: true,
                                    message: formatMessage({ id: 'form.file.desc.title.placeholder' }),
                                },
                                {
                                    validator: handleValidateFileDesc
                                }
                            ]}
                        >
                            <Input placeholder={formatMessage({ id: 'form.file.desc.title.placeholder' })} />
                        </Form.Item>
                    </div>

                    <div className={styles.form_item}>
                        <span>{formatMessage({ id: 'form.file.catory.title' })}</span>
                        <Form.Item
                            name="fileCatory"
                            rules={[
                                {
                                    required: false,
                                    message: formatMessage({ id: 'form.validate.file.it.catory.placeholder' }),
                                },
                                {
                                    validator: handleValidateCatory
                                }
                            ]}
                        >
                            <Select
                                allowClear={true}
                                maxTagCount={3}
                                maxTagTextLength={3}
                                maxTagPlaceholder="最多选择3个标签"
                                mode="multiple"
                                style={{ width: '100%' }}
                                placeholder={formatMessage({ id: 'form.file.catory.title.placeholder' })}
                                options={fileCatorys}
                            />

                        </Form.Item>
                    </div>
                    <div className={styles.form_item}>
                        <span>{formatMessage({ id: 'form.file.it.title' })}</span>
                        <Form.Item
                            name="itCatory"
                            rules={[
                                {
                                    required: false,
                                    message: formatMessage({ id: 'form.validate.file.catory.placeholder' }),
                                },
                                {
                                    validator: handleValidateCatory
                                }
                            ]}
                        >
                            <Select
                                allowClear={true}
                                maxTagCount={3}
                                maxTagTextLength={3}
                                maxTagPlaceholder="最多选择3个标签"
                                mode="multiple"
                                placeholder={formatMessage({ id: 'form.file.it.title.placeholder' })}
                                options={itCatorys}
                            />

                        </Form.Item>
                    </div>
                    {(content && props.match.params.id || props.match.params.id == 0) && (
                        <div className={styles.form_item_content}>
                            <span>{formatMessage({ id: 'form.file.content.title' })}</span>
                            <Form.Item
                                name="content"
                            >
                                <EditorArticle data={content} onContentStateChange={onContentStateChange} />
                            </Form.Item>
                        </div>
                    )}
                    <div className={styles.form_item}>
                        <span>{formatMessage({ id: 'form.file.encolse.title' })}</span>
                        <Form.Item
                            name="attachments"
                            rules={[
                                {
                                    validator: handleValidateAttachments
                                }
                            ]}
                        >
                            <MyUpload uploadProprs={uploadProprs} fileList={fileList} />
                        </Form.Item>
                    </div>
                </div>
                <Form.Item
                    name="id"
                >
                    <Input hidden />
                </Form.Item>
                <div style={{ margin: '0 auto' }}>
                    <Button onClick={save}>{formatMessage({ id: 'form.save.title' })}</Button>
                </div>
            </Form>
        </PageHeaderWrapper>
    );
};

export default connect(({ user }: ConnectState) => ({
    currentUser: user.currentUser,
}))(EditorArticleForm);
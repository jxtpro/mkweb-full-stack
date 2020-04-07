import request from '@/utils/request';
import { ArticleData } from './data.d';

// 查询单个文章详情
export async function queryArticle(params?: ArticleData) {
  return request(`/api/queryArticle/${params}`);
}

// 新增文章
export async function addArticle(params: any) {
  return request('/api/saveOrUpateArticle', {
    method: 'POST',
    data: {
      ...params,
      method: 'post',
    },
  });
}

// 查询文档分类
export async function queryFileCatoryList() {
  return request('/api/fileCatory/queryList', {
    method: 'GET'
  });
}

// 查询it分类
export async function queryItCatoryList() {
  return request('/api/itCatory/queryList', {
    method: 'GET'
  });
}

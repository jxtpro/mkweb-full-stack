import request from '@/utils/request';

// 查询文档分类
export async function queryArticleAll(params: any) {
  return request(`/api/queryArticleAll/${params.pageSize}/${params.page}`);
}

// 点赞
export async function updateArticleLike(id: number) {
  return request(`/api/updateArticleLike/${id}`);
}
// 浏览
export async function updateArticleBrowse(id: number) {
  return request(`/api/updateArticleBrowse/${id}`);
}

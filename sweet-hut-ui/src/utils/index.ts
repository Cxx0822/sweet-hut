/**
 * 获取assets静态资源
 * @param url 路径
 */
export const getAssetsImageFile = (url: string) => {
  return new URL(`/src/assets/${url}`, import.meta.url).href
}

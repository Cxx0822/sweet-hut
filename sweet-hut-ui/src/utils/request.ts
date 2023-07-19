import axios, { InternalAxiosRequestConfig, AxiosResponse } from 'axios'
import { ElMessage } from 'element-plus'

const service = axios.create({
  // URL地址
  // baseURL: 'https://d428233v29.imdo.co',
  // baseURL: 'http://192.168.5.80:8080',
  baseURL: 'http://127.0.0.1:8080',
  // 连接时间
  timeout: 5000
})

// 请求拦截器
service.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    // const userStore = useUserStore()
    // // 如果有token 则加上token值
    // if (userStore.token) {
    //   config.headers['X-Token'] = getToken()
    // }
    return config
  },

  (error) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  (response: AxiosResponse) => {
    const res = response.data

    // 如果状态码不是20000
    // 根据实际的后端接口确定状态码
    if (response.status !== 200) {
      ElMessage({
        message: res.message || 'Error',
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject(new Error(res.message || 'Error'))
    } else {
      // 正确则返回数据
      return res
    }
  },
  (error) => {
    ElMessage({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service

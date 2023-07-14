import request from '@/utils/request'

export const addPropertyRecord = (propertyRecord): any =>
  request({
    url: '/propertyRecord/addPropertyRecord',
    method: 'post',
    data: propertyRecord
  })

export const getAllPropertyRecordByYear = (year: string): any =>
  request({
    url: '/propertyRecord/getAllPropertyRecordByYear',
    method: 'get',
    params: { year }
  })

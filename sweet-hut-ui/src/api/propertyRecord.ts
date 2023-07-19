import request from '@/utils/request'

export const addPropertyRecord = (propertyRecord: any): any =>
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

export const updatePropertyRecord = (propertyRecord: any): any =>
  request({
    url: '/propertyRecord/updatePropertyRecord',
    method: 'post',
    data: propertyRecord
  })

export const deletePropertyRecord = (id: number): any =>
  request({
    url: '/propertyRecord/deletePropertyRecord',
    method: 'delete',
    params: { id }
  })

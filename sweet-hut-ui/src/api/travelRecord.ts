import request from '@/utils/request'

export const getAllTravelRecord = (): any =>
  request({
    url: '/travelRecord/getAllTravelRecord',
    method: 'get'
  })

export const getAllTravelRecordByPage = (currentPage: number, pageSize: number): any =>
  request({
    url: '/travelRecord/getAllTravelRecordByPage',
    method: 'get',
    params: { currentPage, pageSize }
  })

export const addTravelRecord = (travelJournal): any =>
  request({
    url: '/travelRecord/addTravelRecord',
    method: 'post',
    data: travelJournal
  })

export const addTravelRecordImage = (imageList, date, place): any =>
  request({
    url: '/travelRecord/addTravelRecordImage',
    method: 'post',
    headers: { 'Content-Type': 'multipart/form-data' },
    params: { date, place },
    data: imageList
  })


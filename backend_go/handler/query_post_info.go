package handler

import (
	"backend_go/service"
	"strconv"
)

func QueryPostInfo(topicIdstr string) *PageData {
	topicId, err := strconv.ParseInt(topicIdstr, 10, 64)
	if err != nil {
		return &PageData{
			Code: -1,
			Msg:  err.Error(),
		}
	}
	postInfo, err := service.QueryPostByTopicId(topicId)
	if err != nil {
		return &PageData{
			Code: -1,
			Msg:  err.Error(),
		}
	}

	return &PageData{
		Code: 0,
		Msg:  "success",
		Data: postInfo,
	}

}

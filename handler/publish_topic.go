package handler

import (
	"fmt"
	"github.com/Moonlight-Zhao/go-project-example/service"
	"strconv"
)

type TopicData struct {
	Code int64       `json:"code"`
	Msg  string      `json:"msg"`
	Data interface{} `json:"data"`
}

func PublishTopic(userIdstr, topic_title, content string) *TopicData {

	user_id, _ := strconv.ParseInt(userIdstr, 10, 64)
	fmt.Println(topic_title)
	fmt.Println(content)

	topic_info, err := service.PublishTopic(user_id, topic_title, content)
	if err != nil {
		return &TopicData{
			Code: -1,
			Msg:  err.Error(),
		}
	}
	return &TopicData{
		Code: 0,
		Msg:  "success",
		Data: topic_info,
	}
}

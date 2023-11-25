package handler

import (
	"backend_go/service"
	"strconv"
)

func UpdateScore(UserIdstr string, newScoreStr string) *PageData {
	uid, _ := strconv.ParseInt(UserIdstr, 10, 64)
	newScore, _ := strconv.ParseInt(newScoreStr, 10, 64)

	userId, err := service.UpdateScore(uid, newScore)

	if err != nil {
		return &PageData{
			Code: -1,
			Msg:  err.Error(),
		}
	}
	return &PageData{
		Code: 0,
		Msg:  "success",
		Data: userId,
	}
}

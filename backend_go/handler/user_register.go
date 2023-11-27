package handler

import "backend_go/service"

type UserData struct {
	Code int64       `json:"code"`
	Msg  string      `json:"msg"`
	Data interface{} `json:"data"`
}

func RegisterUser(username, email, password string) *UserData {
	user_info, err := service.RegisterUser(username, email, password)
	if err != nil {
		return &UserData{
			Code: -1,
			Msg:  err.Error(),
		}
	}
	return &UserData{
		Code: 0,
		Msg:  "success",
		Data: user_info,
	}
}

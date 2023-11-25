package handler

import "backend_go/service"

func QueryAllUser() *UserData {
	userInfo, err := service.QueryAllUser()
	if err != nil {
		return &UserData{
			Code: -1,
			Msg:  err.Error(),
		}
	}
	return &UserData{
		Code: 0,
		Msg:  "success query users",
		Data: userInfo,
	}
}

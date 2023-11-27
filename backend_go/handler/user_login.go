package handler

import (
	"backend_go/service"
	"fmt"
)

func UserLogin(UserEmail, UserPwd string) *UserData {

	login_info, err := service.QueryUserByEmail(UserEmail, UserPwd)

	fmt.Println(login_info)
	if err != nil {
		return &UserData{
			Code: -1,
			Msg:  err.Error(),
			Data: login_info,
		}
	}
	return &UserData{
		Code: 0,
		Msg:  "See message below",
		Data: login_info,
	}
}

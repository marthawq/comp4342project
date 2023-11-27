package service

import (
	"backend_go/repository"
	"errors"
)

type LoginInfo struct {
	Status string `json:"status"`
	User   *repository.User
}

func QueryUserByEmail(email, pwd string) (*LoginInfo, error) {
	return NewQueryLoginInfoFlow(email, pwd).Do()
}

func NewQueryLoginInfoFlow(email, pwd string) *QueryLoginInfoFlow {
	return &QueryLoginInfoFlow{
		email: email,
		pwd:   pwd,
	}
}

type QueryLoginInfoFlow struct {
	email string
	pwd   string

	loginInfo *LoginInfo
}

func (f *QueryLoginInfoFlow) Do() (*LoginInfo, error) {
	if err := f.checkParam(); err != nil {
		return nil, err
	}
	if err := f.checkEmail(); err != nil {
		return nil, err
	}
	return f.loginInfo, nil
}

func (f *QueryLoginInfoFlow) checkParam() error {
	if len(f.email) == 0 {
		return errors.New("not valid email")
	}
	return nil
}

func (f *QueryLoginInfoFlow) checkEmail() error {
	user, err := repository.NewUserDaoInstance().QueryUserByEmail(f.email)
	var loginErr error
	if err != nil {
		loginErr = err
	}
	if loginErr != nil {
		f.loginInfo = &LoginInfo{
			Status: "login error",
		}
		return loginErr
	}
	if user == nil {
		f.loginInfo = &LoginInfo{ //-1 denotes user doesn't exist
			Status: "-1",
		}
	} else {
		if user.Password != f.pwd {
			f.loginInfo = &LoginInfo{
				Status: "0", //0 denotes incorrect password
			}
		} else {
			f.loginInfo = &LoginInfo{
				Status: "1", //1 denotes login successfully
				User:   user,
			}
		}
	}
	return nil
}

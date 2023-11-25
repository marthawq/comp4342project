package service

import "backend_go/repository"

type UserInfo struct {
	User []*repository.User
}

func QueryAllUser() (*UserInfo, error) {
	return NewQueryAllUserInfoFlow().Do()
}

func NewQueryAllUserInfoFlow() *QueryAllUserInfoFlow {
	return &QueryAllUserInfoFlow{}
}

type QueryAllUserInfoFlow struct {
	userInfo *UserInfo
}

func (f *QueryAllUserInfoFlow) Do() (*UserInfo, error) {
	if err := f.queryAllUser(); err != nil {
		return nil, err
	}
	return f.userInfo, nil
}

func (f *QueryAllUserInfoFlow) queryAllUser() error {
	users, err := repository.NewUserDaoInstance().QueryAllUser()
	if err != nil {
		return err
	}
	f.userInfo = &UserInfo{
		User: users,
	}
	return nil
}

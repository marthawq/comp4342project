package service

import (
	"backend_go/repository"
	"errors"
	"fmt"
)

func RegisterUser(username, email, password string) (int64, error) {
	return NewRegisterUserFlow(username, email, password).Do()
}

func NewRegisterUserFlow(username, email, password string) *RegisterUserFlow {
	return &RegisterUserFlow{
		username: username,
		Email:    email,
		password: password,
	}
}

type RegisterUserFlow struct {
	UserId   int64
	username string
	Email    string
	password string
	Score    string
}

func (f *RegisterUserFlow) Do() (int64, error) {
	if err := f.checkParam(); err != nil {
		return 0, err
	}
	if err := f.register(); err != nil {
		return 0, err
	}
	return f.UserId, nil

}

func (f *RegisterUserFlow) checkParam() error {
	if len(f.Email) < 0 {
		return errors.New("no email is inputted")
	}
	return nil
}

func (f *RegisterUserFlow) register() error {
	newUser := &repository.User{
		Username: f.username,
		Email:    f.Email,
		Password: f.password,
		Score:    0,
	}

	if err := repository.NewUserDaoInstance().CreateUser(newUser); err != nil {
		return err
	}
	f.UserId = newUser.UserId
	fmt.Println(newUser.UserId)

	return nil
}

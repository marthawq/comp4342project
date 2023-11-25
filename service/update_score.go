package service

import (
	"backend_go/repository"
	"errors"
)

func UpdateScore(userId, newScore int64) (int64, error) {
	return NewUpdateScoreFlow(userId, newScore).Do()
}

func NewUpdateScoreFlow(userId, newScore int64) *UpdateScoreFlow {
	return &UpdateScoreFlow{
		userId:   userId,
		newScore: newScore,
	}
}

type UpdateScoreFlow struct {
	userId   int64
	newScore int64
}

func (f *UpdateScoreFlow) Do() (int64, error) {
	if err := f.checkParam(); err != nil {
		return -1, err
	}
	if err := f.update(); err != nil {
		return -1, err
	}
	return f.userId, nil
}

func (f *UpdateScoreFlow) checkParam() error {
	if f.userId < 0 {
		return errors.New("userId must be larger than 0")
	}
	if f.newScore < 0 {
		return errors.New("score must be larger than 0")
	}
	return nil
}

func (f *UpdateScoreFlow) update() error {

	if err := repository.NewUserDaoInstance().UpdateScore(f.userId, f.newScore); err != nil {
		return err
	}
	return nil
}

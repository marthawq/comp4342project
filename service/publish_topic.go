package service

import (
	"errors"
	"github.com/Moonlight-Zhao/go-project-example/repository"
	"time"
	"unicode/utf8"
)

func PublishTopic(uid int64, TopicTitle, content string) (int64, error) {
	return NewPublishTopicFlow(uid, TopicTitle, content).Do()
}

func NewPublishTopicFlow(userId int64, TopicTitle, content string) *PublishTopicFlow {
	return &PublishTopicFlow{
		UserId:  userId,
		Title:   TopicTitle,
		Content: content,
	}
}

type PublishTopicFlow struct {
	UserId  int64
	Title   string
	Content string

	TopicId int64
}

func (f *PublishTopicFlow) Do() (int64, error) {
	if err := f.checkParam(); err != nil {
		return 0, err
	}
	if err := f.publish(); err != nil {
		return 0, err
	}
	return f.TopicId, nil
}

func (f *PublishTopicFlow) checkParam() error {
	if f.UserId <= 0 {
		return errors.New("userId id must be larger than 0")
	}
	if utf8.RuneCountInString(f.Content) >= 500 {
		return errors.New("content length must be less than 500")
	}
	return nil

}

func (f *PublishTopicFlow) publish() error {
	topic := &repository.Topic{
		CreateTime: time.Now(),
		UserId:     f.UserId,
		Title:      f.Title,
		Content:    f.Content,
	}

	if err := repository.NewTopicDaoInstance().CreateTopic(topic); err != nil {
		return err
	}
	f.TopicId = topic.Id
	return nil
}

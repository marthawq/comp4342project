package repository

import (
	"github.com/Moonlight-Zhao/go-project-example/util"
	"sync"
	"time"
)

type Topic struct {
	TopicId    int64     `gorm:"column:topicId" json:"id,omitempty"`
	UserId     int64     `gorm:"column:UserId" json:"username,omitempty"`
	Title      string    `gorm:"column:title" json:"title,omitempty"`
	Content    string    `gorm:"column:content" json:"content,omitempty"`
	CreateTime time.Time `gorm:"column:create_time" json:"create_time,omitempty"`
}

func (Topic) TableName() string {
	return "topic"
}

type TopicDao struct {
}

var topicDao *TopicDao
var topicOnce sync.Once

func NewTopicDaoInstance() *TopicDao {
	topicOnce.Do(
		func() {
			topicDao = &TopicDao{}
		})
	return topicDao
}

func (*TopicDao) QueryTopicById(id int64) (*Topic, error) {
	var topic Topic
	err := db.Where("id = ?", id).Find(&topic).Error
	if err != nil {
		util.Logger.Error("find topic by id err:" + err.Error())
		return nil, err
	}
	return &topic, nil
}

func (*TopicDao) CreateTopic(topic *Topic) error {
	if err := db.Create(topic).Error; err != nil {
		util.Logger.Error("insert topic err:" + err.Error())
		return err
	}
	return nil
}

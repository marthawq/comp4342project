package repository

import (
	"backend_go/util"
	"sync"
	"time"
)

type Topic struct {
	TopicId    int64     `gorm:"column:topicId" json:"id,omitempty"`
	UserId     int64     `gorm:"column:userId" json:"username,omitempty"`
	Title      string    `gorm:"column:title" json:"title,omitempty"`
	Content    string    `gorm:"column:content" json:"content,omitempty"`
	CreateTime time.Time `gorm:"column:create_time" json:"create_time,omitempty"`
}

type TopicUser struct {
	TopicID    string    `gorm:"column:topicId" json:"topic_id"`
	UserId     string    `gorm:"column:userId" json:"user_id"`
	Title      string    `gorm:"column:title" json:"title"`
	Content    string    `gorm:"column:content" json:"content"`
	CreateTime time.Time `gorm:"column:create_time" json:"create_time"`
	Username   string    `gorm:"column:username" json:"username"`
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
	err := db.Where("topicId = ?", id).Find(&topic).Error
	if err != nil {
		util.Logger.Error("find topic by id err:" + err.Error())
		return nil, err
	}
	return &topic, nil
}

func (*TopicDao) QueryTopics() ([]*TopicUser, error) {
	var topicList []*TopicUser
	sql := "select topic.topicId,topic.userId,topic.title,topic.content,topic.create_time,user.username from `topic` left join `user` on topic.userId = user.userId"
	err := db.Raw(sql).Scan(&topicList).Error
	if err != nil {
		util.Logger.Error("find topic err:" + err.Error())
		return nil, err
	}
	return topicList, nil
}

func (*TopicDao) CreateTopic(topic *Topic) error {
	if err := db.Create(topic).Error; err != nil {
		util.Logger.Error("insert topic err:" + err.Error())
		return err
	}
	return nil
}

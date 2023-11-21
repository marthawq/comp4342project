package repository

import (
	"github.com/Moonlight-Zhao/go-project-example/util"
	"gorm.io/gorm"
	"sync"
)

type User struct {
	Id       int64  `gorm:"column:userId" json:"id,omitempty"`
	Username string `gorm:"column:username" json:"username,omitempty"`
	Email    string `gorm:"column:email" json:"email,omitempty"`
	Password string `gorm:"column:password" json:"password,omitempty"`
}

func (User) TableName() string {
	return "user"
}

type UserDao struct {
}

var userDao *UserDao
var userOnce sync.Once

func NewUserDaoInstance() *UserDao {
	userOnce.Do(
		func() {
			userDao = &UserDao{}
		})
	return userDao
}

func (*UserDao) QueryUserById(id int64) (*User, error) {
	var user User
	err := db.Where("id = ?", id).Find(&user).Error
	if err == gorm.ErrRecordNotFound {
		return nil, nil
	}
	if err != nil {
		util.Logger.Error("find user by id err:" + err.Error())
		return nil, err
	}
	return &user, nil
}

func (*UserDao) MQueryUserById(ids []int64) (map[int64]*User, error) {
	var users []*User
	//fmt.Println(ids)
	err := db.Where("id in (?)", ids).Find(&users).Error
	if err != nil {
		util.Logger.Error("batch find user by id err:" + err.Error())
		return nil, err
	}
	userMap := make(map[int64]*User)
	for _, user := range users {
		//fmt.Println(user.Id)
		userMap[user.Id] = user
	}
	return userMap, nil
}

func (*UserDao) CreateTopic(user *User) error {
	if err := db.Create(user).Error; err != nil {
		util.Logger.Error("insert user err" + err.Error())
		return err
	}
	return nil
}

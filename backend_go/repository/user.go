package repository

import (
	"backend_go/util"
	"gorm.io/gorm"
	"sync"
)

type User struct {
	UserId   int64  `gorm:"column:userId" json:"id,omitempty"`
	Username string `gorm:"column:username" json:"username,omitempty"`
	Email    string `gorm:"column:email" json:"email,omitempty"`
	Password string `gorm:"column:password" json:"password,omitempty"`
	Score    int64  `gorm:"column:score" json:"score"`
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
	err := db.Where("UserId = ?", id).Find(&user).Error
	if err == gorm.ErrRecordNotFound {
		return nil, nil
	}
	if err != nil {
		util.Logger.Error("find user by id err:" + err.Error())
		return nil, err
	}
	return &user, nil
}

func (*UserDao) QueryUserByEmail(email string) (*User, error) {
	var user User
	err := db.Where("email = ?", email).First(&user).Error
	if err == gorm.ErrRecordNotFound {
		return nil, nil
	}
	if err != nil {
		util.Logger.Error("find user by email err:" + err.Error())
		return nil, err
	}
	return &user, nil
}

func (*UserDao) MQueryUserById(ids []int64) (map[int64]*User, error) {
	var users []*User
	//fmt.Println(ids)
	err := db.Where("userId in (?)", ids).Find(&users).Error
	if err != nil {
		util.Logger.Error("batch find user by id err:" + err.Error())
		return nil, err
	}
	userMap := make(map[int64]*User)
	for _, user := range users {
		//fmt.Println(user.Id)
		userMap[user.UserId] = user
	}
	return userMap, nil
}

func (*UserDao) CreateUser(user *User) error {
	if err := db.Create(user).Error; err != nil {
		util.Logger.Error("insert user err" + err.Error())
		return err
	}
	return nil
}

func (*UserDao) UpdateScore(userId, newScore int64) error {
	var user *User
	if err := db.Model(&user).Where("userId = ?", userId).Update("score", newScore).Error; err != nil {
		util.Logger.Error("update score failed" + err.Error())
		return err
	}
	return nil
}

func (*UserDao) QueryAllUser() ([]*User, error) {
	var userList []*User
	sql := "select username,score from `user` order by score desc LIMIT 10;"
	err := db.Raw(sql).Scan(&userList).Error
	if err != nil {
		util.Logger.Error("find user err: " + err.Error())
		return nil, err
	}
	return userList, nil
}

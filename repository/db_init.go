package repository

import (
	"fmt"
	"gorm.io/driver/mysql"
	"gorm.io/gorm"
)

var db *gorm.DB

func Init() error {
	var err error
	//dsn := "root:mysqlpasswordl@tcp(127.0.0.1:3306)/community?charset=utf8mb4&parseTime=True&loc=Local"
	user := "root"
	password := "mysqlpassword"
	hostname := "localhost"
	port := "3306"
	dbname := "community"
	dsn := fmt.Sprintf("%s:%s@tcp(%s:%s)/%s", user, password, hostname, port, dbname) + "?charset=utf8mb4&parseTime=True&loc=Local"
	db, err = gorm.Open(mysql.Open(dsn), &gorm.Config{})
	return err
}

package main

import (
	"backend_go/handler"
	"backend_go/repository"
	"backend_go/util"
	"fmt"
	"gopkg.in/gin-gonic/gin.v1"
	"os"
)

func main() {
	if err := Init(); err != nil {
		os.Exit(-1)
	}
	r := gin.Default()

	r.Use(gin.Logger())

	//user login
	r.GET("/login/:email/:password", func(c *gin.Context) {
		loginEmail := c.Param("email")
		loginPwd := c.Param("password")
		data := handler.UserLogin(loginEmail, loginPwd)
		c.JSON(200, data)
	})
	//get topic
	r.GET("/get/topics", func(c *gin.Context) {
		//topicId := c.Param("UserId")
		data := handler.QueryPageInfo()
		fmt.Println(data)
		c.JSON(200, data)
	})

	//query topic by topic id
	r.GET("/get/post/:topicId", func(c *gin.Context) {
		topicId := c.Param("topicId")
		data := handler.QueryPostInfo(topicId)
		c.JSON(200, data)

	})

	//query all user order by score
	r.GET("/get/users", func(c *gin.Context) {
		data := handler.QueryAllUser()
		c.JSON(200, data)
	})
	//user register
	r.POST("/reg", func(c *gin.Context) {
		username := c.Query("username")
		email := c.Query("email")
		password := c.Query("password")
		data := handler.RegisterUser(username, email, password)
		c.JSON(200, data)
	})

	//user publish reply post
	r.POST("/post/do", func(c *gin.Context) {
		uid := c.Query("uid")
		topicId := c.Query("topic_id")
		content := c.Query("content")
		data := handler.PublishPost(uid, topicId, content)
		c.JSON(200, data)
	})

	//user publish topic
	r.POST("/topic/do", func(c *gin.Context) {
		topic_title := c.Query("title")
		content := c.Query("content")
		uid := c.Query("uid")
		//fmt.Println(uid)
		data := handler.PublishTopic(uid, topic_title, content)
		c.JSON(200, data)
	})

	//user update score
	//required: user id, new score
	r.POST("/update", func(c *gin.Context) {
		userId := c.Query("userId")
		newScore := c.Query("score")
		data := handler.UpdateScore(userId, newScore)
		c.JSON(200, data)
	})
	err := r.Run()
	if err != nil {
		return
	}
}

func Init() error {
	if err := repository.Init(); err != nil {
		return err
	}
	if err := util.InitLogger(); err != nil {
		return err
	}
	return nil
}

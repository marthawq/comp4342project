package service

import (
	"backend_go/repository"
	"fmt"
)

type TopicInfo struct {
	Topic *repository.Topic
	User  *repository.User
}

//type PostInfo struct {
//	Post *repository.Post
//	User *repository.User
//}

type PageInfo struct {
	TopicUser []*repository.TopicUser
}

func QueryPageInfo() (*PageInfo, error) {
	return NewQueryPageInfoFlow().Do()
}

func NewQueryPageInfoFlow() *QueryPageInfoFlow {
	return &QueryPageInfoFlow{}
}

type QueryPageInfoFlow struct {
	pageInfo *PageInfo
}

func (f *QueryPageInfoFlow) Do() (*PageInfo, error) {
	//if err := f.checkParam(); err != nil {
	//	return nil, err
	//}
	//if err := f.prepareInfo(); err != nil {
	//	return nil, err
	//}
	//if err := f.packPageInfo(); err != nil {
	//	return nil, err
	//}
	if err := f.queryTopic(); err != nil {
		return nil, err
	}
	fmt.Println(f.pageInfo)
	return f.pageInfo, nil
}

func (f *QueryPageInfoFlow) queryTopic() error {
	topics, err := repository.NewTopicDaoInstance().QueryTopics()
	if err != nil {
		return err
	}
	//fmt.Println(topics)
	f.pageInfo = &PageInfo{
		TopicUser: topics,
	}
	return nil
}

/*func (f *QueryPageInfoFlow) checkParam() error {
	if f.topicId <= 0 {
		return errors.New("topic id must be larger than 0")
	}
	return nil
}*/

//func (f *QueryPageInfoFlow) prepareInfo() error {
//	//获取topic信息
//	var wg sync.WaitGroup
//	wg.Add(2)
//	var topicErr, postErr error
//	go func() {
//		defer wg.Done()
//		topic, err := repository.NewTopicDaoInstance().QueryTopicById(f.topicId)
//		if err != nil {
//			topicErr = err
//			return
//		}
//		f.topic = topic
//	}()
//	//获取post列表
//	go func() {
//		defer wg.Done()
//		posts, err := repository.NewPostDaoInstance().QueryPostByParentId(f.topicId)
//		if err != nil {
//			postErr = err
//			return
//		}
//		f.posts = posts
//	}()
//	wg.Wait()
//	if topicErr != nil {
//		return topicErr
//	}
//	if postErr != nil {
//		return postErr
//	}
//	//获取用户信息
//	uids := []int64{f.topic.UserId}
//	for _, post := range f.posts {
//		uids = append(uids, post.UserId)
//	}
//	//fmt.Println(uids)
//	userMap, err := repository.NewUserDaoInstance().MQueryUserById(uids)
//	if err != nil {
//		return err
//	}
//	f.userMap = userMap
//	return nil
//}
//
//func (f *QueryPageInfoFlow) packPageInfo() error {
//	//topic info
//	userMap := f.userMap
//	topicUser, ok := userMap[f.topic.UserId]
//	if !ok {
//		return errors.New("has no topic user info")
//	}
//	//post list
//	postList := make([]*PostInfo, 0)
//	for _, post := range f.posts {
//		postUser, ok := userMap[post.UserId]
//		if !ok {
//			return errors.New("has no post user info for " + fmt.Sprint(post.UserId))
//		}
//		postList = append(postList, &PostInfo{
//			Post: post,
//			User: postUser,
//		})
//	}
//	f.pageInfo = &PageInfo{
//		TopicInfo: &TopicInfo{
//			Topic: f.topic,
//			User:  topicUser,
//		},
//		PostList: postList,
//	}
//	return nil
//}

package service

import (
	"backend_go/repository"
	"errors"
)

type PostInfo struct {
	Post []*repository.Post
}

func QueryPostByTopicId(topicId int64) (*PostInfo, error) {
	return NewQueryPostInfoFlow(topicId).Do()
}

func NewQueryPostInfoFlow(topicId int64) *QueryPostInfoFLow {
	return &QueryPostInfoFLow{
		topicId: topicId,
	}
}

type QueryPostInfoFLow struct {
	postInfo *PostInfo
	topicId  int64
}

func (f *QueryPostInfoFLow) Do() (*PostInfo, error) {
	if err := f.checkParam(); err != nil {
		return nil, err
	}
	if err := f.queryPosts(); err != nil {
		return nil, err
	}
	return f.postInfo, nil
}

func (f *QueryPostInfoFLow) checkParam() error {
	if f.topicId < 0 {
		return errors.New("not a valid topic ID")
	}
	return nil
}

func (f *QueryPostInfoFLow) queryPosts() error {
	posts, err := repository.NewPostDaoInstance().QueryPostByParentId(f.topicId)
	var queryErr error
	if err != nil {
		queryErr = err
	}
	if queryErr != nil {
		return queryErr
	}
	f.postInfo = &PostInfo{
		Post: posts,
	}
	return nil
}

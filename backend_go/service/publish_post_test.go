package service

import (
	"backend_go/repository"
	"backend_go/util"
	"os"
	"testing"
)

func TestMain(m *testing.M) {
	if err := repository.Init(); err != nil {
		os.Exit(1)
	}
	if err := util.InitLogger(); err != nil {
		os.Exit(1)
	}
	m.Run()
}

func TestPublishPost(t *testing.T) {

	type args struct {
		topicId int64
		userId  int64
		content string
	}
	tests := []struct {
		name    string
		args    args
		wantErr bool
	}{
		{
			name: "Test posting",
			args: args{
				topicId: 1,
				userId:  2,
				content: "posting again",
			},
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			_, err := PublishPost(tt.args.topicId, tt.args.userId, tt.args.content)
			if (err != nil) != tt.wantErr {
				t.Errorf("PublishPost() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
		})
	}
}

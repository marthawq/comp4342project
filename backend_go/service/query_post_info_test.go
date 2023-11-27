package service

import (
	"testing"
)

func TestQueryPostByTopicId(t *testing.T) {
	type args struct {
		topicId int64
	}
	tests := []struct {
		name    string
		args    args
		wantErr bool
	}{
		{
			name: "Testing query topic id",
			args: args{
				topicId: 1,
			},
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			_, err := QueryPostByTopicId(tt.args.topicId)
			if (err != nil) != tt.wantErr {
				t.Errorf("QueryPostByTopicId() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
		})
	}
}

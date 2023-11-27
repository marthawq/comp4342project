package service

import "testing"

func TestPublishTopic(t *testing.T) {
	type args struct {
		uid        int64
		TopicTitle string
		content    string
	}
	tests := []struct {
		name    string
		args    args
		wantErr bool
	}{
		{
			name: "Test publishing",
			args: args{
				uid:        2,
				TopicTitle: "testing title",
				content:    "test publishing",
			},
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			_, err := PublishTopic(tt.args.uid, tt.args.TopicTitle, tt.args.content)
			if (err != nil) != tt.wantErr {
				t.Errorf("PublishTopic() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
		})
	}
}

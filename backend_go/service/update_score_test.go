package service

import "testing"

func TestUpdateScore(t *testing.T) {
	type args struct {
		userId   int64
		newScore int64
	}
	tests := []struct {
		name    string
		args    args
		wantErr bool
	}{
		{
			name: "testing update score",
			args: args{
				userId:   1,
				newScore: 10,
			},
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			_, err := UpdateScore(tt.args.userId, tt.args.newScore)
			if (err != nil) != tt.wantErr {
				t.Errorf("UpdateScore() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
		})
	}
}

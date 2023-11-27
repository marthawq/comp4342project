package service

import (
	"testing"
)

func TestQueryUserByEmail(t *testing.T) {
	type args struct {
		email string
		pwd   string
	}
	tests := []struct {
		name    string
		args    args
		wantErr bool
	}{
		{
			name: "login testing",
			args: args{
				email: "abcd@gmail.com",
				pwd:   "1234",
			},
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			_, err := QueryUserByEmail(tt.args.email, tt.args.pwd)
			if (err != nil) != tt.wantErr {
				t.Errorf("QueryUserByEmail() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
		})
	}
}

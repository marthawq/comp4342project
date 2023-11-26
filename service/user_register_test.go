package service

import "testing"

func TestRegisterUser(t *testing.T) {
	type args struct {
		username string
		email    string
		password string
	}
	tests := []struct {
		name    string
		args    args
		wantErr bool
	}{
		{
			name: "testing register",
			args: args{
				username: "asdf",
				email:    "asdf@gmail.com",
				password: "asdf",
			},
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			_, err := RegisterUser(tt.args.username, tt.args.email, tt.args.password)
			if (err != nil) != tt.wantErr {
				t.Errorf("RegisterUser() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
		})
	}
}

package service

import (
	"testing"
)

func TestQueryAllUser(t *testing.T) {
	tests := []struct {
		name    string
		want    *UserInfo
		wantErr bool
	}{
		// TODO: Add test cases.
		{
			name:    "Test Query user",
			wantErr: false,
		},
	}
	for _, tt := range tests {
		t.Run(tt.name, func(t *testing.T) {
			_, err := QueryAllUser()
			if (err != nil) != tt.wantErr {
				t.Errorf("QueryAllUser() error = %v, wantErr %v", err, tt.wantErr)
				return
			}
		})
	}
}

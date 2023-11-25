FROM golang:1.17.1-alpine3.14 as builder
WORKDIR /app
COPY . /app/
RUN mkdir -p "/app"
FROM alpine:3.13
WORKDIR /app
COPY hello /app/hello


# FFtalk

求一设计帮忙，有意者请联系：511095208@qq.com

an IM&amp;RTC project

### 技术

1. 开发语言均采用java
2. 传输协议使用websocket+json
3. 音视频使用webrtc
4. 实现server和android

### 功能设计

1. 现阶段基于局域网实现
2. 用户直接使用用户名登录，不做安全校验
3. IM：所有人在一个房间中，实现单聊和群聊
4. RTC：所有人在一个房间中，实现点对点的通话和会议

### 详细设计

##### 登录登出流程

1. 用户向服务侧发起登录请求，服务侧返回登录成功或失败，当已经有同名用户在线时，返回登录失败，其它情况均成功。
2. 用户侧websocket或服务侧websocket连接断开时，登出服务。

##### 在线成员管理

1. 每个用户登录成功后，主动获取当前在线的成员
2. 服务侧在有成员加入，或退出时，发送全体广播，用户收到广播时，刷新房间状态

##### IM

1. 点击其它单个成员，进入单聊
2. 点击自己，进入群聊

##### RTC

1. 点击其它单个成员，进入点对点通话
2. 点击自己，进入会议。

### 传输协议设计

```json
Msg:{
	type:""//消息的类型
    content:""//消息的内容
    from:""//消息的来源
    to:""//消息的目的，可选，当需要服务器转发时，才赋值
}
```

MsgType:

1. LoginRequest

2. LoginResponse

3. OnlineListRequest 

4. OnlineListResponse

5. NotifyOnline

6. NotifyOffline

7. IM

8. RTC

   

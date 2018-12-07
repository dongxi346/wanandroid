package com.dong.wanandroid.data.event_bus_model;

import com.dong.wanandroid.data.user.UserModel;

/**
 * 传递用户信息的EventBus消息事件
 */
public class UserEvent {
    public UserModel mUserModel;
    public UserEvent(UserModel userModel) {
        this.mUserModel = userModel ;
    }
}

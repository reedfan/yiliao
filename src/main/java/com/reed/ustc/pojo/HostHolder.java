package com.reed.ustc.pojo;

import org.springframework.stereotype.Component;

/**
 * Created by nowcoder on 2016/7/3.
 */
@Component
public class HostHolder {
    private static ThreadLocal<TbUser> users = new ThreadLocal<TbUser>();

    public TbUser getUser() {
        return users.get();
    }

    public void setUser(TbUser user) {
        users.set(user);
    }

    public void clear() {
        users.remove();;
    }
}

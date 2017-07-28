package com.mengzhidu.test;

import java.util.Random;

/**
 * 远程服务器调用
 */
public class RemoteService implements  IRemoteService{

    // 随机数对象
    private Random random = new Random();

    /**
     * 获取状态统计数据
     * 它随机返回小于200的整数状态信息
     * @return 状态信息
     */
    public int getStatus() {
        return random.nextInt(200);
    }
}

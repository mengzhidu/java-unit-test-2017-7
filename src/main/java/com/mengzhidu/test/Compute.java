package com.mengzhidu.test;

/**
 * 计算类
 * 这也是单元测试中最简单的情形，因为它通常无需任何依赖
 */
public class Compute {

    /**
     * 简单的计算两个整数的和
     * @param a 被加数
     * @param b 加数
     * @return 结果
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * 简单的返回两个数的差
      * @param a 被减数
     * @param b 减数
     * @return 差
     */
    public int sub(int a, int b) {
        return a - b;
    }
}

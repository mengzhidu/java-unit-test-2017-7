package com.mengzhidu.test;

import junit.framework.TestCase;

public class TestCompute extends TestCase{

    private Compute compute;

    /**
     * 初始化类的对象
     * @throws Exception 抛出未处理的异常
     */
    @Override
    public void setUp() throws Exception {
        super.setUp();
        compute = new Compute();
    }

    /**
     * 测试加法
     */
    public void testAdd() {
        assertEquals(7, compute.add(3, 4));
        assertEquals(9, compute.add(2, 7));
    }

    /**
     * 测试减法
     */
    public void testSub() {
        assertEquals(2, compute.sub(3, 1));
        assertEquals(8, compute.sub(22, 14));
    }

    /**
     * 把类对象设置为null
     * @throws Exception 抛出未处理的异常
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        compute = null;
    }
}

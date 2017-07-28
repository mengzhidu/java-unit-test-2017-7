package com.mengzhidu.test;

import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.junit.Test;

public class TestStatusReport extends TestCase {

    private IRemoteService remoteService;

    private StatusReport statusReport;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        remoteService = EasyMock.createMock(IRemoteService.class);
        statusReport = new StatusReport();
        statusReport.setRemoteService(remoteService);
    }

    @Test
    public void testReport() {
        // 测试临界值0
        EasyMock.expect(remoteService.getStatus()).andReturn(0);
        EasyMock.replay(remoteService);
        assertEquals(statusReport.report(), "需要维护");

        // 测试临界值50
        EasyMock.reset(remoteService);
        EasyMock.expect(remoteService.getStatus()).andReturn(50);
        EasyMock.replay(remoteService);
        assertEquals(statusReport.report(), "需要维护");

        // 测试临界值51
        EasyMock.reset(remoteService);
        EasyMock.expect(remoteService.getStatus()).andReturn(51);
        EasyMock.replay(remoteService);
        assertEquals(statusReport.report(), "状态一般");

        // 测试普通值88
        EasyMock.reset(remoteService);
        EasyMock.expect(remoteService.getStatus()).andReturn(88);
        EasyMock.replay(remoteService);
        assertEquals(statusReport.report(), "状态一般");

        // 测试临界值100
        EasyMock.reset(remoteService);
        EasyMock.expect(remoteService.getStatus()).andReturn(100);
        EasyMock.replay(remoteService);
        assertEquals(statusReport.report(), "状态一般");

        // 测试临界值101
        EasyMock.reset(remoteService);
        EasyMock.expect(remoteService.getStatus()).andReturn(101);
        EasyMock.replay(remoteService);
        assertEquals(statusReport.report(), "状态优秀");

        // 测试随机值171
        EasyMock.reset(remoteService);
        EasyMock.expect(remoteService.getStatus()).andReturn(171);
        EasyMock.replay(remoteService);
        assertEquals(statusReport.report(), "状态优秀");

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        statusReport = null;
        remoteService = null;
    }
}

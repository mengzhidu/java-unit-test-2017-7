package com.mengzhidu.test;

/**
 * 状态统计信息
 */
public class StatusReport {

    private IRemoteService remoteService;

    /**
     * 设置远程服务器
     * @param remoteService 远程服务器
     */
    public void setRemoteService(IRemoteService remoteService) {
        this.remoteService = remoteService;
    }

    /**
     * 根据远程服务器给出的状态属性信息返回对应的文本表述
     * @return 文本信息
     */
    public String report() {
        int status = remoteService.getStatus();
        if (status > 100) {
            return "状态优秀";
        } else if (status > 50) {
            return  "状态一般";
        } else {
            return "需要维护";
        }
    }
}

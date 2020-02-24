package com.lyy.properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ConfigurationProperties(prefix = "academy.hbase")
public class HbaseServerProperties {
    private final Log LOGGER = LogFactory.getLog(HbaseServerProperties.class);
    /**
     * 服务地址,一般设置为ip1,ip2,ip3
     */
    private String zkNodes;
    /**
     * 默认连接的数据库是default
     */
    private String zkParent = "/hyperbase1";
    private int zkPort = 2181;

    private int scanTimeout = 60000;

    private int rpcTimeout = 60000;

    private int searchTimeout = 60000;

    private int maxPoolSize = 200;

    private int retryNumber;

    private int zkRecoveryRetry;

    private int zkRecoveryRetryInterval = 100;

    public String getZkNodes() {
        return zkNodes;
    }

    public void setZkNodes(String zkNodes) {
        String tmpNodes = validateNodes(zkNodes);
        if (tmpNodes.isEmpty()) {
            LOGGER.error("please write correct nodes ,such as 192.168.1.4,192.168.1.5");
            throw new IllegalArgumentException();
        }
        this.zkNodes = tmpNodes;
    }

    private static String validateNodes(String nodes) {
        String num = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";
        String regex = num + "\\." + num + "\\." + num + "\\." + num;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nodes);
        StringBuilder tmp = new StringBuilder();
        while (matcher.find()) {
            if (tmp.length() != 0) {
                tmp.append(",");
            }
            tmp.append(matcher.group());
        }

        return tmp.toString();
    }

    public String getZkParent() {
        return zkParent;
    }

    public void setZkParent(String zkParent) {
        this.zkParent = zkParent;
    }

    public int getZkPort() {
        return zkPort;
    }

    public void setZkPort(int zkPort) {
        this.zkPort = zkPort;
    }

    public int getScanTimeout() {
        return scanTimeout;
    }

    public void setScanTimeout(int scanTimeout) {
        this.scanTimeout = scanTimeout;
    }

    public int getRpcTimeout() {
        return rpcTimeout;
    }

    public void setRpcTimeout(int rpcTimeout) {
        this.rpcTimeout = rpcTimeout;
    }

    public int getSearchTimeout() {
        return searchTimeout;
    }

    public void setSearchTimeout(int searchTimeout) {
        this.searchTimeout = searchTimeout;
    }

    public int getZkRecoveryRetry() {
        return zkRecoveryRetry;
    }

    public void setZkRecoveryRetry(int zkRecoveryRetry) {
        this.zkRecoveryRetry = zkRecoveryRetry;
    }

    public int getRetryNumber() {
        return retryNumber;
    }

    public void setRetryNumber(int retryNumber) {
        this.retryNumber = retryNumber;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }

    public int getZkRecoveryRetryInterval() {
        return zkRecoveryRetryInterval;
    }

    public void setZkRecoveryRetryInterval(int zkRecoveryRetryInterval) {
        this.zkRecoveryRetryInterval = zkRecoveryRetryInterval;
    }
}

package com.lyy.beans;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import com.lyy.properties.HbaseServerProperties;


public class HbaseTemplateMap {
    private HbaseTemplate hbaseTemplate;


    public HbaseTemplateMap() {
    }



    private void init(HbaseServerProperties property) {
        Configuration hbaseConfig = HBaseConfiguration.create();
        hbaseConfig.set("hbase.zookeeper.quorum", property.getZkNodes());
        hbaseConfig.set("zookeeper.znode.parent", property.getZkParent());
        hbaseConfig.set("hbase.zookeeper.property.clientPort", String.valueOf(property.getZkPort()));
        hbaseConfig.set("hbase.client.scanner.timeout.period", String.valueOf(property.getScanTimeout()));
        hbaseConfig.set("hbase.client.retries.number", String.valueOf(property.getRetryNumber()));
        hbaseConfig.set("zookeeper.recovery.retry", String.valueOf(property.getZkRecoveryRetry()));
        hbaseConfig.set("zookeeper.recovery.retry.intervalmill", String.valueOf(property.getZkRecoveryRetryInterval()));
        if (property.getScanTimeout() > property.getRpcTimeout()) {
            throw new IllegalArgumentException("rpc-timeout must >= scan-timeout");
        }
        hbaseConfig.set("hbase.rpc.timeout", String.valueOf(property.getRpcTimeout()));

        hbaseTemplate = new HbaseTemplate(hbaseConfig);
    }



}

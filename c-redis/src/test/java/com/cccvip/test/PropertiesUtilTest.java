/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.test;


import com.cccvip.socket.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * PropertiesUtilTest.
 *
 * @author Carl, 2023-06-21 9:35
 */
@Slf4j
public class PropertiesUtilTest {

    @Test
    public void readInData() {

        Integer port = PropertiesUtil.getNodePort();

        log.info("server port: {}", port);
    }

}

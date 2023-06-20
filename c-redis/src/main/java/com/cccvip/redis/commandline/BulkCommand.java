/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.commandline;


import com.cccvip.redis.resp.entity.BulkArray;

import java.util.List;

/**
 * BulkCommand.
 *
 * @author Carl, 2023-06-20 10:38
 */
public interface BulkCommand extends Command {

    void setContent(List<BulkArray> array);

}

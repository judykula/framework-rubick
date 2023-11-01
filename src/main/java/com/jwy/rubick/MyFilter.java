/*
 * easy come, easy go.
 *
 * contact : syiae.jwy@gmail.com
 *
 * · · · · ||   ..     __       ___      ____  ®
 * · · · · ||  ||  || _ ||   ||    ||   ||      ||
 * · · · · ||  ||  \\_ ||_.||    ||   \\_  ||
 * · · _//                                       ||
 * · · · · · · · · · · · · · · · · · ·· ·    ___//
 */
package com.jwy.rubick;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;

/**
 * <p>
 *      部分影响告警的重复异常或者业务异常强制打印为WARN级别
 * </p>
 *
 * @author Jiang Wanyu
 * @version 1.0
 * @date 2023/10/30
 */
public class MyFilter extends TurboFilter {

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String s, Object[] objects, Throwable throwable) {
        if (level == Level.ERROR) {
            // sleuth的ExceptionLoggingFilter强制打印WARN级别
            if (logger.getName().equals("org.springframework.cloud.sleuth.instrument.web.ExceptionLoggingFilter")) {
                logger.warn(marker, s, objects);
                return FilterReply.DENY;
            }
        }
        return FilterReply.NEUTRAL;
    }
}

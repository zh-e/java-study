package com.zhe.javabase.Thread;

import java.text.DecimalFormat;

/**
 * @author zhangzhe
 */
public final class RequestIDGenerator {

    private final static RequestIDGenerator INSTANCE = new RequestIDGenerator();

    private final static short SEQ_UPPER_LIMIT = 999;

    private short sequence = -1;

    private RequestIDGenerator() {}

    private short nextSequence() {
        if(sequence >= SEQ_UPPER_LIMIT) {
            sequence = 0;
        } else {
            sequence++;
        }
        return sequence;
    }

    public String nextID() {
        DecimalFormat df = new DecimalFormat("000");
        return "hello-" + df.format(nextSequence());
    }

    public static RequestIDGenerator getInstance() {
        return INSTANCE;
    }

}

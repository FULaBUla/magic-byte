package com.github.misterchangray.core;

import com.github.misterchangray.core.util.ConverterUtil;
import org.junit.Assert;
import org.junit.Test;

public class TestFunctional {


    /**
     * 序列化时： data数据中包含所有已序列化的数据(包括 calcLength 也已经调用并序列化)
     * 反序列化时: data数据为传入数据的副本
     * @param data
     * @return
     */
    public static long checker(byte[] data) {
        System.out.println("chcker");
        return 0x3341;
    }

    public static long checker2(byte[] data) {
        System.out.println("chcker2");
        return 0x11;
    }

    @Test
    public void testFunctional() {

    }

}

package com.github.misterchangray.core;

import com.github.misterchangray.core.clazz.ClassManager;
import com.github.misterchangray.core.clazz.ClassMetaInfo;
import com.github.misterchangray.core.clazz.GlobalConfigs;
import com.github.misterchangray.core.clazz.MessageManager;
import com.github.misterchangray.core.exception.MagicByteException;
import com.github.misterchangray.core.intf.MagicMessage;
import com.github.misterchangray.core.util.DynamicByteBuffer;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;


public class MagicByte {
    private static UnPacker unPacker = UnPacker.getInstance();
    private static Packer packer = Packer.getInstance();
    private static MagicChecker magicChecker;

    /**
     * 全局配置默认字符集
     */
    public static void configDefaultCharset(Charset charset) {
        GlobalConfigs.setGlobalDefaultCharset(charset);
    }

    /**
     * 返回结构体总字节数
     * 注意：如果结构体有动态大小，那么返回值为最小字节数
     * @return
     */
    public static int structBytes(Class<?> magicClazz) {
        ClassMetaInfo classMetaInfo = ClassManager.getClassMetaInfo(magicClazz);
        return classMetaInfo.getElementBytes();
    }

    /**
     *
     * 将字节数组按照Class的定义封装成对象
     * 本方法不对字节进行效验; 如果字节缺失也可进行转换, 但是转换后的对象将会不完整
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T pack(byte[] data, Class<?> clazz) throws MagicByteException {
        return pack(data, clazz, magicChecker);
    }


    /**
     *
     * 将字节数组按照Class的定义封装成对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @param checker
     * @return
     */
    public static <T> T pack(byte[] data, Class<?> clazz, MagicChecker checker) throws MagicByteException {
        if(null == data || 0 == data.length) return null;

        DynamicByteBuffer res = DynamicByteBuffer.allocate(data.length);
        res.put(data);
        res.position(0);

        return packer.packObject(res, clazz, checker);
    }

    /**
     *
     * 将对象封装成字节Buffer, 方便后续操作
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ByteBuffer unpack(T data) throws MagicByteException {
        if (null == data) return null;

        return unpack(data, magicChecker);
    }


    /**
     *  将对象转为字节数组
     * @param data
     * @param <T>
     * @return
     */
    public static <T> byte[] unpackToByte(T data) throws MagicByteException {
        if (null == data) return null;

        return  unpack(data, null).array();
    }

    /**
     *  将对象转为字节数组
     * @param data
     * @param <T>
     * @return
     */
    public static <T> byte[] unpackToByte(T data, MagicChecker checker) throws MagicByteException {
        if (null == data) return null;

        return  unpack(data, checker).array();
    }

    /**
     * 携带检查器, 可以计算校验和计算校验和
     * @param data
     * @param checker
     * @param <T>
     * @return
     * @throws MagicByteException
     */
    public static <T> ByteBuffer unpack(T data, MagicChecker checker) throws MagicByteException {
        if (null == data) return null;
        DynamicByteBuffer res = unPacker.unpackObject(data, checker);
        return res.buffer();
    }

    /**
     * 尝试使用命令委托进行解析,解析失败则返回null
     * @param data
     * @return
     * @param <T>
     * @throws MagicByteException
     */
    public static <T extends MagicMessage> T pack(byte[] data) throws MagicByteException {
        if(!MessageManager.hasMessage()) {
            return null;
        }
        return pack(data, null, magicChecker);
    }


    /**
     * 注册消息
     * @param msgClazz 消息类,必须实现 MagicMessage 接口
     */
    public static void registerCMD(Class<? extends  MagicMessage> msgClazz) {
        MessageManager.register(msgClazz);
    }

    /**
     * 注册指定命令的消息
     * @param cmd  命令值
     * @param msgClazz 消息类,必须实现 MagicMessage 接口
     */
    public static void registerCMD(int cmd, Class<? extends  MagicMessage> msgClazz) {
        MessageManager.register(cmd, msgClazz);
    }

    public static void configMagicChecker(MagicChecker checker) {
        magicChecker = checker;
    }

}

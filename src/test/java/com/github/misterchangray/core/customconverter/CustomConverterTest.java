package com.github.misterchangray.core.customconverter;

import com.github.misterchangray.core.MagicByte;
import com.github.misterchangray.core.complex.NestingObject;
import com.github.misterchangray.core.customconverter.entity.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * 自定义序列化测试
 *
 * 1. 整个对象进行测试
 * 2. 单个对象单个属性测试
 * 3. 单个对象多个属性 attachData 测试
 * 4. fixsize 测试
 * 5. list 自定义序列化测试
 */
public class CustomConverterTest {


    @Test
    public void testStaff3() {
        Staff3 staff3 = new Staff3();
        staff3.setId(33);
        staff3.setName("Jack");
        ArrayList arrayList = new ArrayList();

        Book3 book = new Book3();
        book.setCode(24);
        book.setId(23);
        arrayList.add(book);
        Book3 book2 = new Book3();
        book2.setCode(25);
        book2.setId(24);
        arrayList.add(book2);
        staff3.setBook(arrayList);

        ArrayList arrayList2 = new ArrayList();

        Book3 book3 = new Book3();
        book3.setCode(26);
        book3.setId(25);
        arrayList2.add(book3);
        staff3.setBook2(arrayList2);

        byte[] bytes = MagicByte.unpackToByte(staff3);
        Staff3 pack = MagicByte.pack(bytes, Staff3.class);

        Assert.assertEquals(staff3.getId(), pack.getId());
        Assert.assertEquals(staff3.getName(), pack.getName());
        for (int i = 0; i < staff3.getBook().size(); i++) {
            Assert.assertEquals(staff3.getBook().get(i).getCode(), pack.getBook().get(i).getCode());
            Assert.assertEquals(staff3.getBook().get(i).getId(), pack.getBook().get(i).getId());
        }
        for (int i = 0; i < staff3.getBook2().size(); i++) {
            Assert.assertEquals(staff3.getBook().get(i).getCode(), pack.getBook().get(i).getCode());
            Assert.assertEquals(staff3.getBook().get(i).getId(), pack.getBook().get(i).getId());
        }
    }


    @Test
    public void testStaff2() {
        Staff2 staff2 = new Staff2();
        staff2.setId(33);
        staff2.setName("Jack");
        Book book = new Book();
        book.setCreateDate(new Date());
        book.setId(23);
        staff2.setBook(book);

        Book book2 = new Book();
        book2.setCreateDate(new Date());
        book2.setId(24);
        staff2.setBook2(book2);

        byte[] bytes = MagicByte.unpackToByte(staff2);
        Staff2 pack = MagicByte.pack(bytes, Staff2.class);

        Assert.assertEquals(staff2.getBook().getId(), pack.getBook().getId());
        Assert.assertEquals(staff2.getBook2().getId(), pack.getBook2().getId());
        Assert.assertEquals(staff2.getId(), pack.getId());
        Assert.assertEquals(staff2.getName(), pack.getName());

    }


    @Test
    public void testStaff1() {
        Staff1 staff1 = new Staff1();
        staff1.setId(33);
        staff1.setName("Jack");
        Book book = new Book();
        book.setCreateDate(new Date());
        book.setId(23);
        staff1.setBook(book);

        Book book2 = new Book();
        book2.setCreateDate(new Date());
        book2.setId(24);
        staff1.setBook2(book2);

        byte[] bytes = MagicByte.unpackToByte(staff1);
        Staff1 pack = MagicByte.pack(bytes, Staff1.class);

        Assert.assertEquals(staff1.getBook().getId(), pack.getBook().getId());
        Assert.assertEquals(staff1.getBook2().getId(), pack.getBook2().getId());
        Assert.assertEquals(staff1.getId(), pack.getId());
        Assert.assertEquals(staff1.getName(), pack.getName());

    }




}
package com.github.misterchangray.core.common;


import com.github.misterchangray.core.MagicByte;
import com.github.misterchangray.core.common.entity.Classes;
import com.github.misterchangray.core.common.entity.Student;
import com.github.misterchangray.core.common.entity.Teacher;
import com.github.misterchangray.core.common.entity.custom.OrderConfuse;
import org.junit.Assert;
import org.junit.Test;

import java.nio.ByteBuffer;


/**

 常规测试, 这里的数据都是完整的 符合定义标准的

 */
public class NormalTest {


    /**
     * test byte.
     *
     */
    @Test
    public void testOrderConfuse() {
        OrderConfuse orderConfuse = new OrderConfuse();
        orderConfuse.setA(1);
        orderConfuse.setB(2);
        orderConfuse.setC(3);

        ByteBuffer unpack = MagicByte.unpack(orderConfuse);
        unpack.position(0);
        Assert.assertEquals(unpack.getInt(), 2);
        Assert.assertEquals(unpack.getInt(), 3);
        Assert.assertEquals(unpack.getInt(), 1);
        OrderConfuse pack  = MagicByte.pack(unpack.array(), OrderConfuse.class);
        Assert.assertEquals(orderConfuse.getA(), pack.getA());
        Assert.assertEquals(orderConfuse.getB(), pack.getB());
        Assert.assertEquals(orderConfuse.getC(), pack.getC());
    }


    /**
     * test byte.
     *
     */
    @Test
    public void testClasses() {
        Classes classes = Classes.build(1).get(0);


        byte[] unpack = MagicByte.unpackToByte(classes);
        Classes pack = MagicByte.pack(unpack, Classes.class);


        for (int i = 0; i < classes.getTeacher().length; i++) {
            Teacher teacher1 = classes.getTeacher()[i];
            Teacher teacher2 = pack.getTeacher()[i];

            Assert.assertEquals(teacher1.getId(), teacher2.getId());
            Assert.assertEquals(teacher1.getName(), teacher2.getName());
            Assert.assertArrayEquals(teacher1.getPhones(), teacher2.getPhones());
        }


        for (int i = 0; i < classes.getStudentList().size(); i++) {
            Student student1 = classes.getStudentList().get(i);
            Student student2 = pack.getStudentList().get(i);

            Assert.assertEquals(student1.getName(), student2.getName());
            Assert.assertEquals(student1.getPhone(), student2.getPhone());
            Assert.assertArrayEquals(student1.getBookids(), student2.getBookids());
        }
    }




    /**
     * test byte.
     *
     */
    @Test
    public void testStudent() {
        Student student = Student.build(1).get(0);
        byte[] unpack = MagicByte.unpackToByte(student);
        Student pack = MagicByte.pack(unpack, Student.class);
        Assert.assertEquals(student.getName(), pack.getName());
        Assert.assertEquals(student.getPhone(), pack.getPhone());
        Assert.assertArrayEquals(student.getBookids(), student.getBookids());
    }




    /**
     * test byte.
     *
     */
    @Test
    public void testTeacher() {
        Teacher teacher = Teacher.build(2).get(0);

        byte[] unpack = MagicByte.unpackToByte(teacher);
        Teacher pack = MagicByte.pack(unpack, Teacher.class);
        Assert.assertEquals(teacher.getId(), pack.getId());
        Assert.assertEquals(teacher.getName(), pack.getName());
        Assert.assertArrayEquals(teacher.getPhones(), pack.getPhones());
    }


}

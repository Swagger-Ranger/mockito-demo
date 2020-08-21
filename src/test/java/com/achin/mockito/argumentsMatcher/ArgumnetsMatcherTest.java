package com.achin.mockito.argumentsMatcher;

import javafx.scene.Parent;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    ArgumnetsMatcherTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/30 11:06 </p>
 * <p>@Description:  </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

//这里静态方法导入，就可以不用加Runner
public class ArgumnetsMatcherTest {

    /**
     * 参数匹配的基本使用
     */
    @Test
    public void basicTest() {
        List<Integer> list = mock(ArrayList.class);
        when(list.get(0)).thenReturn(100);
        assertThat(list.get(0), equalTo(100));
        assertThat(list.get(1), nullValue());
    }

    /**
     * isA ,any
     * isA:只要是其实例或者是其子类的实例
     * Any:只要是其实例或者是继承了相同的父类
     */
    @Test
    public void complexTest() {
        Foo foo = mock(Foo.class);
        when(foo.function(Mockito.isA(Child_1.class))).thenReturn(100);
        int result = foo.function(new Child_1());
        assertThat(result, equalTo(100));

        result = foo.function(new Child_2());
        assertThat(result, equalTo(0)); //当when传入的是Child时isA不能处理兄弟类，所以会返回0，默认的基本类型的初始值

        reset(foo);

        when(foo.function(Mockito.any(Child_1.class))).thenReturn(100);
        result = foo.function(new Child_2());
        assertThat(result, equalTo(100));
    }


    static class Foo {
        int function( Parent p ) {
            return p.work();
        }
    }

    interface Parent {
        int work();
    }

    class Child_1 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }

    class Child_2 implements Parent {
        @Override
        public int work() {
            throw new RuntimeException();
        }
    }


}































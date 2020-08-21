package com.achin.mockito.spy;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    SpyingAnnotationTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/30 1:03 </p>
 * <p>@Description: spy的注解方式 </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

public class SpyingAnnotationTest {

    /**
     * 使用spy注解
     * 本质上spy就是一个代理模式,传入一个对象给代理类,当调用代理类时真正调的是代理类的方法.此时就不需要先去写一个原类再写一个代理类spy,可以写的更简洁
     */
    @Spy
    private List<String> list = new ArrayList<>();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSpy() {
        list.add("swagger");
        list.add("ranger");

        /**
         * 当使用spy的对象,操作后是会生效的,也就是操作的就是原对象
         */
        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.get(0), equalTo("swagger"));
        assertThat(list.get(1), equalTo("ranger"));

        //对部分方法进行mock
        when(list.isEmpty()).thenReturn(true);
        when(list.size()).thenReturn(0);

        //原来的方法不会受影响,只有stub的方法才会受影响
        assertThat(list.get(0), equalTo("swagger"));
        assertThat(list.get(1), equalTo("ranger"));
        assertThat(list.isEmpty(), equalTo(true));
        assertThat(list.size(), equalTo(0));
    }
}

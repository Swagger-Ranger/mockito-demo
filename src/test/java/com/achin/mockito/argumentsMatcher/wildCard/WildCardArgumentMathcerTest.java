package com.achin.mockito.argumentsMatcher.wildCard;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    WildCardArgumentMathcerTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/30 14:22 </p>
 * <p>@Description:  </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

@RunWith(MockitoJUnitRunner.class)
public class WildCardArgumentMathcerTest {

    @Mock
    private DemoService demoService;

    @Test
    public void wildCardMethod_1() {
        when(demoService.method_1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);
        int result = demoService.method_1(1, "achin", Collections.emptyList(), "swagger");
        assertThat(result, equalTo(100));

    }

    /**
     * 在wildCard中，有些要全部指定类型，而有些要指定到具体的值时的写法
     */
    @Test
    public void wildCardMethodWithSpec() {
        //下面的when是个错误的写法，如果stub使用了wildCard写法，就要全部用wildCard的写法
//        when(demoService.method_1(anyInt(), "swagger", anyCollection(), isA(Serializable.class))).thenReturn(100);

        //正确的用法是使用eq来指定特定的写法
        when(demoService.method_1(anyInt(), eq("swagger"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(demoService.method_1(anyInt(), eq("ranger"), anyCollection(), isA(Serializable.class))).thenReturn(200);
        int result = demoService.method_1(1, "swagger", Collections.emptyList(), "swagger");
        assertThat(result, equalTo(100));

        result = demoService.method_1(1, "ranger", Collections.emptyList(), "swagger");
        assertThat(result, equalTo(200));
    }

    /**
     * 没有返回的情况
     */
    @Test
    public void wildCardMethod_2() {
        doNothing().when(demoService).method_2(anyInt(),anyString(), anyCollection(), isA(Serializable.class));

        //下面在不同地方ollections.emptyList()时返回对比的对象不一定就是相同的，
        List list = Collections.emptyList();
        demoService.method_2(1, "swagger", list, "ranger");
        //下面两个verify都是等价的，当写法不能混用
        verify(demoService, times(1)).method_2(1, "swagger", list, "ranger");
        verify(demoService, times(1)).method_2(anyInt(), eq("swagger"), anyCollection(), isA(Serializable.class));

    }


    @After
    public void destroy() {
        reset(demoService);
    }
}

























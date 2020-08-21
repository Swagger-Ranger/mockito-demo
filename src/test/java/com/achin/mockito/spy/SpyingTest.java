package com.achin.mockito.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    SpyingTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/30 0:46 </p>
 * <p>@Description: mockito的spy操作 </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

@RunWith(MockitoJUnitRunner.class)
public class SpyingTest {

    @Test
    public void testSpy() {
        List<String> realList = new ArrayList<>();
        List<String> list = spy(realList);//新建一个list后,使用其来spy一个list对象

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

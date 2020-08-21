package com.achin.mockito.deepMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    DeepMockTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/29 22:08 </p>
 * <p>@Description:  </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

public class DeepMockTest {

//    @Mock
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)//deepMock的用法,他会把mock出来的对象中要返回的对象也mock出来
    private DeepMockServiceClass deepMockServiceClass;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDeepMock() {
        /**
         * 这里如果不使用deepMcok方式的话,这里mock出的DeepMockClass必定会抛出空指针异常
         * 如果想正确使用,就要自己mock出一个deepMockClass类并且写出when thenReturn的桩,而deepmock这可以完成这一步
         */
//        DeepMockClass deepMockClass = deepMockServiceClass.get();
//        deepMockClass.foo();

        deepMockServiceClass.get().foo();//在使用了deepMock之后deepMockServiceClass就不会抛出空指针异常


    }
}

package com.achin.mockito.stubbing;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    StubbingTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/29 22:46 </p>
 * <p>@Description:  </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    private List<String> list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    /**
     * 基本的stub操作,when...thenReturn
     */
    @Test
    public void howToUseStubbing() {
        //使用stub来设置对象
        when(list.get(0)).thenReturn("first");
        assertThat(list.get(0), equalTo("first"));

        //使用stub来设置抛出异常
        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();//如果没有抛出异常则执行失败
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    /**
     * 如何对返回值为void的方法插桩stub,when...thenReturn显然不行,因为没有返回,没办法判断
     * 方法就是使用verify,验证没有返回值的方法是否被正确执行
     */
    @Test
    public void howToSubbingVoidMethod() {
        /**
         * doNothing就是什么都不做,即当when执行指定操作时什么都不返回也不执行任何方法
         */
        doNothing().when(list).clear();
        list.clear();//这里调用clear,如果上一句stub正确的话mock的东西什么都不会被执行
        list.clear();//这里调用clear,如果上一句stub正确的话mock的东西什么都不会被执行
        verify(list, times(2)).clear();//这里times是执行次数,这里是2所以上面要执行两次才能成功


        /**
         * doThrow就是方法没有返回,mock时执行后就抛出异常的方法
         */
        doThrow(RuntimeException.class).when(list).clear();
        try {
            list.clear();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    /**
     * 和when...thenReturn相似的方法
     * doReturn(返回值).then(操作的对象).method(params...);后面的就是对象要调的方法及传参
     */
    @Test
    public void stubbingDoReturn() {
        when(list.get(0)).thenReturn("first");
        doReturn("second").when(list).get(1);
        assertThat(list.get(0), equalTo("first"));
        assertThat(list.get(1), equalTo("second"));
    }

    /**
     * 链式调用
     * 即第一次调用返回a,第二次返回b,第三次返回c...
     */
    @Test
    public void iterateStubbing() {
        //如果这样写每次调用的结果会被最后一次给覆盖
//        when(list.size()).thenReturn(1);
//        when(list.size()).thenReturn(2);
//        when(list.size()).thenReturn(3);
//        when(list.size()).thenReturn(4);
//        assertThat(list.size(),equalTo(1));//第一次调用也会是4

        //正确写法
        when(list.size()).thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4);
        //上面的链式可以简化为
        when(list.size()).thenReturn(1, 2, 3, 4);
        assertThat(list.size(),equalTo(1));
        assertThat(list.size(),equalTo(2));
        assertThat(list.size(),equalTo(3));
        assertThat(list.size(),equalTo(4));//后面再调用就是最后的设置结果
        assertThat(list.size(),equalTo(4));
        assertThat(list.size(),equalTo(4));
    }

    /**
     * 对返回的值编写返回逻辑,比如当list中取第n个就返回n*10的字符串
     */
    @Test
    public void stubbingWithAnswer() {
        //thenAnswer 传来的是Answer接口,可以使用lambda表达式
        /*when(list.get(anyInt())).thenAnswer(new Answer<String>() {
            public String answer( InvocationOnMock invocationOnMock ) throws Throwable {
                return null;
            }
        });*/
        when(list.get(anyInt())).thenAnswer(invocationOnMock ->{
            Integer index = invocationOnMock.getArgumentAt(0, Integer.class);//拿到第n个参数,并设置返回对象为Integer
            return String.valueOf(index * 10);
        });

        assertThat(list.get(0),equalTo("0"));
        assertThat(list.get(999),equalTo("9990"));
        assertThat(list.get(12),equalTo("120"));
    }

    /**
     * realCall,当使用mock时生成的对象是由CGlib生成的代理类,在调方法时并不是真的在调原来的类的方法
     * 有些方法我要mock,而有些方法我要调原方法,此时就需要使用realCall
     */
    @Test
    public void stubbingWithRealCall() {
        StubbingService service = mock(StubbingService.class);
        System.out.println(service.getClass());//可以看到打印中有CGlib的标识
        service.get_s();//并没有去调原方法不会抛出异常
        System.out.println(service.get_i());//结果为0,不是10;没有调原方法

        //这里是stub的数据
        when(service.get_s()).thenReturn("Achin");
        assertThat(service.get_s(), equalTo("Achin"));



        //这里是真实的调用,会正确的打印
        when(service.get_i()).thenCallRealMethod();
        assertThat(service.get_i(), equalTo(10));

    }

    @After
    public void destroy() {
        reset(this.list);//将我mockito出来的对象销毁调
    }
}

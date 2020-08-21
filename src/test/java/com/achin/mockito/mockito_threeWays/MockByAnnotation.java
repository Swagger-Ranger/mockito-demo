package com.achin.mockito.mockito_threeWays;

import com.achin.mockito.quickStart.Account;
import com.achin.mockito.quickStart.AccountDao;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    MockByAnnotation </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/29 16:28 </p>
 * <p>@Description: 使用注解来mock </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

public class MockByAnnotation {

    @Before
    /**
     * 注意如果不使用这个来初始化,则@mock注解mock出的对象都是null的,调用就会抛出空指针异常
     */
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

//    @Mock//如果不指定answer则返回的mock对象仍然是null
    @Mock(answer = Answers.RETURNS_SMART_NULLS)//这就等于runnerTest方法中的mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS)
    private AccountDao accountDao;

    @Test
    public void testMock() {
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);
    }
}

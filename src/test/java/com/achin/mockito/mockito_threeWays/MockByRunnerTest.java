package com.achin.mockito.mockito_threeWays;

import com.achin.mockito.quickStart.Account;
import com.achin.mockito.quickStart.AccountDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    MockByRunnerTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/29 16:18 </p>
 * <p>@Description:  通过RunnerTest mock</p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

//@RunWith(MockitoJUnitRunner.class)
public class MockByRunnerTest {

    @Test
    public void testMock() {
        AccountDao accountDao = mock(AccountDao.class);//如果这样mock,得到的对象是个null
        AccountDao accountDaoNotNull = mock(AccountDao.class, Mockito.RETURNS_SMART_NULLS);//如果这样mock,得到的对象是调用方法的字符串

        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);

        Account accountNotNull = accountDaoNotNull.findAccount("x", "x");
        System.out.println(accountNotNull);

    }
}

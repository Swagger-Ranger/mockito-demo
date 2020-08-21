package com.achin.mockito.mockito_threeWays;

import com.achin.mockito.quickStart.Account;
import com.achin.mockito.quickStart.AccountDao;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.mock;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    MockByRuleTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/29 21:48 </p>
 * <p>@Description: 使用Rule注解来mock </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

public class MockByRuleTest {

    @Rule //使用Rule来声明Mock,使用@Mock注解mock出来的对象就不会是null了
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private AccountDao accountDao;

    @Test
    public void testMock() {
//        AccountDao accountDao = mock(AccountDao.class);//这就等于@Mock private AccountDao accountDao;
        Account account = accountDao.findAccount("x", "x");
        System.out.println(account);

    }
}

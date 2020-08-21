package com.achin.mockito.quickStart;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    AccountLoginControllerTest </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/29 15:49 </p>
 * <p>@Description:  mockito的快速上手</p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

@RunWith(MockitoJUnitRunner.class)
public class AccountLoginControllerTest {

    private AccountDao accountDao;
    private HttpServletRequest request;
    private AccountLoginController accountLoginController;

    @Before
    public void setUp() {
        this.accountDao = Mockito.mock(AccountDao.class);
        this.request = Mockito.mock(HttpServletRequest.class);
        this.accountLoginController = new AccountLoginController(accountDao);//这个对象不是mock的,而是使用的编写要测试的类
    }

    @Test
    public void testLoginSuccess() {
        Account account = new Account();
        when(request.getParameter("username")).thenReturn("achin");
        when(request.getParameter("password")).thenReturn("123456");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(account);
        assertThat(accountLoginController.login(request), equalTo("/index"));
    }

    @Test
    public void testLoginFailure() {
        when(request.getParameter("username")).thenReturn("achin");
        when(request.getParameter("password")).thenReturn("12345611");
        when(accountDao.findAccount(anyString(), anyString())).thenReturn(null);
        assertThat(accountLoginController.login(request), equalTo("/login"));
    }

    @Test
    public void testLogin505() {
        when(request.getParameter("username")).thenReturn("achin");
        when(request.getParameter("password")).thenReturn("12345611");
        when(accountDao.findAccount(anyString(), anyString())).thenThrow(UnsupportedOperationException.class);

        assertThat(accountLoginController.login(request), equalTo("505"));
    }

}

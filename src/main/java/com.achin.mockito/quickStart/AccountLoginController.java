package com.achin.mockito.quickStart;

import javax.servlet.http.HttpServletRequest;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    AccountLoginController </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/29 15:40 </p>
 * <p>@Description:  </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

public class AccountLoginController {

    private final AccountDao accountDao;

    public AccountLoginController( AccountDao accountDao ) {
        this.accountDao = accountDao;
    }

    public String login( HttpServletRequest request ) {
        final String userName = request.getParameter("username");
        final String password = request.getParameter("password");

        try {

            Account account = accountDao.findAccount(userName, password);
            if (account == null) {
                return "/login";
            } else {
                return "/index";
            }
        } catch (Exception e) {
            return "505";
        }
    }
}

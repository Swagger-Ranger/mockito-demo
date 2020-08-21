package com.achin.mockito.stubbing;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    StubbingService </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/30 0:28 </p>
 * <p>@Description: stubbing的realCall要使用的类 </p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

public class StubbingService {

    public int get_i() {
        System.out.println("---- get i ----");
        return 10;
    }

    public String get_s() {
        System.out.println("---- get s ----");
        throw new RuntimeException();
    }
}

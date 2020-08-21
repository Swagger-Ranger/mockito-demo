package com.achin.mockito.argumentsMatcher.wildCard;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

/*******************************************************************************
 * <p>@Copyright (C), 2018-2019,github:Swagger-Ranger </p>
 * <p>@FileName:    DemoService </p>
 * <p>@Author:      liufei32@outlook.com </p>
 * <p>@Date:        2019/12/30 14:17 </p>
 * <p>@Description:  更复杂的类，复杂的入参</p>
 * <p>@Aha-eureka: </p>
 ******************************************************************************/

public class DemoService {

    /**
     * 一个具有复杂入参的实例类的方法
     * @param i
     * @param s
     * @param c
     * @param ser 这里传入ser只是为了传入一个标记接口
     * @return
     */
    public int method_1( int i, String s, Collection<?> c, Serializable ser ) {
        throw new RuntimeException();
    }

    public void method_2( int i, String s, Collection<?> c, Serializable ser ) {
        throw new RuntimeException();
    }

}























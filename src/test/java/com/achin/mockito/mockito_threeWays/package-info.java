/**
 * mockito的3中方式
 * 1.@RunWith(MockitoJUnitRunner.class) 在测试类上使用此注解,借助Junit的runwith功能就能够激活mockito的mock功能
 * 2.MockitoAnnotations.initMocks(this) 使用mockito的annotation,这里需要去写一个@Before先执行的initMocks方法来初始化annotation初始化,这样就能使用@Mock注解,否则@Mock出的对象为null
 *  并将测试类对象传进去
 * 3.@Rule public MockitoRule mockitoRule = MockitoJUnit.rule(); 这里的MockitoRule是public的,还有MockitoRule对象也不会使用
 */
package com.achin.mockito.mockito_threeWays;
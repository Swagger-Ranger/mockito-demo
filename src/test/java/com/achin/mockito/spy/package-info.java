/**
 * spy的操作和mock刚好相反
 * mock:是mock出的对象是不会去操作原对象的,除非你stub,就是插桩设置对应的when...thenReturn等等.要调用真的原方法就要realCallMethod()
 * spy:则是正常的操作都是在操作原对象,除非你单独stub设置了才会去使用spy的对象;即spy是对部分方法的mock
 */
package com.achin.mockito.spy;
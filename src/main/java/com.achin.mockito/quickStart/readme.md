# mockito 快速学习

这个包里的文件是测试mockito的基础类,模拟用户登陆的情况.对应 的test类在test包中

### mockito_threeWays
开启mockito mock的三种方式

### deepmock
当mock一个对象之后,对象具备的属性也进行mock,不然默认mock出来对象的属性是null的,
但当mock对象属性为private时,如果要mock私有属性需要使用set方式,也就是mockito FieldSet设置,在不同版本的mockito中略有不同,其中一种方式是:使用mockito的whitebox
```java
    Object 新mock出的对象 = mock(class);
    Whitebox.setInternalState(mock的对象实例,对应field的字符串名,新mock出的对象);
    //然后正常的对新mock出的对象进行stubing
```
### spy
spy和mock正好相反,mock出的都是假的,要使用真的方法需要调其realCall方法,但spy则是spy出来都是真的,走到stub时,才会不用原来的方法

### stubbing
插桩的用法

### argumentsMatcher
参数匹配
#### wildCard
可变灵活的入参
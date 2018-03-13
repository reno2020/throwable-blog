# 这个博客用到的技术栈
## 后台
- jdk：1.8
- kotlin：1.2.30（**人生苦短，我用kotlin**）
- springboot：2.0.0.RELEASE
- 其他：druid、mysql、freemarker、mybatis等

## 前端
我前端比较水，随便拼凑的页面。

- bootstrap
- jquery
- 其他js插件（多数来自github），这里不一一列出

# 遇到的问题
- \[toc\]生成插件找了很久找不到合适的，后来找到了[i5ting_ztree_toc ](https://github.com/i5ting/i5ting_ztree_toc)

# 彩蛋
下面贴出一句springboot基于kotlin下的启动函数：
```kotlin
//测试一下代码块
@SpringBootApplication
class Application

fun main(vararg args: String) {
    runApplication<Application>(* args)
}
```
package java.lang;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description : 双亲委派机制的栗子（沙箱安全机制）
 * @CreateTime : 2020.04.29 22:35
 **/
public class String {

    static {
        System.out.println("能看到我，就说明这个String类被加载了~");
    }

    // 运行会报错，因为双亲委派机制，最终由启动类加载器(BootstrapClassLoader)加载。
    // 加载的是java核心包里的String类，该类并没有main方法，所以会报错
    // 错误: 在类 java.lang.String 中找不到 main 方法
    public static void main(String[] args) {
        System.out.println("QAQ");
    }

}

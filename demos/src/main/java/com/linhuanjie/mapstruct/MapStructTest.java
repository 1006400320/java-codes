package com.linhuanjie.mapstruct;

/**
 * todo 试用一下
 * https://blog.csdn.net/zhige_me/article/details/80699784
 * @author : linhuanjie
 * @email : lhuanjie@qq.com
 * @create : 2020.05.05 17:53
 **/
public class MapStructTest {
    public static void main(String[] args) {
        // 仿造一个请求的对象
        UserDemoAddDTO addDTO = new UserDemoAddDTO();
        addDTO.setName("张三");
        addDTO.setEmail("zhangsan@qq.com");
        addDTO.setAddress("北京市朝阳区");
        addDTO.setPhone("110");
        System.out.println("addDTO = " + addDTO.toString());

        UserDemoDO convert = UserDemoConvert.INSTANCE.convert(addDTO);
        System.out.println("convert = " + convert.toString());

    }
}

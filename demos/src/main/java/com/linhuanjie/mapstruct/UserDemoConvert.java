package com.linhuanjie.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author : linhuanjie
 * @email : lhuanjie@qq.com
 * @create : 2020.05.05 18:03
 **/
@Mapper
public interface UserDemoConvert {

    UserDemoConvert INSTANCE = Mappers.getMapper(UserDemoConvert.class);

    /**
     * addDTO --> DO
     */
    @Mappings({})
    UserDemoDO convert(UserDemoAddDTO userDemoAddDTO);

}

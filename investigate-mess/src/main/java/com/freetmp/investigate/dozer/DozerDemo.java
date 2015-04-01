package com.freetmp.investigate.dozer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.Arrays;
/**
 * Created by LiuPin on 2015/3/30.
 */
public class DozerDemo {

    public static void mapEnum(Mapper mapper){
        UserGroup userGroup = new UserGroup();
        userGroup.setStatus(Status.ERROR);

        UserGroupPrime userGroupPrime = mapper.map(userGroup,UserGroupPrime.class);

        print(userGroup,userGroupPrime);
    }

    public static void print(Object src, Object dest){
        System.out.println(ToStringBuilder.reflectionToString(src) + " ----> " + ToStringBuilder.reflectionToString(dest));
    }

    public static void main(String[] args) {
        Mapper mapper = new DozerBeanMapper(Arrays.asList("dozer/EnumMapping.xml"));
        mapEnum(mapper);
    }

}

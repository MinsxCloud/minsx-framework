package com.minsx.starter.test;

import com.alibaba.fastjson.JSON;
import com.minsx.core.common.entity.base.type.AuthType;
import org.junit.Test;

public class OtherTest {

    @Test
    public void test(){
        System.out.println(JSON.toJSONString(AuthType.BUTTON));
    }


}

/* ******************************************************
 * Copyright (C) 2016 iQIYI.COM - All Rights Reserved
 *
 * This file is part of qixiao-passport.
 * Unauthorized copy of this file, via any medium is strictly prohibited.
 * Proprietary and Confidential
 *
 * @Date: 2017/11/10
 * @Author: zhaoxu <zhaoxu@qiyi.com>
 * ******************************************************/
package com.hyl.qixiao.http;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PathEnumVariable {
    String value() default "";
}

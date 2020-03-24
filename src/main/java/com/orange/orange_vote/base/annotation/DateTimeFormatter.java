package com.orange.orange_vote.base.annotation;

import com.orange.orange_vote.base.enums.DateTimeFormatType;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeFormatter {

    DateTimeFormatType value() default DateTimeFormatType.DEFAULT;

}

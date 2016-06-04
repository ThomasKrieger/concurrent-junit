package com.anarsoft.vmlens.concurrent.junit;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

@Retention(value=RUNTIME)
public @interface ThreadCount {
     int value();
}

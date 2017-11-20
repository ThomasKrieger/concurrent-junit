package com.anarsoft.vmlens.concurrent.junit;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.*;

/**
 * 
 * Annotation for a test method. The annotated test method will be run by ThreadCount threads. 
 * 
 * @author thomas
 *
 */


@Retention(value=RUNTIME)
public @interface ThreadCount {
     
	/** 
     * the number of threads to use
     */
	int value();
     
}

package com.example.zzy.springbootTest.util;

import com.google.common.base.Preconditions;

public final class NullCheckUtil {

	public static void checkNotNull(Object... params) {
		for(Object obj : params){
			Preconditions.checkNotNull(obj);
		}
	}

}

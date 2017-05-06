package com.yp.common.convertor;

import org.springframework.beans.BeanUtils;

/**
 * 类转换基础类
 * @author zhiya.chai
 *
 */
public class BaseConvertor {

	protected static void copyProperties(Object source, Object target, String... ignoreProperties) {
		try {
			BeanUtils.copyProperties(source, target, ignoreProperties);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}

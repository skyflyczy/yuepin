/**
 * 
 */
package com.yp.mix.support;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.yp.common.encrypt.SymmetricEncrypt;


/**
 * 配置文件加载扩展
 * 
 * @author yuhui.tang 2013-4-28 上午3:46:55
 */
public class PropertyPlaceholderConfigurerExt extends
		PropertyPlaceholderConfigurer {
	private static String DbUrl ="Core_DB.url";
	private static String DbUserKey ="Core_DB.user";
	private static String DbPasswdKey ="Core_DB.password";
	private static String RabbitMqUserName = "rabbitMq.userName";
	private static String RabbitMqPassword = "rabbitMq.password";
	private static String RabbitMqUrl = "rabbitMq.address";
	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		//-----------解决用户、密码恢复的问题
		String url = props.getProperty(DbUrl);
		String user = props.getProperty(DbUserKey);
		String password = props.getProperty(DbPasswdKey);
		String rabbitUserName = props.getProperty(RabbitMqUserName);
		String rabbitMqPassword = props.getProperty(RabbitMqPassword);
		String rabbitMqUrl = props.getProperty(RabbitMqUrl);
		if(StringUtils.isNotBlank(url)) {
			props.setProperty(DbUrl, SymmetricEncrypt.decryptStr(url));
		}
		if(StringUtils.isNotBlank(user)) {
			props.setProperty(DbUserKey,SymmetricEncrypt.decryptStr(user));
		}
		if(StringUtils.isNotBlank(password)) {
			props.setProperty(DbPasswdKey,SymmetricEncrypt.decryptStr(password));
		}
		if(StringUtils.isNotBlank(rabbitUserName)) {
			props.setProperty(RabbitMqUserName,SymmetricEncrypt.decryptStr(rabbitUserName));
		}
		if(StringUtils.isNotBlank(rabbitMqPassword)) {
			props.setProperty(RabbitMqPassword,SymmetricEncrypt.decryptStr(rabbitMqPassword));
		}
		if(StringUtils.isNotBlank(rabbitMqUrl)) {
			props.setProperty(RabbitMqUrl,SymmetricEncrypt.decryptStr(rabbitMqUrl));
		}
		super.processProperties(beanFactory, props);
	}

}

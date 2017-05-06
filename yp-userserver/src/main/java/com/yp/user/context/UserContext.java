package com.yp.user.context;

/**
 *
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * @author ming.li
 *
 */
public class UserContext {

    private static UserContext context;

    /**
     * Spring的上下文
     */
    private ApplicationContext springContext;

    private UserContext()
    {
        try {
            springContext = new FileSystemXmlApplicationContext(new String[]{"classpath*:applicationContext..xml"});
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static UserContext getInstance()
    {
        if(context == null)
        {
            context = new UserContext();
        }
        return context;
    }

    /**
     * 获得Spring的上下文
     * @return
     */
    public ApplicationContext getSpringContext()
    {
        return this.springContext;
    }

    /**
     * 获得Spring的bean
     * @param beanName
     * @param clazz
     * @return
     */
    public <T> T getBean(String beanName, Class<T> clazz) {
        return springContext.getBean(beanName, clazz);
    }
}


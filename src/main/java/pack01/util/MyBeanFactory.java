package pack01.util;

import pack01.service.AccountService;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

//1:工厂类
public class MyBeanFactory {

    //2:容器创建
    private static Map<String,Object> container=new HashMap<String,Object>();
    private  static   Properties properties;

    //3:静态代码块中加载
    static {
        //加载类型信息
        InputStream inputStream =  MyBeanFactory.class.getResourceAsStream("/mybean.properties");
       //解析
         properties=new Properties();
        try {
            properties.load(inputStream);
            //可以这里初始化对象，放到容器中，也可以在使用时再初始化
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //4：查找对象
    public static Object getBean(String id, String singleton) {
        if("singleton".equals(singleton)){
            //查找
            Object obj = container.get(id);

            if( obj != null){
                return obj;
            }else{
                //容器里面没有对象
                String className = properties.getProperty(id);
                Class czl = null;
                try {
                    czl = Class.forName(className);
                    Object o = czl.newInstance();
                    //添加到容器中
                    container.put(id,o);
                    return o;
                } catch (Exception e) {

                }


            }
        }else{
            //多实例
            //容器里面没有对象
            String className = properties.getProperty(id);
            Class czl = null;
            try {
                czl = Class.forName(className);
                Object o = czl.newInstance();
                return o;
            }catch (Exception e){

            }

        }
        return null;
    }
    //5:创建配置文件
}

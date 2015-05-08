package com.ccw.testonline.common.utils;

import java.lang.reflect.ParameterizedType;  
import java.lang.reflect.Type;  
/**
 * 
 * 
 * Title: TestOnline <br>
 * Description: 通过反射得到类型<br>
 * 
 * @author <a href="mailto:775302619@qq.com">陈炽伟</a><br>
 * @e-mail: 775302619@qq.com <br>
 * @version 1.0 <br>
 * @creatdate 2014年10月6日 上午9:35:43 <br>
 *
 */
public class GenericsUtils {  
    /**   
     * 通过反射,获得定义Class时声明的父类的范型参数的类型.   
     * 如public BookManager extends GenricManager<Book>   
     *   
      * @param clazz The class to introspect   
      * @return the first generic declaration, or <code>Object.class</code> if cannot be determined   
      */  
     public static Class getSuperClassGenricType(Class clazz) {  
         return getSuperClassGenricType(clazz, 0);  
     }  
   
     /**   
      * 通过反射,获得定义Class时声明的父类的范型参数的类型.   
      * 如public BookManager extends GenricManager<Book>   
      *   
      * @param clazz clazz The class to introspect   
      * @param index the Index of the generic ddeclaration,start from 0.   
      */  
     public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {  
   
         Type genType = clazz.getGenericSuperclass();  
         if (!(genType instanceof ParameterizedType)) {  
             return Object.class;  
         }  
         
         Type[] params = ((ParameterizedType) genType).getActualTypeArguments();  
   
         if (index >= params.length || index < 0) {  
             return Object.class;  
         }  
         if (!(params[index] instanceof Class)) {  
             return Object.class;  
         }  
         return (Class) params[index];  
     }  
 }  
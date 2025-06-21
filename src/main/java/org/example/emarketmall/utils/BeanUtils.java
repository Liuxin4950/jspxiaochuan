package org.example.emarketmall.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Bean 工具类
 */
public class BeanUtils extends org.apache.commons.beanutils.BeanUtils {
    /**
     * Bean方法名中属性名开始的下标
     */
    private static final int BEAN_METHOD_PROP_INDEX = 3;

    /**
     * 匹配getter方法的正则表达式
     */
    private static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");

    /**
     * 匹配setter方法的正则表达式
     */
    private static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * Bean属性复制工具方法。
     *
     * @param dest 目标对象
     * @param src  源对象
     */
    public static void copyBeanProp(Object dest, Object src) {
        try {
            copyProperties(src, dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 把map的内容拷贝给Java对象
     *
     * @param src
     * @param target
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T copyMap2Object(Map src, Class<T> target) throws IllegalAccessException, InstantiationException {
        Field[] fields = target.getDeclaredFields();
        T ret = target.newInstance();
        for (Field field : fields) {
            Object value = src.get(field.getName());

            if (value != null) {
                field.setAccessible(true);
                // 处理基本类型和包装类型的转换
                try {
                    if (field.getType().isPrimitive() && value == null) {
                        // 基本类型遇到null时赋予默认值
                        setPrimitiveDefaultValue(field, ret);
                    } else {
                        // 非基本类型或值不为null时正常设置
                        field.set(ret, ConvertUtils.convert(value, field.getType()));
                    }
                } catch (IllegalArgumentException e) {
                    // 类型转换失败处理
                    handleTypeConversionError(field, value, ret);
                }
            }
        }
        return ret;
    }

    // 类型转换失败的处理
    private static <T> void handleTypeConversionError(Field field, Object value, T target) {
        try {
            // 尝试使用Apache Commons的ConvertUtils进行转换
            Object convertedValue = org.apache.commons.beanutils.ConvertUtils.convert(value.toString(), field.getType());
            field.set(target, convertedValue);
        } catch (Exception e) {
            System.err.println(String.format(
                    "Failed to convert value '%s' to type %s for field %s",
                    value, field.getType(), field.getName()));
        }
    }

    // 设置基本类型的默认值
    private static <T> void setPrimitiveDefaultValue(Field field, T target)
            throws IllegalAccessException {
        Class<?> type = field.getType();
        if (type == int.class) {
            field.setInt(target, 0);
        } else if (type == double.class) {
            field.setDouble(target, 0.0);
        } else if (type == long.class) {
            field.setLong(target, 0L);
        } else if (type == boolean.class) {
            field.setBoolean(target, false);
        } else if (type == float.class) {
            field.setFloat(target, 0.0f);
        } else if (type == short.class) {
            field.setShort(target, (short) 0);
        } else if (type == byte.class) {
            field.setByte(target, (byte) 0);
        } else if (type == char.class) {
            field.setChar(target, '\u0000');
        }
    }

    /**
     * 判断Bean是否只被实例化，未被赋值
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> boolean isEmpty(T obj) {
        if (obj == null) {
            return true;
        }
        List<String> props = getBeanProperties(obj);
        int count = 0;
        //delFlag->0 getFieldValueByName返回null
        for (String prop : props) {
            if (StringUtils.isEmpty(getFieldValueByName(prop, obj))) {
                count++;
            }
        }
        return count >= props.size();
    }

    /**
     * 获取对象的setter方法。
     *
     * @param obj 对象
     * @return 对象的setter方法列表
     */
    public static List<Method> getSetterMethods(Object obj) {
        // setter方法列表
        List<Method> setterMethods = new ArrayList<Method>();

        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();

        // 查找setter方法

        for (Method method : methods) {
            Matcher m = SET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 1)) {
                setterMethods.add(method);
            }
        }
        // 返回setter方法列表
        return setterMethods;
    }

    /**
     * 获取对象的getter方法。
     *
     * @param obj 对象
     * @return 对象的getter方法列表
     */

    public static List<Method> getGetterMethods(Object obj) {
        // getter方法列表
        List<Method> getterMethods = new ArrayList<Method>();
        // 获取所有方法
        Method[] methods = obj.getClass().getMethods();
        // 查找getter方法
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches() && (method.getParameterTypes().length == 0)) {
                getterMethods.add(method);
            }
        }
        // 返回getter方法列表
        return getterMethods;
    }

    /**
     * 检查Bean方法名中的属性名是否相等。<br>
     * 如getName()和setName()属性名一样，getName()和setAge()属性名不一样。
     *
     * @param m1 方法名1
     * @param m2 方法名2
     * @return 属性名一样返回true，否则返回false
     */

    public static boolean isMethodPropEquals(String m1, String m2) {
        return m1.substring(BEAN_METHOD_PROP_INDEX).equals(m2.substring(BEAN_METHOD_PROP_INDEX));
    }

    /**
     * 返回对象的属性名
     *
     * @param obj
     * @return
     */
    public static List<String> getBeanProperties(Object obj) {
        List<Method> methods = getGetterMethods(obj);
        List<String> properties = new ArrayList<>(methods.size());
        String prop = "";
        String firstLetter = "";
        for (Method method : methods) {
            prop = method.getName().substring(BEAN_METHOD_PROP_INDEX);
            firstLetter = prop.substring(0, 1);
            prop = prop.replaceFirst(firstLetter, firstLetter.toLowerCase());
            properties.add(prop);
        }
        return properties;
    }

    /**
     * 根据属性名获取属性元素，包括各种安全范围和所有父类
     *
     * @param fieldName 属性名
     * @param object    属性所属类
     * @return 属性值
     */
    public static String getFieldValueByName(String fieldName, Object object) {
        Field field = null;
        String ret = null;
        Class<?> clazz = object.getClass();
        //从当前的类开始找，没找到再从父类中找。
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                field.setAccessible(true);
                Object value = field.get(object);
                ret = convertToString(value);
                if (ret.equals("0") || ret.equals("0.00")) {
                    ret = null;
                }
            } catch (Exception e) {
                // 这里甚么都不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会进入
            }
        }
        return ret;

    }

    /**
     * 安全将任意类型转换为String
     */
    private static String convertToString(Object value) {
        if (value == null) {
            return null;
        }
        // 处理Double，Float类型（避免直接转换为String）
        if (value instanceof Double || value instanceof Float) {
            return String.format("%.2f", value); // 浮点数保留两位小数
        }
        // 处理其他常用类型
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        }
        //处理日期类型
        if (value instanceof java.util.Date) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        }

        // 默认转为字符串（适用于String、Enum等）
        return value.toString();
    }

}

package com.media.his.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntityUtil.class);

    /**
     * 忽略字段
     */
    private static List<String> ignore = ImmutableList.of("serialVersionUID");

    /**
     * 转换list
     *
     * @param entities          要转换的list
     * @param covertEntityClass 转换后的 class
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T, S> List<T> convert(List<S> entities, Class<T> covertEntityClass) {
        List<T> covertEntities = new ArrayList<T>();
        if (CollectionUtil.isEmpty(entities)) {
            return covertEntities;
        }
        for (S entity : entities) {
            covertEntities.add(convert(entity, covertEntityClass));
        }
        return covertEntities;
    }

    /**
     * 实体类转换
     * <p>
     * 根据实体内属性名进行映射
     * <p>
     * 属性名相同 默认属性名相同 则认为属性类型也相同 且不为空  则映射
     *
     * @param <T>
     * @param <S>
     * @param entity            被转换实体
     * @param covertEntityClass 转换后的 Class
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T, S> T convert(S entity, Class<T> covertEntityClass) {
        Preconditions.checkNotNull(entity, "entity can't null !");
        Preconditions.checkNotNull(covertEntityClass, "covertEntityClass can't null !");
        try {
            T covertEntity = covertEntityClass.newInstance();

            List<Field> entityFieldList = new ArrayList<>();
            {
                // 获取自身类所用的属性
                entityFieldList.addAll(Arrays.asList(entity.getClass().getDeclaredFields()));
                // 获取父类属性[此处只处理当前类的上一级父类，再高层的父类不会处理]
                if (null != entity.getClass().getSuperclass()) {
                    entityFieldList.addAll(Arrays.asList(entity.getClass().getSuperclass().getDeclaredFields()));
                }
            }

            List<Field> covertFieldList = new ArrayList<>();
            {
                // 获取自身类所用的属性
                covertFieldList.addAll(Arrays.asList(covertEntityClass.getDeclaredFields()));
                // 获取父类属性[此处只处理当前类的上一级父类，再高层的父类不会处理]
                if (null != covertEntityClass.getSuperclass()) {
                    covertFieldList.addAll(Arrays.asList(covertEntityClass.getSuperclass().getDeclaredFields()));
                }
            }


            boolean breakFlag = false;
            for (Field covertField : covertFieldList) {
                covertField.setAccessible(true);
                for (Field entityField : entityFieldList) {
                    if (ignore.contains(entityField.getName())) {
                        // 忽略
                        continue;
                    }
                    entityField.setAccessible(true);
                    if (entityField.getName().equals(covertField.getName())) {//属性名相同
                        if (entityField.get(entity) != null) {//如果不为空 则放入
                            if ((entityField.getModifiers() & Modifier.FINAL) == Modifier.FINAL) {//如果属性是final类型，不赋值
                                break;
                            }
                            covertField.set(covertEntity, entityField.get(entity));
                            breakFlag = true;
                        }
                    }
                    if (breakFlag) {
                        breakFlag = false;
                        break;
                    }
                }

            }
            return covertEntity;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.openwebtechnology.demo.challenge.boat.model;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BoatTest {
        private Boat a = new Boat();
        private Boat b = new Boat();


        @Before("")
        public void init() {
            Stream.of(a.getClass().getDeclaredFields()).forEachOrdered(field -> {
                try {
                    field.setAccessible(true);
                    Method setter = a.getClass().getMethod("set" + StringUtils.capitalize(field.getName()),
                            field.getType());
                    Method getter = a.getClass().getMethod("get" + StringUtils.capitalize(field.getName()));

                    if (field.getType().isAssignableFrom(Long.class))
                        setter.invoke(a, 1l);
                    else
                        setter.invoke(a, field.getType().newInstance());
                    setter.invoke(b, getter.invoke(a));

                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                         | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }

            });
        }


    @Test
    public void testEqualsToString() {
        assertEquals(a, a);
        assertEquals(a, b);
        assertNotEquals(a, null);
        assertEquals(a.toString(), b.toString());
        assertNotNull(a);
        assertNotEquals(a, new Object());
    }

    @Test
    public void hashCodeTest() {
        assertEquals(a.hashCode(), b.hashCode());
    }
    @Test
    public void constructorTest() {
        assertEquals(new Boat("boat1","boat1").getDescription(), new Boat("boat2","boat1").getDescription());
        assertEquals(new Boat(1,"boat1","boat1").getDescription(), new Boat(1,"boat2","boat1").getDescription());
    }

}
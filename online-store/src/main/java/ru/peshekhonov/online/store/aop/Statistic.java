package ru.peshekhonov.online.store.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Aspect
@Component
public class Statistic {

    private AtomicLong cartServiceRunTime;
    private AtomicLong categoryServiceRunTime;
    private AtomicLong orderItemServiceRunTime;
    private AtomicLong orderServiceRunTime;
    private AtomicLong productServiceRunTime;
    private AtomicLong roleServiceRunTime;
    private AtomicLong visitorServiceRunTime;

    @PostConstruct
    public void init() {
        cartServiceRunTime = new AtomicLong();
        categoryServiceRunTime = new AtomicLong();
        orderItemServiceRunTime = new AtomicLong();
        orderServiceRunTime = new AtomicLong();
        productServiceRunTime = new AtomicLong();
        roleServiceRunTime = new AtomicLong();
        visitorServiceRunTime = new AtomicLong();
    }

    public Map<String, Long> get() {
        Map<String, Long> map = new HashMap<>(7);
        map.put("cartService", cartServiceRunTime.get());
        map.put("categoryService", categoryServiceRunTime.get());
        map.put("orderItemService", orderItemServiceRunTime.get());
        map.put("orderService", orderServiceRunTime.get());
        map.put("productService", productServiceRunTime.get());
        map.put("roleService", roleServiceRunTime.get());
        map.put("visitorService", visitorServiceRunTime.get());
        return map;
    }

    @Around("execution(public * ru.peshekhonov.online.store.services.CartService.*(..))")
    public Object calculateCartServiceRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        cartServiceRunTime.addAndGet(System.currentTimeMillis() - begin);
        return out;
    }

    @Around("execution(public * ru.peshekhonov.online.store.services.CategoryService.*(..))")
    public Object calculateCategoryServiceRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        categoryServiceRunTime.addAndGet(System.currentTimeMillis() - begin);
        return out;
    }

    @Around("execution(public * ru.peshekhonov.online.store.services.OrderItemService.*(..))")
    public Object calculateOrderItemServiceRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        orderItemServiceRunTime.addAndGet(System.currentTimeMillis() - begin);
        return out;
    }

    @Around("execution(public * ru.peshekhonov.online.store.services.OrderService.*(..))")
    public Object calculateOrderServiceRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        orderServiceRunTime.addAndGet(System.currentTimeMillis() - begin);
        return out;
    }

    @Around("execution(public * ru.peshekhonov.online.store.services.ProductService.*(..))")
    public Object calculateProductServiceRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        productServiceRunTime.addAndGet(System.currentTimeMillis() - begin);
        return out;
    }

    @Around("execution(public * ru.peshekhonov.online.store.services.RoleService.*(..))")
    public Object calculateRoleServiceRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        roleServiceRunTime.addAndGet(System.currentTimeMillis() - begin);
        return out;
    }

    @Around("execution(public * ru.peshekhonov.online.store.services.VisitorService.*(..))")
    public Object calculateVisitorServiceRunTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        visitorServiceRunTime.addAndGet(System.currentTimeMillis() - begin);
        return out;
    }
}

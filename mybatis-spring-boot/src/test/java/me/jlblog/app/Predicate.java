package me.jlblog.app;

@FunctionalInterface
public interface Predicate<T> {
	boolean test(T t);
}

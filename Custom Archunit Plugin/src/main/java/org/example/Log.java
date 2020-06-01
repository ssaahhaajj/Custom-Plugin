package org.example;

public interface Log {

    boolean isInfoEnabled();

    void info(String s);

    void warn(String toString);
}
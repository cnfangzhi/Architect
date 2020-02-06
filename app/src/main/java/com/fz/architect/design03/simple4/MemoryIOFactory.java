package com.fz.architect.design03.simple4;

/**
 * 运行内存存储的 Factory
 * Created by fz on 2017/9/24.
 */

public class MemoryIOFactory implements IOFactory{
    @Override
    public IOHandler createIOHandler() {
        return new MemoryIOHandler();
    }
}

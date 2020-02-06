package com.fz.architect.design03.simple4;

/**
 * Created by fz on 2017/9/24.
 */

public class PreferencesIOFactory implements IOFactory{
    @Override
    public IOHandler createIOHandler() {
        return new PreferencesIOHandler();
    }
}

package indi.wangsc.hotline.config;

import java.io.File;
import java.util.Map;

public interface Config {
    Map parse(String configFilepath);
}

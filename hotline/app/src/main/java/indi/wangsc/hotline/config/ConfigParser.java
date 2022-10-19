package indi.wangsc.hotline.config;

import java.util.Map;

public interface ConfigParser {
    Map parse(String configFilepath);
}

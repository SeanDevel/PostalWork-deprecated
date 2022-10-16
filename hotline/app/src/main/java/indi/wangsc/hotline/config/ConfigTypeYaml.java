package indi.wangsc.hotline.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ConfigTypeYaml implements Config {

    @Override
    public Map parse(String configFilepath) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        Map result = null;
        try {
            result = mapper.readValue(new File(configFilepath), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

package cn.yongjie.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AppConfig {

    private static Config conf = ConfigFactory.load("application.conf");

    public static String pavastBasePath = conf.getString("pavast.basePath");

    public static String dcmBasePath = conf.getString("dcm.basePath");

    public static String a2lBasePath = conf.getString("a2l.basePath");

    public static String formulaBasePath = conf.getString("formula.basePath");
}

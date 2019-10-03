package cn.yongjie;

import cn.yongjie.service.impl.VariableServiceImpl;
import cn.yongjie.utils.AppConfig;

public class APP {
    public static void main(String[] args) {

        VariableServiceImpl vsi = new VariableServiceImpl();
        vsi.batchParseXML(AppConfig.pavastBasePath);
    }
}

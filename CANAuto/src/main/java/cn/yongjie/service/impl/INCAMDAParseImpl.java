package cn.yongjie.service.impl;

import cn.yongjie.service.INCAMDAParse;
import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class INCAMDAParseImpl implements INCAMDAParse {

    public void parseMDAFileByPythonScript(String scriptPath) {

        PythonInterpreter interpreter = new PythonInterpreter();
        PySystemState sys = Py.getSystemState();
        sys.path.add("D:\\Software\\Anaconda\\Lib\\site-packages");
        InputStream filePy = null;
        try {
            filePy = new FileInputStream(scriptPath);
            interpreter.execfile(filePy);
            filePy.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

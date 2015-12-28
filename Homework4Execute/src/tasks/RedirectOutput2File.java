/*
 * Copyright 2013 Integrity Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * @author Zoltan Papp
 * 
 */
package tasks;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class RedirectOutput2File {

    public static void getUname() {
        String[] cmd;
        File file;

        Properties properties = ReadDefaultProperties.getProperties();

        if (System.getProperty("os.name").startsWith("Windows")) {
            cmd = new String[] { "c:\\cygwin\\bin\\bash.exe", "--login", "-li", "teszt.sh" };
            file = new File(properties.getProperty("file.out.win"));
        } else {
            cmd = new String[] { "uname", "-a" };
            file = new File(properties.getProperty("file.out.linux"));
        }
        ProcessBuilder pb = new ProcessBuilder(cmd);
        pb.redirectOutput(file);
        Process p = null;
        try {
            p = pb.start();
            p.waitFor();
            int exitVal = p.exitValue();
            System.out.println("exit value:" + exitVal);
            System.out.println("open and read output file:" + file.getAbsolutePath());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
    }

}

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

import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class RuntimeExecute {

    public static void cmdWinDir(String dir) {
        String command = "cmd /c dir " + dir;
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(command);
            InputStreamReader in = new InputStreamReader(p.getInputStream());
            LineNumberReader lineReader = new LineNumberReader(in);
            String line = lineReader.readLine();
            if (line != null) {
                do {
                    System.out.println(line);
                    line = lineReader.readLine();
                } while (line != null);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }

    }

}

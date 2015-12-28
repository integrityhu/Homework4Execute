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

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class RedirectOutput2Pipe {

    // java -jar traceroute.jar --task redirect --host mondoka.hu | more
    public static void traceroute(String host) {
        String command;
        if (System.getProperty("os.name").startsWith("Windows")) {
            command = "tracert";
        } else {
            command = "traceroute";
        }
        String[] cmd = { command, host };
        ProcessBuilder pb = new ProcessBuilder(cmd);

        Redirect redirect = ProcessBuilder.Redirect.INHERIT;
        pb.redirectOutput(redirect);
        pb.redirectErrorStream(true);
        Process p = null;
        try {
            p = pb.start();
            p.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.err.println("Did you install "+command+" ?");
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
    }

}

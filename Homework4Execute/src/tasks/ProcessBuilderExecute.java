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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ProcessBuilderExecute {

    public static void cmdWinTrace(String host) {
        ProcessBuilder pb = new ProcessBuilder(new String[] { "tracert", host });
        Map<String, String> env = pb.environment();
        env.put("CHARSET", "cp852");

        Process p = null;
        try {
            p = pb.start();
            InputStream inputStream = p.getInputStream();
            InputStream errorStream = p.getErrorStream();

            ByteArrayOutputStream oByteArrayStream = new ByteArrayOutputStream();
            byte[] iOut = new byte[20];
            int iCnt = 0;

            do {
                iCnt = inputStream.read(iOut);
                if (iCnt > 0) {
                    oByteArrayStream.write(iOut, 0, iCnt);
                }
            } while (iCnt != -1);

            System.out.print(oByteArrayStream.toString("cp852"));

            byte[] eOut = new byte[20];
            int eAvailable;
            int eCnt = 0;
            ByteArrayOutputStream errorByteArrayStream = new ByteArrayOutputStream();
            do {
                eAvailable = errorStream.available();
                eCnt = eAvailable > 0 ? errorStream.read(eOut) : 0;
                if (eAvailable > 0) {
                    errorByteArrayStream.write(eOut, 0, eCnt);
                }
            } while (eCnt > 0);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }

    }

}

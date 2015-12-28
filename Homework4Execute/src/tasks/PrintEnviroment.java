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

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class PrintEnviroment {
    
    public static void printEnviroment() {
        Properties props = System.getProperties();
        Iterator<Entry<Object, Object>> iter = props.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<Object, Object> entry = iter.next();
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
    
}

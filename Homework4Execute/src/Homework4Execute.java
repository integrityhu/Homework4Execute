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
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;

import tasks.RedirectOutput2File;
import tasks.PrintEnviroment;
import tasks.ProcessBuilderExecute;
import tasks.RedirectOutput2Pipe;
import tasks.RunWithThread;
import tasks.RuntimeExecute;

public class Homework4Execute {
    
    @SuppressWarnings("static-access")
    private static Options getCommandLineOptions() {
        Options options = new Options();
        options.addOption(OptionBuilder.isRequired(false).withArgName("task").withLongOpt("task").hasArg(true).withDescription("trace route task [uname,traceroute,enviroment,cmdWinDir,cmdWinTrace]").create());
        options.addOption(OptionBuilder.isRequired(false).withArgName("host").withLongOpt("host").hasArg(true).withDescription("trace route host on redirect task").create());
        options.addOption(OptionBuilder.isRequired(false).withArgName("dir").withLongOpt("dir").hasArg(true).withDescription("test dir on cmdWinDir task").create());
        return options;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Parser parser = new GnuParser();
        Options options = getCommandLineOptions();
        String task = null;
        String host = null;
        String dir = null;
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
            task = (String) commandLine.getOptionValue("task");
            if ("traceroute".equals(task) || "cmdWinTrace".equals(task)) {
                host = (String) commandLine.getOptionValue("host");
                if (host == null) {
                    System.err.println("--host parameter required at this task!");
                    System.exit(-1);
                }
            }
            if ("cmdWinDir".equals(task)) {
                dir = (String) commandLine.getOptionValue("dir");
                if (dir == null) {
                    System.err.println("--dir parameter required at this task!");
                    System.exit(-1);
                }
            }
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java -jar homework4execute.jar", options);
            System.exit(-1);
        }

        if (task != null) {
            switch (task) {
            case "enviroment":
                PrintEnviroment.printEnviroment();
                break;
            case "traceroute":
                RedirectOutput2Pipe.traceroute(host);
                break;
            case "cmdWinTrace":
                ProcessBuilderExecute.cmdWinTrace(host);
                break;
            case "cmdWinDir":
                RuntimeExecute.cmdWinDir(dir);
                break;
            case "uname":
                RedirectOutput2File.getUname();
                break;
            }
        } else {
            RunWithThread.executeCommand("javac Teszt.java");
            RunWithThread.executeCommand("java -cp ./ Teszt");
        }
    }
}

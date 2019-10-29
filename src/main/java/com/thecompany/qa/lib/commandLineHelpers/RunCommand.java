/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.commandLineHelpers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;
import static java.lang.Runtime.getRuntime;

public class RunCommand {

    final static Logger logger = Logger.getRootLogger();
    public String RunUnixCommand(String command) {
        String s = null;
        try
        {
            // run the Unix "ps -ef" command
            // using the Runtime exec method:

            //Process p = getRuntime().exec("ps -ef");
            Process p = getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new
                InputStreamReader(p.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

            // read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

            // read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            System.exit(0);
            return "";
            } catch (IOException e) {
                System.out.println("exception happened - here's what I know: ");
                e.printStackTrace();
                System.exit(-1);
            }
        return "";
    }

    public String RunBatCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder();
        //command = "hello.bat";
        processBuilder.command("cmd", "/c", command);
        File dir = new File("C:\\Users\\effie\\");
        processBuilder.directory(dir);

        //Process process = Runtime.getRuntime().exec(
        //            "cmd /c hello.bat", null, new File("C:\\Users\\mkyong\\"));

        try {
            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println(output);
                //System.exit(0);
                return "";
            } else {
                //abnormal...
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "";
    }
}
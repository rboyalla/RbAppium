package org.boyalla.appium.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import org.boyalla.exception.FwException;

   public class SimulatorMnager {

    private static final String APPLICATIONS_XCODE_APP_CONTENTS_DEVELOPER_USR_BIN = "/Applications/Xcode.app/Contents/Developer/usr/bin";

    public static void main(String[] args) throws IOException, InterruptedException {

        String[] cmd = {"mkdir", "test2"};
        String[] createPhone = {"xcrun", "simctl", "clone", "iPhone 7", "iPhone 7d4"};
        String[] deletePhone = {"xcrun", "simctl", "delete", "iPhone 7AD"};

        // System.out.println(executeSsimctl(deletePhone));
         String toCloneNmae = "iPhone Xs1";
        //cloneSimulator( "iPhone 7", toCloneNmae);
        String simName = "iPhone Xs";
        //deleteSimulator("iPhone 70");
        //shutdownSimulator("iPhone 7");
        //SimulatorMnager.cloneSimulator(simName, toCloneNmae);
       SimulatorMnager.cloneSimulator(simName, toCloneNmae);
        //SimulatorMnager.cloneSimulator(simName, toCloneNmae);
        //SimulatorMnager.deleteSimulator(toCloneNmae);
        int noOfSims = 3;
       // cleanSimulators(simName, noOfSims);

    }

    public static void cleanSimulators(String simName, int noOfSims) throws IOException, InterruptedException {
        for (int i = 0; i <= noOfSims; i++) {
            System.out.println("########### org.boyalla.appium.server.SimulatorMnager.cleanSimulators() deleting " + simName + i);
            SimulatorMnager.deleteSimulator(simName + i);
            System.out.println("########### org.boyalla.appium.server.SimulatorMnager.cleanSimulators() deleted " + simName + i);

        }
    }

    public static synchronized String executeCommand(String[] macCommand) throws IOException, InterruptedException {

        Process pr = Runtime.getRuntime().exec(macCommand);
        pr.waitFor();

        InputStream is = pr.getErrorStream();
        String string = new String(is.readAllBytes());
        //System.out.println(string);

        return string;

    }

    public static synchronized String executeSsimctl(String[] simctlCommand) throws IOException, InterruptedException {

        Process pr = Runtime.getRuntime().exec(simctlCommand, null, new File(APPLICATIONS_XCODE_APP_CONTENTS_DEVELOPER_USR_BIN));
        pr.waitFor();

        InputStream is = pr.getErrorStream();
        String string = new String(is.readAllBytes());
        //System.out.println(string);

        return string;

    }

    public static synchronized String cloneSimulator(String originalNmae, String toCloneNmae) throws IOException, InterruptedException {
        //String[] simctl_dir= {"cd", APPLICATIONS_XCODE_APP_CONTENTS_DEVELOPER_USR_BIN};
        String[] clonePhone = {"xcrun", "simctl", "clone", originalNmae, toCloneNmae};
        //String[] deletePhone= {"xcrun", "simctl", "delete", "iPhone 7", "iPhone 7d4"};

        //String currentDir = System.getProperty("user.dir");
        //System.out.println("Current dir using System:" +currentDir);
        //Runtime.getRuntime().exec(simctl_dir);
        //System.out.println("Current dir using System:" +currentDir);
        Process pr = Runtime.getRuntime().exec(clonePhone, null, new File(APPLICATIONS_XCODE_APP_CONTENTS_DEVELOPER_USR_BIN));
        pr.waitFor();
        System.out.println("Cloned : " + toCloneNmae);
        
        BufferedInputStream bfi=new BufferedInputStream(pr.getInputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(bfi));
        if(br.ready()) {
          return   br.readLine();
        }
        
        throw new FwException("Unable to clone divce="+originalNmae);
        
    }

    public static synchronized void deleteSimulator(String toCloneNmae) throws IOException, InterruptedException {
        shutdownSimulator(toCloneNmae);
        String[] deletePhone = {"xcrun", "simctl", "delete", toCloneNmae};
        String error = executeSsimctl(deletePhone);

        while (!error.contains("Invalid device")) {
            error = executeSsimctl(deletePhone);
        }
        }

    public static synchronized void shutdownSimulator(String toCloneNmae) throws IOException, InterruptedException {
        //String[] simctl_dir= {"cd", APPLICATIONS_XCODE_APP_CONTENTS_DEVELOPER_USR_BIN};
        //String[] createPhone= {"xcrun", "simctl", "clone", originalNmae, toCloneNmae};
        String[] shutdownPhone = {"xcrun", "simctl", "shutdown", toCloneNmae};

        //String currentDir = System.getProperty("user.dir");
        //System.out.println("Current dir using System:" +currentDir);
        //Runtime.getRuntime().exec(simctl_dir);
        //System.out.println("Current dir using System:" +currentDir);
        Process pr = Runtime.getRuntime().exec(shutdownPhone, null, new File(APPLICATIONS_XCODE_APP_CONTENTS_DEVELOPER_USR_BIN));
        pr.waitFor();
        System.out.println("Shutdowned : " + toCloneNmae);
    }

}

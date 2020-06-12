package org.boyalla.util;

import com.jcabi.ssh.Shell;
import com.jcabi.ssh.SshByPassword;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.boyalla.exception.FwException;

public class FwSysUtil {

    public static String getHostName() {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new FwException(e);
        }
        return inetAddress.getHostName()
                .trim();
    }

    public static Path getUserHome() {
        return Paths.get(System.getProperty(SysConstants.userHome.toString()));
    }

    public static Path getUserDir() {
        return Paths.get(System.getProperty(SysConstants.userDir.toString()));
    }

    public static String getIpAddress() {
        InetAddress inetAddress;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new FwException(e);
        }
        return inetAddress.getHostAddress();
    }

    public static String getFileSeparator() {
        return File.separator;
    }

    public static synchronized Integer getRemoteFreePort(String remoteMachineIp, String remoteMachineUser,
            String remoteMachinePwd) {
        Shell ssh = null;
        try {
            ssh = new SshByPassword(remoteMachineIp, 22, remoteMachineUser, remoteMachinePwd);
        } catch (UnknownHostException e) {
            System.out.println(e);
            throw new FwException(e);
        }

        Shell.Plain plain = new Shell.Plain(ssh);
        String stdout = null;
        try {
            stdout = plain.exec(
                    "comm -23 <(seq 49152 65535) <(/usr/sbin/ss -tan | awk '{print $4}' | cut -d':' -f2 | grep \"[0-9]\\{1,5\\}\" | sort | uniq) | shuf | head -n 1");
        } catch (IOException e) {
            System.out.println("error =" + e);
            throw new FwException(e);
        }
        System.out.println("stdout=" + stdout);

        return Integer.valueOf(stdout.trim());
    }

    public static synchronized Integer getFreePort() {
        int port;
        try {
            ServerSocket socket = new ServerSocket(0);
            port = socket.getLocalPort();
            socket.close();
        } catch (Exception e) {
            port = -1;
        }
        return port;
    }

    public static String getOs() {
        return System.getProperty("os.name");
    }

    public static void main(String[] args) {
        getRemoteFreePort("192.168.1.193", "qa", "qa");
    }

}

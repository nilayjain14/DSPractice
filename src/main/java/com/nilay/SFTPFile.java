package com.nilay;


import com.jcraft.jsch.*;

import java.io.*;

public class SFTPFile {


    public static void main(String[] args) throws IOException {

        String sftpUser = "user_name";
        String privateKeyPath = "id_rsa_path";
        String sftpDir = "sftp_dir";
        sftp(sftpUser, sftpDir,privateKeyPath);

    }

    private static void sftp(String sftpUser, String sftpDir, String privateKeyPath) throws IOException {
        JSch jSch = new JSch();
        Session session     = null;
        Channel channel     = null;
        ChannelSftp channelSftp = null;
        try {
            jSch.addIdentity(privateKeyPath);
            System.out.println("Private Key Added.");
            session = jSch.getSession(sftpUser,"host",22);
            System.out.println("session created.");

            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("shell channel connected....");
            channelSftp = (ChannelSftp)channel;
            channelSftp.cd(sftpDir);
            System.out.println("Changed the directory...");
            File file = new File("path_of_files");
            File[] listOfFiles = getFilesFromFillerLocation(file);
            if (!listOfFiles.equals(null) || listOfFiles.length != 0) {
                for (File currentFile :listOfFiles) {

                    channelSftp.put(new FileInputStream(currentFile), currentFile.getName());
                    System.out.println("bajshdcjsdgvf");
                    currentFile.delete();
                }
                System.out.println("File transferred successfully to host.");
            }
            else{
                System.out.println("No files to transfer.");
            }
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SftpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally{
            if(channelSftp!=null){
                channelSftp.disconnect();
                channelSftp.exit();
            }
            if(channel!=null) channel.disconnect();

            if(session!=null) session.disconnect();
        }
    }

    private static File[] getFilesFromFillerLocation(File file) {
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().matches(".+\\.(json|gz|avro)");
            }
        };
        return file.listFiles(filter);
    }


}

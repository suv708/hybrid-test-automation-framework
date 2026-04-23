package sendReport;

import java.io.File;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class ShareReport {

    public void sendReport(String toEmail) {

        final String fromEmail = "adityagite.sknsits.it@gmail.com";
        final String appPassword = "klzfjfjsfyflwpiy";

        String reportFolder = "D:\\selenium\\SAG\\Reports";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, appPassword);
                    }
                });

        try {

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(fromEmail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            msg.setSubject("Automation HTML Reports");

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText("Hi,\n\nPlease find attached ALL automation HTML reports.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            // ✅ Attach ALL HTML reports
            File folder = new File(reportFolder);
            File[] files = folder.listFiles();

            if (files != null && files.length > 0) {

                int count = 0;

                for (File file : files) {

                    if (file.isFile() && file.getName().toLowerCase().endsWith(".html")) {

                        MimeBodyPart attachment = new MimeBodyPart();
                        attachment.attachFile(file);
                        attachment.setFileName(file.getName());

                        multipart.addBodyPart(attachment);

                        System.out.println("📎 Attached: " + file.getName());
                        count++;
                    }
                }

                if (count == 0) {
                    System.out.println("❌ No HTML reports found in folder.");
                }

            } else {
                System.out.println("❌ Folder is empty or not found: " + reportFolder);
            }

            msg.setContent(multipart);

            Transport.send(msg);

            System.out.println("✅ All HTML Reports sent successfully to: " + toEmail);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//package sendReport;
//
//import java.io.File;
//import java.util.Properties;
//import javax.mail.*;
//import javax.mail.internet.*;
//
//public class ShareReport {
//
//    public void sendReport(String toEmail) {
//
//        final String fromEmail = "adityagite.sknsits.it@gmail.com";
//        final String appPassword = "klzfjfjsfyflwpiy";   // App Password
//
//        String reportFolder = "D:\\selenium\\SAG\\Reports";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props,
//                new Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(fromEmail, appPassword);
//                    }
//                });
//
//        try {
//
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(fromEmail));
//            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//            msg.setSubject("Automation HTML Report");
//
//            MimeBodyPart textPart = new MimeBodyPart();
//            textPart.setText("Hi,\n\nPlease find attached latest automation HTML report.\n\nThanks,\nAutomation Team");
//
//            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(textPart);
//
//            // Fetch latest report (HTML)
//            File latestReport = getLatestReport(reportFolder);
//
//            if (latestReport != null) {
//                MimeBodyPart attachment = new MimeBodyPart();
//                attachment.attachFile(latestReport);
//                attachment.setFileName(latestReport.getName());  // important
//                multipart.addBodyPart(attachment);
//
//                System.out.println("✅ Report Attached: " + latestReport.getAbsolutePath());
//            } else {
//                System.out.println("❌ No HTML report found in folder: " + reportFolder);
//            }
//
//            msg.setContent(multipart);
//
//            Transport.send(msg);
//
//            System.out.println("✅ HTML Report sent successfully to: " + toEmail);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // ✅ Fetch latest HTML file from folder + subfolders
//    private static File getLatestReport(String folderPath) {
//
//        File folder = new File(folderPath);
//
//        if (!folder.exists()) {
//            System.out.println("❌ Folder not found: " + folderPath);
//            return null;
//        }
//
//        File latest = null;
//
//        File[] files = folder.listFiles();
//        if (files == null) return null;
//
//        for (File f : files) {
//
//            // If directory, check inside
//            if (f.isDirectory()) {
//                File inside = getLatestReport(f.getAbsolutePath());
//                if (inside != null && (latest == null || inside.lastModified() > latest.lastModified())) {
//                    latest = inside;
//                }
//            }
//
//            // If HTML file
//            if (f.isFile() && f.getName().endsWith(".html")) {
//                if (latest == null || f.lastModified() > latest.lastModified()) {
//                    latest = f;
//                }
//            }
//        }
//
//        return latest;
//    }
//}

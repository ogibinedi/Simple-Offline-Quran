package com.obe.quranid2.module.email;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.EditText;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import com.obe.quranid2.BuildConfig;
import com.obe.quranid2.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DirectEmailActivity extends AppCompatActivity {
    private EditText edtEmail, edtSubject, edtMessage;
    private String sEmail, sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct_email);
        setTitle("Laporan Terkait Konten");
        initView();
    }

    private void initView() {
        edtSubject = findViewById(R.id.ti_et_subject);
        edtEmail = findViewById(R.id.ti_et_email);
        edtEmail.setText(BuildConfig.AppEmail);
        edtMessage = findViewById(R.id.ti_et_content);
        AppCompatButton btnSend = findViewById(R.id.btn_send);

        btnSend.setOnClickListener(view ->{
            sEmail = BuildConfig.AppEmail;
            sPassword = BuildConfig.AppPassword;

            // Initialize properties
            Properties properties = new Properties();
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");

            // Initialize session
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sEmail, sPassword);
                }
            });

            try {
                // Initialize email content
                MimeMessage message = new MimeMessage(session);
                // Sender email
                message.setFrom(new InternetAddress(sEmail));
                // Recipient email
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(edtEmail.getText().toString().trim()));
                // Email subject
                message.setSubject(edtSubject.getText().toString().trim());
                // Email content
                message.setText(edtMessage.getText().toString().trim());

                // Send email
                new SendMail().execute(message);
            }catch (MessagingException e){
                e.printStackTrace();
            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    private class SendMail extends AsyncTask<Message, String, String> {
        // Initialize progress dialog
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(DirectEmailActivity.this);
            dialog.setTitle("Silahkan tunggu");
            dialog.setMessage("Sedang mengirim email");
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setIndeterminate(true);
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                // When success
                Transport.send(messages[0]);
                return "Success";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            // Dismiss progress dialog
            dialog.dismiss();
            if (s.equals("Success")){
                // When success

                // Initialize  alert dialog
                AlertDialog.Builder aSuccess = new AlertDialog.Builder(DirectEmailActivity.this);
                aSuccess.setCancelable(false);
                aSuccess.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                aSuccess.setMessage("Email berhasil dikirim");
                aSuccess.setPositiveButton("OK", (dialogInterface, i) -> {
                    dialog.dismiss();
                    // Clear all input form field
                    edtEmail.setText("");
                    edtSubject.setText("");
                    edtMessage.setText("");
                });

                // Show alert dialog
                aSuccess.show();
            }else{
                // When error
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan, pastikan email yang anda input valid", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Animatoo.animateSwipeLeft(this);
    }
}
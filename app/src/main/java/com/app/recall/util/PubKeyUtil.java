package com.app.recall.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by KenChan on 17/1/12.
 */

public class PubKeyUtil {

    public static String getPubKey(Context context) {
        String signcode = null;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context
                    .getPackageName(), PackageManager.GET_SIGNATURES);
            Signature[] signatures = packageInfo.signatures;
            Signature signature = signatures[0];
            signcode = parseSignature(signature.toByteArray());
            signcode = signcode.toLowerCase();
            Logger.d(signcode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return signcode;
    }

    private static String parseSignature(byte[] signature) {
        String sign = "";
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new
                    ByteArrayInputStream(signature));
            String pubKey = cert.getPublicKey().toString();
            String ss = subString(pubKey);
            ss = ss.replace(",", "");
            ss = ss.toLowerCase();
            int aa = ss.indexOf("modulus");
            int bb = ss.indexOf("publicexponent");
            sign = ss.substring(aa + 8, bb);
        } catch (CertificateException e) {
            Log.e("recall", e.getMessage(), e);
        }
        return sign;
    }

    private static String subString(String sub) {
        Pattern pp = Pattern.compile("\\s*|\t|\r|\n");
        Matcher mm = pp.matcher(sub);
        return mm.replaceAll("");
    }
}

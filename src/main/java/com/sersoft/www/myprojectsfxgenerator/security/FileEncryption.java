/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sersoft.www.myprojectsfxgenerator.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

public class FileEncryption {

	public static void main(String[] args,File file,File file1,String pass) throws Exception {

            FileOutputStream outFile;
            try (FileInputStream inFile = new FileInputStream(file)) {
                outFile = new FileOutputStream(file1);//"plainfile.des"
                String password = pass;
                PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
                SecretKeyFactory secretKeyFactory = SecretKeyFactory
                        .getInstance("PBEWithMD5AndTripleDES");
                SecretKey secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
                byte[] salt = new byte[8];
                Random random = new Random();
                random.nextBytes(salt);
                PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
                Cipher cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
                cipher.init(Cipher.ENCRYPT_MODE, secretKey, pbeParameterSpec);
                outFile.write(salt);
                byte[] input = new byte[64];
                int bytesRead;
                while ((bytesRead = inFile.read(input)) != -1) {
                    byte[] output = cipher.update(input, 0, bytesRead);
                    if (output != null)
                        outFile.write(output);
                }
                byte[] output = cipher.doFinal();
                if (output != null)
                    outFile.write(output);
            }
		outFile.flush();
		outFile.close();
	}

}
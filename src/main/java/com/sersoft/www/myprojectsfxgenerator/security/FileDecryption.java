/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sersoft.www.myprojectsfxgenerator.security;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileDecryption {
	public static void main(String[] args,File file2,File file3,String pass) {

		String password = pass;
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory secretKeyFactory = null;
            try {
                secretKeyFactory = SecretKeyFactory
                        .getInstance("PBEWithMD5AndTripleDES");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
		SecretKey secretKey = null;
            try {
                secretKey = secretKeyFactory.generateSecret(pbeKeySpec);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }

		FileInputStream fis = null;
            try {
                fis = new FileInputStream(file2);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
		byte[] salt = new byte[8];
            try {
                fis.read(salt);
            } catch (IOException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }

		PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);

		Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("PBEWithMD5AndTripleDES");
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                cipher.init(Cipher.DECRYPT_MODE, secretKey, pbeParameterSpec);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidAlgorithmParameterException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
		FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file3);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
		byte[] in = new byte[64];
		int read;
            try {
                while ((read = fis.read(in)) != -1) {
                    byte[] output = cipher.update(in, 0, read);
                    if (output != null)
                        fos.write(output);
                }
            } catch (IOException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }

		byte[] output = null;
            try {
                output = cipher.doFinal();
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
		if (output != null)
			try {
                            fos.write(output);
                } catch (IOException ex) {
                    Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
                }

            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fos.flush();
            } catch (IOException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(FileDecryption.class.getName()).log(Level.SEVERE, null, ex);
            }
	}
}
package agenda;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;

public class AssinaturaDigital {

	PrivateKey priv;
	PublicKey pub;

	public AssinaturaDigital() {
		super();
	}

	public PrivateKey getPriv() {
		return priv;
	}

	public PublicKey getPub() {
		return pub;
	}

	public void gerarChaves() {

		KeyPairGenerator keyGen;
		try {

			keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			keyGen.initialize(1024, random);
			KeyPair pair = keyGen.generateKeyPair();
			priv = pair.getPrivate();
			pub = pair.getPublic();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public byte [] assinarObjeto(byte[] bs) throws SignatureException {
		Signature dsa;
		byte[] realSig = null;
		
		try {
			dsa = Signature.getInstance("SHA1withDSA", "SUN");
			dsa.initSign(priv);
			dsa.update(bs);
			realSig = dsa.sign();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return realSig;
	}

	
}

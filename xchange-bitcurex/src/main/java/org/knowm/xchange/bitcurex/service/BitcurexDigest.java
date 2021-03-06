package org.knowm.xchange.bitcurex.service;

import javax.crypto.Mac;
import javax.xml.bind.DatatypeConverter;

import org.knowm.xchange.service.BaseParamsDigest;

import net.iharder.Base64;
import si.mazi.rescu.RestInvocation;

public class BitcurexDigest extends BaseParamsDigest {

  /**
   * Constructor
   *
   * @param secretKeyBase64
   * @param apiKey @throws IllegalArgumentException if key is invalid (cannot be base-64-decoded or the decoded key is invalid).
   */
  private BitcurexDigest(String secretKeyBase64, String apiKey) {

    super(DatatypeConverter.parseBase64Binary(secretKeyBase64), HMAC_SHA_512);
  }

  public static BitcurexDigest createInstance(String secretKeyBase64, String apiKey) {

    return secretKeyBase64 == null ? null : new BitcurexDigest(secretKeyBase64, apiKey);
  }

  @Override
  public String digestParams(RestInvocation restInvocation) {

    String digest;
    byte[] signature;

    Mac sha512 = getMac();

    sha512.update(restInvocation.getRequestBody().getBytes());

    signature = sha512.doFinal();
    digest = Base64.encodeBytes(signature);

    return digest;
  }
}

package org.poc.imageservice.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class SHA1Service {

    public String getSha1Digest(InputStream is) {
        try {
            return DigestUtils.sha1Hex(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

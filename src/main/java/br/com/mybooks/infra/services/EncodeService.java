package br.com.mybooks.infra.services;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class EncodeService {
	
	public String encodeFile(final MultipartFile file) throws IOException {
		return Base64.getEncoder().encodeToString(file.getBytes());
	}

}

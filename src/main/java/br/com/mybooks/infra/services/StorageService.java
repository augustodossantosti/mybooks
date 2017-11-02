/*
 * mybooks 1.0 18 de out de 2017
 * 
 * MIT License - Copyright (c) 2017
 */
package br.com.mybooks.infra.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.mybooks.domain.items.FileInfo;
import br.com.mybooks.domain.library.exceptions.StorageFileNotFoundException;

/**
 * A classe <code>StorageService</code> contem métodos
 * para operações com os arquivos relacionados a itens.
 *
 * @author Augusto dos Santos
 * @version 1.0 18 de out de 2017
 */
@Service
public class StorageService {
	
	@Autowired
	private EncodeService encodeService;
	
	public FileInfo store(final MultipartFile file, final MultipartFile cover) throws IOException {
		final Path rootLocation = Paths.get(getCurrentFilePath());
		final File uploadedFile = rootLocation.resolve(file.getOriginalFilename()).toFile();
		FileUtils.copyInputStreamToFile(file.getInputStream(), uploadedFile);
		final String coverBase64 = encodeService.encodeFile(cover);
		return new FileInfo(LocalDate.now(), file.getOriginalFilename(), uploadedFile.getAbsolutePath(), coverBase64);
	}
	
	public Resource loadAsResource(final String filePath) throws StorageFileNotFoundException {
		final File file = new File(filePath);
		try {
			final Resource resource = new UrlResource(file.toURI());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new StorageFileNotFoundException();
			}
		} catch (MalformedURLException ex) {
			throw new StorageFileNotFoundException();
		}
	}
	
	private String getCurrentFilePath() {
        final Calendar now = Calendar.getInstance();
        return getAttachmentPath() + now.get(Calendar.YEAR) + File.separator + (now.get(Calendar.MONTH) + 1) + File.separator + now.get(Calendar.DAY_OF_MONTH);
    }
	
	private String getAttachmentPath() {
        return getFileServerPath() + File.separator + "attachment" + File.separator;
    }
	
	private String getFileServerPath() {
        return System.getProperty("java.io.tmpdir") + File.separator + "mybooks";
    }
	
}

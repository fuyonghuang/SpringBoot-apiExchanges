package com.sic.utils.configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class FtpUtils {
	private static final RestTemplate restTemplate = new RestTemplate();

	final String APPLICATION_PDF = "application/jpg";

	public void ftpupload(String url) {
		HttpHeaders headers = new HttpHeaders();
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			List list = new ArrayList<>();
			list.add(MediaType.valueOf(APPLICATION_PDF));
			headers.setAccept(list);
			//下载url
			ResponseEntity<byte[]> response = restTemplate.exchange(
					UriComponentsBuilder.fromHttpUrl(url).build().toUri(), HttpMethod.GET,
					new HttpEntity<byte[]>(headers), byte[].class);

			byte[] result = response.getBody();

			inputStream = new ByteArrayInputStream(result);
			//下载目标url
			File file = new File("C:/Users/admin/Desktop/11.jpg");
			if (!file.exists()) {
				file.createNewFile();
			}

			outputStream = new FileOutputStream(file);
			int len = 0;
			byte[] buf = new byte[1024];
			while ((len = inputStream.read(buf, 0, 1024)) != -1) {
				outputStream.write(buf, 0, len);
			}
			outputStream.flush();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {

				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

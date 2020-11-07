package com.unip.tcc.scarblade.utils;

import java.io.File;
import java.util.Base64;
import java.util.List;

import javax.imageio.stream.FileImageOutputStream;

public class DecoderUtils {
	
	public void decoder(List<String> a) {
		FileImageOutputStream imageOutput;
		String path = "D:\\DEV\\Projetos\\scarblade-treinamento\\fotos\\";
		String id = String.valueOf(2);
		try {
			for (int i = 0; i <= 14; i++) {
				byte[] data = Base64.getDecoder().decode(a.get(i));
				

				String newFileName = path + "pessoa." + id + "." + i
						+ ".jpg";
				imageOutput = new FileImageOutputStream(new File(newFileName));
				imageOutput.write(data, 0, data.length);
				imageOutput.close();

			}

		} catch (Exception e) {
		}
	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unip.tcc.scarblade.treinamento;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.IntBuffer;
import java.util.List;

import static org.bytedeco.opencv.global.opencv_core.*;
import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_face.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.resize;

import com.unip.tcc.scarblade.dao.FacesDAO;
import com.unip.tcc.scarblade.utils.DecoderUtils;




/**
 *
 * @author Jones
 */
public class Treinamento {
	
    public void treinamentoFlux(String id) {
    	
    	FacesDAO dao = new FacesDAO(); 	
    	List<String> a = dao.selectFaces(id);  	
    	DecoderUtils imagemDC = new DecoderUtils();
    	imagemDC.decoder(a,id);
    	
    	
    	 File diretorio = new File("D:\\DEV\\Projetos\\scarblade-treinamento\\fotos\\");
    	 FilenameFilter filtroImagem = new FilenameFilter() {
             @Override
             public boolean accept(File dir, String nome) {
                 return nome.endsWith(".jpg") || nome.endsWith(".gif") || nome.endsWith(".png");
             }
         };
         
         File[] arquivos = diretorio.listFiles(filtroImagem);
         MatVector fotos = new MatVector(arquivos.length);
         Mat rotulos = new Mat(arquivos.length, 1, CV_32SC1);
         IntBuffer rotulosBuffer = rotulos.createBuffer();
         int contador = 0;
         for (File imagem: arquivos) {
             Mat foto = imread(imagem.getAbsolutePath(), IMREAD_GRAYSCALE);
             int classe = Integer.parseInt(imagem.getName().split("\\.")[1]);
             System.out.println(imagem.getName().split("\\.")[1] + "  " + imagem.getAbsolutePath());
             resize(foto, foto, new Size(160,160));
             fotos.put(contador, foto);
             rotulosBuffer.put(contador, classe);
             contador++;
         }

         FaceRecognizer eigenfaces = EigenFaceRecognizer.create(); 
         FaceRecognizer fisherfaces = FisherFaceRecognizer.create(); 
         FaceRecognizer lbph = LBPHFaceRecognizer.create(2,9,9,9,1);
         
         eigenfaces.train(fotos, rotulos);
         eigenfaces.save("D:\\DEV\\Projetos\\scarblade-treinamento\\src\\recursos\\classificadorEigenFaces.yml");
         fisherfaces.train(fotos, rotulos);
         fisherfaces.save("D:\\DEV\\Projetos\\scarblade-treinamento\\src\\recursos\\classificadorFisherFaces.yml");
         lbph.train(fotos, rotulos);
         lbph.save("D:\\DEV\\Projetos\\scarblade-treinamento\\src\\recursos\\classificadorLBPH.yml");
     }
    
}

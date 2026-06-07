package com.Tesis.Programacion.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UploadFileService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    public String guardarImagen(MultipartFile file) throws IOException {
        // Convertimos el String inyectado en un objeto Path
        Path rootFolder = Paths.get(this.uploadDir);

        if (!Files.exists(rootFolder)) {
            Files.createDirectories(rootFolder);
        }

        String nombreUnico = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path rutaCompleta = rootFolder.resolve(nombreUnico).toAbsolutePath();
        Files.copy(file.getInputStream(), rutaCompleta);

        return nombreUnico;
    }

    public boolean eliminarArchivo(String nombreArchivo) {
        try {
            Path rootFolder = Paths.get(this.uploadDir);
            Path rutaCompleta = rootFolder.resolve(nombreArchivo).toAbsolutePath();

            // Intenta borrar el archivo si existe en la carpeta
            return Files.deleteIfExists(rutaCompleta);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo eliminar el archivo físico: " + e.getMessage());
        }
    }
}
package com.clickbait.orient.service;

import com.clickbait.orient.config.FileConfig;
import com.clickbait.orient.database.EventRepository;
import com.clickbait.orient.model.Event;
import com.clickbait.orient.model.Photo;
import com.clickbait.orient.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

/**
 * Handles files
 */
public class FileServiceImpl implements FileService {

    // file configuration to get properties
    private FileConfig fileConfig;

    // used to find and update event when a new photo is uploaded
    private EventRepository events;

    /**
     * Class constructor
     * @param fileConfig automatically injected file configuration
     */
    @Autowired
    public FileServiceImpl(FileConfig fileConfig, EventRepository events) {
        this.fileConfig = fileConfig;
        this.events = events;

        // create file upload directory if it doesn't exist
        File photoDir = new File(fileConfig.getPhotoUploadDir());

        if (!photoDir.exists()) {
            photoDir.mkdir();
        }
    }

    /**
     * Saves a multipart photo file to the specified directory
     *
     * @param file file to save
     * @param eventId to which event the photo belongs
     * @param teamId to which team the photo belongs
     *
     * @return file download url
     */
    @Override
    public String savePhoto(MultipartFile file, String eventId, String teamId) {
        // validate event id
        Optional<Event> event = events.findById(eventId);

        if (!event.isPresent()) {
            return null;
        }

        // validate team id
        if (!EventServiceImpl.validateTeamId(event.get(), teamId)) {
            return null;
        }

        File photoFile = new File(fileConfig.getPhotoUploadDir() + "/" + System.currentTimeMillis() + "_" + file.getOriginalFilename());

        try {
            if (!photoFile.exists()) {
                photoFile.createNewFile();
            }

            Files.copy(file.getInputStream(), Paths.get(photoFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            return null;
        }

        // get the download url
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/photo/")
                .path(photoFile.getName())
                .toUriString();

        // add the photo to the event
        event.get().getPhotos().add(new Photo(
                event.get().getId(),
                teamId,
                downloadURL,
                file.getContentType(),
                file.getSize()
        ));

        return downloadURL;
    }

    /**
     * Loads a photo file with the given name
     *
     * @param fileName name of the file
     * @return file as resource object
     */
    @Override
    public Resource getPhoto(String fileName) {
        try {
            Resource resource = new UrlResource(Paths.get(fileConfig.getPhotoUploadDir()).resolve(fileName).normalize().toUri());

            if (resource.exists()) {
                return resource;
            }
        } catch (MalformedURLException ex) { }

        return null;
    }
}

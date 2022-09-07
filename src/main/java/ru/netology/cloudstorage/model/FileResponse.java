package ru.netology.cloudstorage.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileResponse {

    private String filename;
    private Long size;
}

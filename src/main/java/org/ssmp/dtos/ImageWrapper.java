package org.ssmp.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ImageWrapper {

    private List<byte[]> imageData = new ArrayList<>();
}

package com.app.cinema.service;

import com.app.cinema.dto.ImageDto;
import com.app.cinema.model.Image;
import com.app.cinema.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public Page<ImageDto> findAll(Pageable pageable) {
        Page<Image> imagesPage = imageRepository.findAll(pageable);
        List<ImageDto> images = imagesPage.getContent()
                .stream()
                .map(ModelMapper::fromImageToImageDto)
                .collect(Collectors.toList());
        return new PageImpl<>(images, imagesPage.getPageable(), imagesPage.getTotalElements());
    }

    public List<ImageDto> findImageByMovieId(Long movieId) {
        return imageRepository.findByMovieId(movieId)
                .stream()
                .map(ModelMapper::fromImageToImageDto)
                .collect(Collectors.toList());
    }

}

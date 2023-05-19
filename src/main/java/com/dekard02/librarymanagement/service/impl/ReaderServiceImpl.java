package com.dekard02.librarymanagement.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.dekard02.librarymanagement.api.ResponseBody;
import com.dekard02.librarymanagement.dto.ReaderDto;
import com.dekard02.librarymanagement.dto.mapper.ReaderRequestDtoMapper;
import com.dekard02.librarymanagement.dto.mapper.ReaderResponseDtoMapper;
import com.dekard02.librarymanagement.entity.Reader;
import com.dekard02.librarymanagement.helper.ResponseBodyHelper;
import com.dekard02.librarymanagement.repository.ReaderRepository;
import com.dekard02.librarymanagement.service.ReaderService;
import com.dekard02.librarymanagement.service.StorageService;
import com.dekard02.librarymanagement.service.StorageService.StorageFolder;
import com.querydsl.core.types.Predicate;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;
    private final ReaderResponseDtoMapper readerResponseDtoMapper;
    private final ReaderRequestDtoMapper readerRequestDtoMapper;
    private final ResponseBodyHelper responseBodyHelper;
    private final StorageService storageService;

    private void setReaderResponseDtoPhoto(ReaderDto.Response readerResponseDto) {
        var photo = readerResponseDto.getPhoto() == null || readerResponseDto.getPhoto().isEmpty()
                ? StorageService.RESOURCE_FOLDER + "/" + "images/default-avatar.png"
                : readerResponseDto.getPhoto();

        var rootUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        readerResponseDto.setPhoto(rootUrl + "/" + photo);
    }

    private Reader findReaderOrThrow(Long id) {
        return readerRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));
    }

    @Override
    public ResponseBody getAllReadersResponse(Predicate predicate, Pageable pageable) {
        var page = readerRepository.findAll(predicate, pageable);
        var readerResponseDtos = new ArrayList<ReaderDto.Response>();
        page
                .stream()
                .forEach(reader -> {
                    var each = readerResponseDtoMapper.readerToReaderDto(reader);
                    this.setReaderResponseDtoPhoto(each);
                    readerResponseDtos.add(each);
                });
        return responseBodyHelper.page(page, "readers", readerResponseDtos);
    }

    @Override
    public ReaderDto.Response getReader(Long id) {
        var reader = findReaderOrThrow(id);
        var readerDto = readerResponseDtoMapper.readerToReaderDto(reader);
        this.setReaderResponseDtoPhoto(readerDto);
        return readerDto;
    }

    @Override
    public ReaderDto.Response saveReader(ReaderDto.Request readerDto) {
        var reader = readerRequestDtoMapper.readerDtoToReader(readerDto);
        reader.setId(null);
        var photoFile = readerDto.getPhoto();
        var photoName = storageService.store(photoFile,
                StorageFolder.IMAGE.toString() + "/" + READER_PHOTO_DIR);
        reader.setPhoto(photoName);
        readerRepository.save(reader);

        var redearResponseDto = readerResponseDtoMapper.readerToReaderDto(reader);
        setReaderResponseDtoPhoto(redearResponseDto);
        return redearResponseDto;
    }

    @Override
    public ReaderDto.Response updateReader(Long id, ReaderDto.Request readerDto) {
        var reader = findReaderOrThrow(id);
        if (readerDto.getPhoto() != null) {
            storageService.delete(reader.getPhoto());
            var fileName = storageService.store(readerDto.getPhoto(),
                    StorageFolder.IMAGE.toString() + "/" + READER_PHOTO_DIR);
            reader.setPhoto(fileName);
        }

        BeanUtils.copyProperties(readerDto, reader, "photo");
        readerRepository.save(reader);
        var redearResponseDto = readerResponseDtoMapper.readerToReaderDto(reader);
        setReaderResponseDtoPhoto(redearResponseDto);
        return redearResponseDto;
    }

    @Override
    public void deleteReader(Long id) {
        var reader = findReaderOrThrow(id);
        if (reader.getPhoto() != null) {
            storageService.delete(reader.getPhoto());
        }
        readerRepository.delete(reader);
    }

}

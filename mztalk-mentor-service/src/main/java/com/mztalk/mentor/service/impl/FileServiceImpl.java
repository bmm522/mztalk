package com.mztalk.mentor.service.impl;

import com.mztalk.mentor.domain.dto.FileDto;
import com.mztalk.mentor.domain.entity.Application;
import com.mztalk.mentor.domain.entity.File;
import com.mztalk.mentor.exception.ImageNotFoundException;
import com.mztalk.mentor.repository.ApplicationRepository;
import com.mztalk.mentor.repository.FileRepository;
import com.mztalk.mentor.service.FileService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;
    private final ApplicationRepository applicationRepository;

    @Override
    @Transactional
    public Long saveFile(FileDto fileDto) {
        Application application = fileDto.getApplication();
        File file = fileDto.toEntity();
        file.addApplication(application);
        return fileRepository.save(file).getId();
    }

    @Override
    public FileDto findById(Long id) {
        File file = fileRepository.findById(id).orElseThrow(()->new ImageNotFoundException("해당 서류가 존재하지 않습니다."));
        FileDto fileDto = new FileDto(file);
        return fileDto;
    }

    @Override
    public void saveFiles(Long applicationId, HttpServletRequest request) {
        ResponseEntity<String> response = getFile(applicationId, request.getHeader("Authorization"), request.getHeader("RefreshToken"));

        // ResponseEntity의 바디를 얻어와 바디를 JSON으로 바꿔준다.
        JSONObject jsonObject = new JSONObject(response.getBody());

        String objectKey = jsonObject.getString("objectKey");
        String fileName = jsonObject.getString("fileName");
        String fileUrl = jsonObject.getString("fileUrl");
        Application application = applicationRepository.findById(applicationId).get();
        File file = File.builder().uploadFileName(fileName).url(fileUrl).application(application).build();
        fileRepository.save(file);
    }

    // 파일 저장 서비스에서 파일 정보 가져오기
    public ResponseEntity<String> getFile(Long applicationId, String authorization, String refreshToken){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/json");
        headers.add("Authorization", authorization);
        headers.add("RefreshToken", refreshToken);

        ResponseEntity<String> response = new RestTemplate().exchange(
                "http:localhost:8000/resource/files?id="+applicationId,
                HttpMethod.GET,
                new HttpEntity<String>(headers),
                String.class);
        return response;
    }


}

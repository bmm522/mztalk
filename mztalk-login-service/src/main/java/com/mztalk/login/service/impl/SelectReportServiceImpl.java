package com.mztalk.login.service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mztalk.login.domain.dto.EditReponseDto;
import com.mztalk.login.domain.dto.ReportResponseDto;
import com.mztalk.login.domain.dto.Result;
import com.mztalk.login.domain.dto.UserInfoDto;
import com.mztalk.login.domain.entity.Report;
import com.mztalk.login.domain.entity.User;
import com.mztalk.login.repository.ReportRepository;
import com.mztalk.login.service.SelectReportService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SelectReportServiceImpl implements SelectReportService {

    private final ReportRepository reportRepository;
    @Override
    public Result<?> getAllReport() {
        List<Report>  reportList = reportRepository.findAllByReportStatus("Y");
        List<ReportResponseDto> reportResponseDtoList = new ArrayList<>();

        for(Report report : reportList){
            reportResponseDtoList.add(new ReportResponseDto(report, new UserInfoDto(report.getUser())));
        }

        return new Result<>(reportResponseDtoList);
    }

    @Override
    public Result<?> getEditList(long userNo) {
        List<Report> reportList = reportRepository.getEditListOfUserNo(userNo);
        List<EditReponseDto> editReponseDtoList = new ArrayList<>();
        JsonParser parser = new JsonParser();
        System.out.println("for문돌아?");
        for(Report report : reportList){
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "text/html");
            System.out.println("boardId : " + report.getBoardId());
            System.out.println("serviceName : " + report.getServiceName());
            try {
                ResponseEntity<String> response = new RestTemplate().exchange(
                        "http://localhost:8000/resource/main-image?bNo=" + report.getBoardId() + "&serviceName=" + report.getServiceName(),
                        HttpMethod.GET,
                        new HttpEntity<String>(headers),
                        String.class
                );
                JSONObject jsonObject = new JSONObject(response.getBody());
                JSONObject jsonData = jsonObject.getJSONObject("data");
                editReponseDtoList.add(new EditReponseDto(jsonData.getString("imageUrl"), report));
            } catch (Exception e){
                editReponseDtoList.add(new EditReponseDto("https://mztalk-resource-server.s3.ap-northeast-2.amazonaws.com/eca2a863-533a-4b19-9e98-d716addc5ad1-mentor.jpg", report));
            }



        }

        return new Result<>(editReponseDtoList);
    }

}

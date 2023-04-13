package com.mztalk.auction.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ImageRestDto {

    private String imageUrl;
    private String objectKey;

    public ImageRestDto(ResponseEntity<String> response){
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject jsonData = jsonObject.getJSONObject("data");

        this.imageUrl = jsonData.getString("imageUrl");
        this.objectKey = jsonData.getString("objectKey");
    }


    public List<ImageRestDto> getImageRestDtoList(ResponseEntity<String> response) {
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONArray data = jsonObject.getJSONArray("data");
        List<ImageRestDto> imageRestDtoList = new ArrayList<>();
        for(int i = 0; i < data.length(); i++) {
            JSONObject resultObject = data.getJSONObject(i);
            ImageRestDto imageRestDto = new ImageRestDto(resultObject.getString("imageUrl"), resultObject.getString("objectKey"));
            imageRestDtoList.add(imageRestDto);
        }
        return imageRestDtoList;
    }
}

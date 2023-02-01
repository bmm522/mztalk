package com.mztalk.auction.domain.dto;

import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class ImageRestDto {

    private String imageUrl;
    private String objectKey;
    private List<ImageRestDto> imageRestDtoList;

    public ImageRestDto(){}
    public ImageRestDto(ResponseEntity<String> response){
        JSONObject jsonObject = new JSONObject(response.getBody());
        JSONObject jsonData = jsonObject.getJSONObject("data");

        this.imageUrl = jsonData.getString("imageUrl");
        this.objectKey = jsonData.getString("objectKey");
    }

    public List<ImageRestDto> getImageRestDtoList(ResponseEntity<String> response) {
        JSONArray imageRestDtoList = new JSONArray();
        for(int i = 0; i < imageRestDtoList.length(); i++) {
            ImageRestDto imageRestDto = new ImageRestDto(response);
            this.imageRestDtoList.add(imageRestDto);
        }
        return this.imageRestDtoList;
    }
}

package com.mztalk.auction.domain.dto;

import lombok.Getter;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;

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
}

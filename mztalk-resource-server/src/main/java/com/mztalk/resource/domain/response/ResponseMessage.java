package com.mztalk.resource.domain.response;

public class ResponseMessage {
    public static final String UPLOAD_SUCCESS = "저장 성공";
    public static final String UPLOAD_FAIL = "저장 실패";
    public static final String READ_FILE_SUCCESS = "데이터 조회 성공";
    public static final String NOT_FOUND_FILE = "데이터 조회 실패";

    public static final String NOT_FOUND_MAIN_IMAGE = "메인 이미지를 찾을 수 없음";

    public static final String UPDATE_MAIN_SUCCESS = "메인 이미지 수정 성공";
    public static final String UPDATE_MAIN_FAIL = "메인 이미지 수정 실패";
    public static final String UPDATE_SUB_SUCCESS = "서브 이미지 수정 성공";

    public static final String UPDATE_SUB_FAIL = "서브 이미지 수정 실패";
    public static final String DELETE_FILE_SUCCESS = "파일 삭제 완료";
    public static final String DELETE_IMAGE_FAIL = "파일 삭제 실패";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String DB_ERROR = "데이터베이스 에러";
}

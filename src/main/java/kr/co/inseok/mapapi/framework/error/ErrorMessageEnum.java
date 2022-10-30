package kr.co.inseok.mapapi.framework.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum ErrorMessageEnum {
    QUERY_REQUIRED("한글자 이상의 검색어를 입력해주세요.");

    private final String errorMessage;

}

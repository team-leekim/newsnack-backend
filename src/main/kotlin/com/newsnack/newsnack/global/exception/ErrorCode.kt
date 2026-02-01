package com.newsnack.newsnack.global.exception

import org.springframework.http.HttpStatus

/**
 * 전역에서 사용할 에러 코드를 정의하는 Enum 클래스
 *
 * @property status HTTP 상태 코드
 * @property code 에러 코드
 * @property message 기본 에러 메시지
 */
enum class ErrorCode(val status: HttpStatus, val code: String, val message: String) {
    // Common
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "유효하지 않은 입력 값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "C002", "지원하지 않는 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C003", "서버 내부에서 오류가 발생했습니다."),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "C004", "요청 값의 타입이 유효하지 않습니다."),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "C005", "접근 권한이 없습니다."),
    URL_NOT_FOUND(HttpStatus.NOT_FOUND, "C007", "요청한 URL을 찾을 수 없습니다."),

    // Content
    TODAY_NEWSNACK_NOT_FOUND(HttpStatus.NOT_FOUND, "N001", "배포된 오늘의 뉴스낵을 찾을 수 없습니다."),
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "N002", "해당 기사를 찾을 수 없습니다."),
    EDITOR_NOT_FOUND(HttpStatus.NOT_FOUND, "N003", "해당 에디터를 찾을 수 없습니다."),
}

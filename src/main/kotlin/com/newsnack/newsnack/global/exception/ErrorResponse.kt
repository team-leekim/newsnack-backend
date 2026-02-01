package com.newsnack.newsnack.global.exception

/**
 * 예외 발생 시 클라이언트에게 반환될 공통 응답 DTO
 *
 * @property status HTTP 상태 코드 값
 * @property code 에러 코드
 * @property message 에러 메시지
 */
data class ErrorResponse(val status: Int, val code: String, val message: String) {
    companion object {
        /** ErrorCode와 메시지를 기반으로 ErrorResponse 객체를 생성하는 정적 팩토리 메서드 */
        fun of(errorCode: ErrorCode, message: String = errorCode.message): ErrorResponse {
            return ErrorResponse(
                status = errorCode.status.value(),
                code = errorCode.code,
                message = message,
            )
        }
    }
}

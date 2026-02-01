package com.newsnack.newsnack.global.exception.content

import com.newsnack.newsnack.global.exception.CustomException
import com.newsnack.newsnack.global.exception.ErrorCode

class TodayNewsnackNotFoundException : CustomException(ErrorCode.TODAY_NEWSNACK_NOT_FOUND)

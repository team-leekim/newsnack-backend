package com.newsnack.newsnack.global.exception.content

import com.newsnack.newsnack.global.exception.CustomException
import com.newsnack.newsnack.global.exception.ErrorCode

class EditorNotFoundException : CustomException(ErrorCode.EDITOR_NOT_FOUND)

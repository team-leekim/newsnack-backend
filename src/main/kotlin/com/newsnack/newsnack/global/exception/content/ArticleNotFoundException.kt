package com.newsnack.newsnack.global.exception.content

import com.newsnack.newsnack.global.exception.CustomException
import com.newsnack.newsnack.global.exception.ErrorCode

class ArticleNotFoundException : CustomException(ErrorCode.ARTICLE_NOT_FOUND)

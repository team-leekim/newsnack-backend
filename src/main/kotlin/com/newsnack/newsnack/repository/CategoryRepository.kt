package com.newsnack.newsnack.repository

import com.newsnack.newsnack.domain.category.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Int>

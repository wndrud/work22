package com.example.work2.repository;  // 패키지 변경

import com.example.work2.domain.Article;  // 도메인 경로 수정
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}

package com.example.work2.controller;  // 패키지 변경

import com.example.work2.domain.Article;      // 도메인 경로 수정
import com.example.work2.repository.ArticleRepository; // 레포지토리 경로 수정
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository repo) {
        this.articleRepository = repo;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("articles", articleRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")));
        return "list";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("article", new Article());
        return "new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute Article article) {
        articleRepository.save(article);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Optional<Article> maybe = articleRepository.findById(id);
        if (maybe.isEmpty()) {
            return "redirect:/articles";
        }
        model.addAttribute("article", maybe.get());
        return "detail";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Article> maybe = articleRepository.findById(id);
        if (maybe.isEmpty()) {
            return "redirect:/articles";
        }
        model.addAttribute("article", maybe.get());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Article form) {
        Optional<Article> maybe = articleRepository.findById(id);
        if (maybe.isEmpty()) {
            return "redirect:/articles";
        }
        Article article = maybe.get();
        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        article.setAuthor(form.getAuthor());
        articleRepository.save(article);
        return "redirect:/articles/" + article.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
        }
        return "redirect:/articles";
    }


}
